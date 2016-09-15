package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.entity.Team;
import kr.jadekim.oj.mainserver.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by vmffkxlgnqh1 on 2016. 9. 12..
 */
@Service
public class TeamInfoService {

    @Autowired
    TeamRepository teamRepository;


    @Async
    public Future<Team> findOne(int id) {
        return new AsyncResult<>(teamRepository.findOne(id));
    }
}
