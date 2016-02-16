package kr.jadekim.oj.mainserver.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Contest;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.Team;
import kr.jadekim.oj.mainserver.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Controller
public class ContestController {

    @Autowired
    private ContestService contestService;



    @RequestMapping(value = "/api/v1/start_contest", method = RequestMethod.POST)
    public @ResponseBody String getContestInfo(int contest_id) throws ExecutionException, InterruptedException {
        Contest contest = contestService.getContest(contest_id).get();
        ProblemSet problemSet = new ProblemSet();
        Team team = new Team();
        Date startDate = new Date();
        Date endDate = new Date();
        contest = new Contest(team,startDate,endDate,"kaak",problemSet);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(contest);
    }
}
