package kr.jadekim.oj.mainserver.controller;


import kr.jadekim.oj.mainserver.controller.WebController.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView){

        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin")
    public @ResponseBody String adminIndex(){
        return "true";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/success")
    public @ResponseBody String loginTest(HttpServletRequest request){
        return request.getSession().getId();
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor());
            }
        };
    }

}
