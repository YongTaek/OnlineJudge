package kr.jadekim.oj.mainserver.controller;

import kr.jadekim.oj.mainserver.entity.Post;
import kr.jadekim.oj.mainserver.repository.PostRepository;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cheonyujung on 2016. 2. 24..
 */
@Controller
public class WebBoardController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping("/notice")
    public ModelAndView showBoard(ModelAndView modelAndView, @PageableDefault(sort = { "id" }, size = 25) Pageable pageable){
        ArrayList<Map> messages = new ArrayList<>();
        Iterator<Post> post = (Iterator<Post>) postRepository.findAll(pageable);
        for(Post p : post){

        }
        modelAndView.setViewName("board");

        return new ModelAndView("board", "messages", messages);
    }
    @RequestMapping("/question")
    public ModelAndView showQuestion(ModelAndView modelAndView, @PageableDefault(sort = { "id" }, size = 25) Pageable pageable){
        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.setViewName("board");
        return new ModelAndView("board", "messages", messages);
    }
}
