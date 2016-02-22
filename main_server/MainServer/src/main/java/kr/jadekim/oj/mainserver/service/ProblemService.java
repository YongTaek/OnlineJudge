package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public Future<Problem> getProblem(int problem_id){
        Problem problem = problemRepository.getOne(problem_id);
        return new AsyncResult<>(problem);
    }

    @Async
    public Future<Iterable<Problem>> findAllProblem(Pageable pageable){
        Iterable<Problem> problems = problemRepository.findAll(pageable);
        return new AsyncResult<>(problems);
    }
}
