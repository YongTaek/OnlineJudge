package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.util.Pagenation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by vmffkxlgnqh1 on 2016. 9. 22..
 */

@Controller
@SessionAttributes("loginUserInfo")
@RequestMapping("/contest")
public class WebContestListController {

    @RequestMapping("/list")
    public ModelAndView ContestList(ModelAndView modelAndView, Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("allContest");
        return modelAndView;
    }
    @RequestMapping("/{id}")
    public ModelAndView Contest(ModelAndView modelAndView,@PathVariable("id") int contest_id){

        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("contest");
        return modelAndView;
    }
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/join/{id}")
    public ModelAndView JoinConest(ModelAndView modelAndView,@PathVariable("id") int contest_id, Authentication authentication){


        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("allContest");
        return modelAndView;
    }
}
