package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.Contest;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public Future<Contest> getContest(int contest_id) {
        Contest contest = contestRepository.findOne(contest_id);
        return new AsyncResult<Contest>(contest);
    }
    public Future<Iterable<Contest>> findAllContest(Pageable pageable){
        Iterable<Contest> contests = contestRepository.findAll(pageable);
        return new AsyncResult<>(contests);
    }

    public void save(Contest contest) {
        contestRepository.save(contest);
    }

    public void deleteContest(Contest contest) {
        contestRepository.delete(contest);
    }
}
