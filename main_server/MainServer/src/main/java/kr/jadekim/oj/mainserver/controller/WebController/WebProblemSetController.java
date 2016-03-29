package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.ProblemSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 2. 24..
 */

@Controller
@SessionAttributes("loginUserInfo")
@RequestMapping("/problemset")
public class WebProblemSetController {

    @Autowired
    ProblemSetRepository problemSetRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProblemSetService problemSetService;

    @RequestMapping
    public ModelAndView list(ModelAndView modelAndView, Pageable pageable, Authentication authentication){
        CurrentUser currentUser = null;
        if(authentication!=null){
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if(currentUser!=null) {
            loginUser = currentUser.getUser();
        }
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<ProblemSet> problemSets = problemSetService.findAllProblemSet(pageable);
        for(ProblemSet p : problemSets){
            Map<String,Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getName();
            int total = p.getProblemList().size();
            int success;
            if(loginUser!=null){
                success = problemSetService.countAllProblem(p.getId(),loginUser.getId());
                map.put("rate",success+"/"+total);
            }
            map.put("id",num);
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
        problemSetRepository.save(problemSet);
        for(Problem p : problems){
            p.setProblemSet(problemSet);
            problemRepository.save(p);
            problemSet.getProblemList().add(p);
        }
        System.out.println(problemSet.getProblemList().size());

        return "success";
    }
}
