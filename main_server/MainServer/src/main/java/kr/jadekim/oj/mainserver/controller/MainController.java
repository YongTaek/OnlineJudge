package kr.jadekim.oj.mainserver.controller;


import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public @ResponseBody String index(){
        return "Hello!";
    }

}
