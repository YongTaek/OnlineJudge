package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.AnswerListRepository;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.service.TeamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by vmffkxlgnqh1 on 2016. 9. 6..
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/team")
public class WebTeamInfoController {

    @Autowired
    TeamInfoService teamInfoService;

    @Autowired
    AnswerListRepository answerListRepository;

    @RequestMapping("/info/{id}")
    public ModelAndView TeamInfo(ModelAndView modelAndView, @PathVariable("id") int team_id, Authentication authentication) {

        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        Team team = null;
        ArrayList<Map> members = new ArrayList<>();
        try {
            team = teamInfoService.findOne(team_id).get();
            if (team != null) {
                modelAndView.addObject("teamname",team.getName());
                modelAndView.addObject("contestname",team.getContest().getName());
                List<User> users = team.getUsers();
                User admin = team.getAdmin();
                if(admin != null){
                    modelAndView.addObject("admin",admin.getName());
                }
                for (User u : users) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", u.getName());
                    map.put("id",u.getId());
                    members.add(map);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Answer> answerList = new ArrayList<>();
        ArrayList<Map> results = new ArrayList<>();
        if (team != null) {
            answerList = answerListRepository.findAnswerListByTeamId(team.getId());
            Boolean gradeResult;
            List<Object> result = new ArrayList<>();
            for (int i = 0; i < answerList.size(); i++) {
                gradeResult = answerList.get(i).getResult().getIsSuccess();
                result.add(gradeResult);
                if ((i + 1) % 5 == 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("list", result);
                    results.add(map);
                    result = new ArrayList<>();
                }
            }
        }
        modelAndView.addObject("members", members);
        modelAndView.addObject("results", results);
        modelAndView.setViewName("teamInfo");
        return modelAndView;
    }


}

