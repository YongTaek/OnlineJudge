package kr.jadekim.oj.mainserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cheonyujung on 2016. 2. 24..
 */
public class WebBoardController {
    @RequestMapping("/board")
    public ModelAndView showBoard(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board.tpl");
        return modelAndView;
    }

}
