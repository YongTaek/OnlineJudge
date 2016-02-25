package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 2. 24..
 */

@Controller
@RequestMapping("/problemset")
public class WebProblemSetController {

    @Autowired
    ProblemSetRepository problemSetRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping
    public ModelAndView list(ModelAndView modelAndView){
        User user =userRepository.findByloginId("ka123ak").get(0);
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<ProblemSet> problemSets = problemSetRepository.findAll();
        for(ProblemSet p : problemSets){
            Map<String,Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getName();
            String author = p.getauthor().getName();
            int total = p.getProblemList().size();
            map.put("rate"," ");
            map.put("id",num);
            map.put("author",author);
            map.put("name",name);
            messages.add(map);
        }
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("workbook");

        return modelAndView;
    }

    @RequestMapping("test")
    public @ResponseBody String test(){
        User user = userRepository.findByloginId("ka123ak").get(0);
        ProblemSet problemSet = new ProblemSet(user,"test");
        Iterable<Problem> problems = problemRepository.findAll();
        for(Problem p : problems){
            problemSet.getProblemList().add(p);
        }
        problemSetRepository.save(problemSet);
        return "success";
    }
}
