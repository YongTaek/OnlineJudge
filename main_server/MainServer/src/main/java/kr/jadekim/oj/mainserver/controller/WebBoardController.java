package kr.jadekim.oj.mainserver.controller;

import kr.jadekim.oj.mainserver.entity.Board;
import kr.jadekim.oj.mainserver.entity.Post;
import kr.jadekim.oj.mainserver.entity.Question;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cheonyujung on 2016. 2. 24..
 */
@Controller
public class WebBoardController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @RequestMapping("/notice/test")
    public @ResponseBody String test(){
        Board b = new Board("notice");
        boardRepository.save(b);
        User user = userRepository.findOne(1);
        Post p = new Post(b,user,new Date(),"title","content");
        postRepository.save(p);

        return "true";
    }

    @RequestMapping("/notice")
    public ModelAndView showBoard(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable) {
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Post> post = postRepository.findAll(pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        for (Post p : post) {
            Map<String, Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getTitle();
            String user = p.getAuthor().getName();
            String date = simpleDateFormat.format(p.getTime());
            map.put("number", num);
            map.put("user", user);
            map.put("date", date);
            map.put("title", name);
            messages.add(map);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("board");

        return modelAndView;
    }

    @RequestMapping("/question")
    public ModelAndView showQuestion(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable) {
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Question> questions = questionRepository.findAll(pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        for (Question p : questions) {
            Map<String, Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getPost().getTitle();
            String user = p.getPost().getAuthor().getName();
            String date = simpleDateFormat.format(p.getPost().getTime());
            String quest = p.getProblem().getName();
            map.put("number", num);
            map.put("user", user);
            map.put("date", date);
            map.put("title", name);
            map.put("question", quest);
            messages.add(map);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("questionlist");
        return modelAndView;
    }

    @RequestMapping("/notice/{id}")
    public ModelAndView notice(ModelAndView modelAndView, Pageable pageable, @PathVariable("id")int id) {
        Post notice = postRepository.findOne(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Map<String, Object> map = new HashMap<>();
        int num = id;
        String title = notice.getTitle();
        String user = notice.getAuthor().getName();
        String date = simpleDateFormat.format(notice.getTime());
        String contents = notice.getContent();
        map.put("number", num);
        map.put("user", user);
        map.put("date", date);
        map.put("title", title);
        map.put("contents", contents);
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("notice");
        return modelAndView;
    }
}
