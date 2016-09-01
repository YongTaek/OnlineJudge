package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.GradeResult;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.TestcaseRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Future<Problem> getProblem(int problem_id){
        Problem problem = problemRepository.findOne(problem_id);
        return new AsyncResult<Problem>(problem);
    }


    public Future<Iterable<Problem>> findAllProblem(Pageable pageable){
        Iterable<Problem> problems = problemRepository.findAll(pageable);
        return new AsyncResult<>(problems);
    }


    public Future<Iterable<Problem>> findByName(String name){
        Iterable<Problem> problems = problemRepository.findByName(name);
        return new AsyncResult<>(problems);
    }

    public Future<ModelAndView> getSortedProbByrank(ModelAndView modelAndView,User loginUser){
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = problemRepository.findOrderBySubmitUsers();
        int i= 0;
        for(Problem p : problems){
            Map<String,Object> map = new HashMap<>();
            int success_count = answerRepository.countBySuccessAndProblemId(p.getId());
            int total_count = answerRepository.countByProblemId(p.getId());
            double rate = success_count/total_count *100;

            GradeResult isSuccess;
            if(loginUser!=null) {
                isSuccess = answerRepository.findIsSuccessTop1ByUserId(loginUser.getId(), p.getId());
                if (isSuccess != null) {
                    map.put("result", isSuccess.getIsSuccess());
                }
            }
            map.put("rank",++i);
            map.put("id",p.getId());
            map.put("name",p.getName());
            map.put("count",success_count);
            map.put("rate",rate);

            messages.add(map);
        }
        ArrayList<Integer> pages = new ArrayList<>();
        modelAndView.setViewName("rankingProblems");
        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        return new AsyncResult<>(modelAndView);

    }

    public Future<ModelAndView> getSortedProbByrank(ModelAndView modelAndView, User loginUser, String searchString) {
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = problemRepository.findByName(searchString);
        int i= 0;
        for(Problem p : problems){
            Map<String,Object> map = new HashMap<>();
            int success_count = answerRepository.countBySuccessAndProblemId(p.getId());
            int total_count = answerRepository.countByProblemId(p.getId());
            double rate = success_count/total_count *100;

            GradeResult isSuccess;
            if(loginUser!=null) {
                isSuccess = answerRepository.findIsSuccessTop1ByUserId(loginUser.getId(), p.getId());
                if (isSuccess != null) {
                    map.put("result", isSuccess.getIsSuccess());
                }
            }
            map.put("rank",++i);
            map.put("id",p.getId());
            map.put("name",p.getName());
            map.put("count",success_count);
            map.put("rate",rate);

            messages.add(map);
        }
        ArrayList<Integer> pages = new ArrayList<>();
        modelAndView.setViewName("rankingProblems");
        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        return new AsyncResult<>(modelAndView);
    }
    public Future<Integer> countAllProblem(){
        return new AsyncResult<>(problemRepository.countAll());
    }


    public Iterable<Problem> findProblemRecent(){
        return problemRepository.findAll(new PageRequest(0,100));
    }

    public Future<List<Problem>> findProblemsBySubmittedUser(User user) {
        System.out.println(user.getId());
        return new AsyncResult<>(problemRepository.findProblemByUserId(user.getId()));
    }
}
