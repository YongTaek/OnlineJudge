package kr.jadekim.oj.controller.controller;

import kr.jadekim.oj.controller.entity.*;
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

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private AnswerListRepository answerListRepository;
    @RequestMapping
    public @ResponseBody String index(){
        return "Hello!";
    }

    @RequestMapping("/users")
    public @ResponseBody
    List<User> getUserList(){
        return userRepository.findAll();
    }
    @RequestMapping("/test")
    public @ResponseBody List<Answer> getSolvedProblem(){
        Team team = new Team();
        team.setName("ka123ak");

        Contest contest = new Contest();
        contest.setManageTeam("ka123ak11");
        contestRepository.save(contest);
        teamRepository.save(team);
        Answer answer = new Answer();
        answer.setCode("asdf");
        answerRepository.save(answer);
        contest.addSolvedProblem(team,answer);
        answerListRepository.save(contest.getAnswerList(team));
        contestRepository.save(contest);
        return contestRepository.getOne(1).getSolvedProblem(team);
    }

}
