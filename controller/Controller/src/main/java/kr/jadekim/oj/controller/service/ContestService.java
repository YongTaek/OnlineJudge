package kr.jadekim.oj.controller.service;

import kr.jadekim.oj.controller.entity.Contest;
import kr.jadekim.oj.controller.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    @Async
    public Future<Contest> getContest(int contest_id){
        Contest contest= contestRepository.getOne(contest_id);
        return new AsyncResult<Contest>(contest);
    }
}
