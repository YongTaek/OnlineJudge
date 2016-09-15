package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.GradeResult;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
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
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    public Answer saveAnswer(Answer answer) {

        return answerRepository.save(answer);

    }

    @Async
    public Future<GradeResult> findIsSuccessByUserId(int user_id,int problem_id){
        GradeResult isSuccess =answerRepository.findIsSuccessTop1ByUserId(user_id,problem_id);

        return new AsyncResult<GradeResult>(isSuccess);
    }

    @Async
    public Future<Integer> countByProblemId(int problem_id){
        int count = answerRepository.countByProblemId(problem_id);

        return new AsyncResult<>(count);
    }

    @Async
    public Future<Integer> countSuccessByProblemId(int problem_id){
        int count = answerRepository.countBySuccessAndProblemId(problem_id);
        return new AsyncResult<>(count);
    }

    @Async
    public Future<Integer> countUserByProblemId(int problem_id){
        int count = answerRepository.countUserByProblemId(problem_id);

        return new AsyncResult<>(count);
    }

    @Async
    public Future<List<Answer>> countAnswerByUserId(int user_id) {
        return new AsyncResult<>(answerRepository.findAnswerByUserId(user_id));
    }

    @Async
    public Future<List<Integer>> findSuccessAnswerByUserId(int user_id) {
        return new AsyncResult<>(answerRepository.findSuccessAnswerByUserId(user_id));
    }
}
