package kr.jadekim.oj.mainserver.controller;


import kr.jadekim.oj.mainserver.entity.CurrentUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */

@Controller
public class MainController {

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView, Authentication authentication){
        if(authentication != null ) {
            modelAndView.addObject("loginUser", ((CurrentUser)authentication.getPrincipal()).getUser().getName());
            System.out.println("login");
        }
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
    public @ResponseBody String loginTest(){
        return "true";
    }


}
