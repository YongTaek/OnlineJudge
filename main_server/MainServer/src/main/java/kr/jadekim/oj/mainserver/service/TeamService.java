package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.Team;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import kr.jadekim.oj.mainserver.repository.TeamRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by cheonyujung on 2016. 9. 1..
 */
public class TeamService {

    TeamRepository teamRepository;

    ContestRepository contestRepository;

    @Async
    public Future<List<Team>> findAllTeam(){
        return new AsyncResult<>(teamRepository.findAll());
    }

    @Async
    public Future<List<Team>> findTeamListByContest(int contest_id) {
        return new AsyncResult<>(teamRepository.findTeamListByContestId(contest_id));
    }

    @Async
    public Future<List<Team>> findTeamListByUser(int user_id){
        return new AsyncResult<>(teamRepository.findTeamListByUserId(user_id));
    }

    @Async
    public void save(Team team) {
        teamRepository.save(team);
    }


}
