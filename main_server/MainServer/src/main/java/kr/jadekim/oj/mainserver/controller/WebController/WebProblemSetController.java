package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.service.ProblemSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Autowired
    ProblemService problemService;

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


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create-problemset-for-contest", method = RequestMethod.GET)
    public ModelAndView createProblemSetForContest(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        ArrayList<Map> messages = problemService.getCreateProblemsetForContest(loginUser);
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("problemsetForContest");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create-problemset-for-contest", method = RequestMethod.POST)
    public ModelAndView createProblemSetForContestPost(ModelAndView modelAndView, HttpServletRequest request, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String name = request.getParameter("problemset_title");
        ProblemSet problemSet = new ProblemSet(loginUser, name);
        String[] checkedList = request.getParameterValues("selectedProblem");
        List<Problem> checkedProblem = new ArrayList<>();
        for(String s : checkedList){
            Problem tempProblem = problemRepository.findOne(Integer.valueOf(s));
            checkedProblem.add(tempProblem);
        }
        problemSet.setProblemList(checkedProblem);
        problemSetRepository.save(problemSet);
        modelAndView.setViewName("redirect:/problemset");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create-problemset", method = RequestMethod.GET)
    public ModelAndView createProblemSet(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        ArrayList<Map> messages = problemService.getCreateProblemset(loginUser);
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("Createproblemset");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create-problemset", method = RequestMethod.POST)
    public ModelAndView createProblemSetPost(ModelAndView modelAndView, HttpServletRequest request, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String name = request.getParameter("problemset_title");
        ProblemSet problemSet = new ProblemSet(loginUser, name);
        String[] checkedList = request.getParameterValues("selectedProblem");
        List<Problem> checkedProblem = new ArrayList<>();
        for(String s : checkedList){
            Problem tempProblem = problemRepository.findOne(Integer.valueOf(s));
            checkedProblem.add(tempProblem);
        }
        problemSet.setProblemList(checkedProblem);
        problemSetRepository.save(problemSet);
        modelAndView.setViewName("redirect:/problemset");
        return modelAndView;
    }
}
