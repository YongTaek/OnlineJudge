package kr.jadekim.oj.controller.service;

import kr.jadekim.oj.controller.entity.Answer;
import kr.jadekim.oj.controller.entity.Problem;
import kr.jadekim.oj.controller.entity.User;
import kr.jadekim.oj.controller.repository.AnswerRepository;
import kr.jadekim.oj.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Async
    public Future<Answer> saveAnswer(User user,String code,Date submitTime,Problem problem){
        Answer answer = new Answer(user,code,submitTime,problem);

        return new AsyncResult<Answer>(answerRepository.save(answer));

    }
}
