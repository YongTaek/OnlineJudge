package kr.jadekim.oj.controller.service;

import kr.jadekim.oj.controller.entity.Problem;
import kr.jadekim.oj.controller.entity.Testcase;
import kr.jadekim.oj.controller.repository.ProblemRepository;
import kr.jadekim.oj.controller.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Service
public class ProblemService {

    @Autowired
    private TestcaseRepository testcaseRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Async
    public Future<List<Testcase>> getTestcase(int problem_id){
        Problem problem = problemRepository.getOne(problem_id);
        List<Testcase> testcaseList = testcaseRepository.findByProblem(problem);
        return new AsyncResult<>(testcaseList);
    }
}
