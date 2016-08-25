package kr.jadekim.oj.mainserver.config;

import kr.jadekim.oj.mainserver.entity.Role;
import kr.jadekim.oj.mainserver.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by ohyongtaek on 2016. 1. 25..
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority(String.valueOf(Role.ADMIN))
                    .antMatchers("/myPage/**").hasAuthority(String.valueOf(Role.USER))
                    .antMatchers("/**").permitAll()
                    .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler((request, response, exception) -> response.addHeader("error",exception.getLocalizedMessage()))
                .usernameParameter("login_id")
                .passwordParameter("login_pw")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies()
                .logoutUrl("/login?logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .rememberMe()
                .and();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/");

        http
                .authorizeRequests().anyRequest().authenticated();

    }
}
