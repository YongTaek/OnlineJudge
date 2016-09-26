package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ohyongtaek on 2016. 8. 26..
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal().getClass().equals(CurrentUser.class)) {
            User loginUser = ((CurrentUser)authentication.getPrincipal()).getUser();
            if(loginUser != null && modelAndView != null) {
                modelAndView.addObject("loginUser", loginUser.getName());
                modelAndView.addObject("user_id", loginUser.getId());
            }
        }

    }
}
