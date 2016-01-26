package kr.jadekim.oj.controller.controller;

import kr.jadekim.oj.controller.entity.Answer;
import kr.jadekim.oj.controller.entity.Contest;
import kr.jadekim.oj.controller.entity.Team;
import kr.jadekim.oj.controller.entity.User;
import kr.jadekim.oj.controller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping
    public @ResponseBody String index(){
        return "Hello!";
    }

}
