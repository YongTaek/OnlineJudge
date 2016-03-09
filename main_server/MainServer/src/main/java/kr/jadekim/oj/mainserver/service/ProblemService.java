package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.TestcaseRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Service
public class ProblemService {

    @Autowired
    private TestcaseRepository testcaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Async
    public Future<Problem> getProblem(int problem_id){
        Problem problem = problemRepository.findOne(problem_id);
        return new AsyncResult<Problem>(problem);
    }

    @Async
    public Future<Iterable<Problem>> findAllProblem(Pageable pageable){
        Iterable<Problem> problems = problemRepository.findAll(pageable);
        return new AsyncResult<>(problems);
    }

    @Async
    public Future<Iterable<Problem>> findByName(String name){
        Iterable<Problem> problems = problemRepository.findByName(name);
        return new AsyncResult<>(problems);
    }

    @Async
    public Future<ModelAndView> getSortedProbByrank(ModelAndView modelAndView){
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = problemRepository.findOrderBySubmitUsers();
        User user = userRepository.findAll().get(1);
        int i= 0;
        for(Problem p : problems){
            Map<String,Object> map = new HashMap<>();
            int success_count = answerRepository.countBySuccessAndProblemId(p.getId());
            int total_count = answerRepository.countByProblemId(p.getId());
            double rate = success_count/total_count *100;
            boolean isSuccess = answerRepository.findIsSuccessTop1ByUserId(user.getId(),p.getId());
            map.put("rank",++i);
            map.put("id",p.getId());
            map.put("name",p.getName());
            map.put("count",success_count);
            map.put("rate",rate);
            map.put("result",isSuccess);
            messages.add(map);
        }
        ArrayList<Integer> pages = new ArrayList<>();
        modelAndView.setViewName("problemRanking");
        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        return new AsyncResult<>(modelAndView);
    }
}
