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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView list(ModelAndView modelAndView, Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<ProblemSet> problemSets = problemSetService.findAllProblemSet(pageable);
        for (ProblemSet p : problemSets) {
            Map<String, Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getName();
            int total = p.getProblemList().size();
            int success;
            if (loginUser != null) {
                success = problemSetService.countAllProblem(p.getId(), loginUser.getId());
                map.put("rate", success + "/" + total);
            }
            map.put("id", num);
            map.put("name", name);
            messages.add(map);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("workbook");

        return modelAndView;
    }

    @RequestMapping("test")
    public
    @ResponseBody
    String test() {
        User user = userRepository.findByloginId("ka123ak").get(0);
        ProblemSet problemSet = new ProblemSet(user, "test");
        Iterable<Problem> problems = problemRepository.findAll();
        problemSetRepository.save(problemSet);
        for (Problem p : problems) {
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
    public ModelAndView createProblemSetForContestPost(ModelAndView modelAndView, HttpServletRequest request, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        modelAndView = duplicate(loginUser, request, modelAndView);
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
    public ModelAndView createProblemSetPost(ModelAndView modelAndView, HttpServletRequest request, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        modelAndView = duplicate(loginUser, request, modelAndView);
        return modelAndView;
    }

    public ModelAndView duplicate(User loginUser, HttpServletRequest request, ModelAndView modelAndView) {
        String name = request.getParameter("problemset_title");
        ProblemSet problemSet = new ProblemSet(loginUser, name);
        String[] checkedList = request.getParameterValues("selectedProblem");
        for (String s : checkedList) {
            Problem tempProblem = problemRepository.findOne(Integer.valueOf(s));
            problemSet.getProblemList().add(tempProblem);
        }
        problemSetRepository.save(problemSet);
        modelAndView.setViewName("redirect:/problemset");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView notice(ModelAndView modelAndView, @PathVariable("id") int id, Authentication authentication) {
        ProblemSet problemSet = problemSetRepository.findOne(id);
        List<Problem> problemList = problemSet.getProblemList();

        String problemset_name = problemSet.getName();
        String author = "asd";

        ArrayList<Map> messages = new ArrayList<>();

        Map<String, Object> problemset_map = new HashMap<>();
        problemset_map.put("problemset_name", problemset_name);
        problemset_map.put("problemset_author", author);
        problemset_map.put("problemset_id", id);

        for(Problem p : problemList){
            Map<String, Object> map = new HashMap<>();
            System.out.println(p);
            System.out.println(p.getId()+"id");
            map.put("problem_id", p.getId());
            map.put("problem_name", p.getName());
            map.put("problem_clear", 0+"");
            map.put("problem_rate", 0+"%");
            map.put("problem_I", "X");
            messages.add(map);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("problemset", problemset_map);

        modelAndView.setViewName("problemsetInfo");
        return modelAndView;
    }
}

