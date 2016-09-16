package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.service.TeamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/info/{id}")
    public ModelAndView index(ModelAndView modelAndView,@PathVariable("id") int team_id, Authentication authentication){

        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        Team team = null;
        ArrayList<Map> members = new ArrayList<>();
        try {
            team = teamInfoService.findOne(team_id).get();
            if(team != null){
                List<User> users = team.getUsers();
                for (User u : users) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", u.getName());
                    members.add(map);
                }
            }
            modelAndView.addObject("members", members);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        modelAndView.setViewName("teamInfo");
        return modelAndView;
    }


}
