package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import kr.jadekim.oj.mainserver.repository.PostRepository;
import kr.jadekim.oj.mainserver.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cheonyujung on 2016. 9. 1..
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/myTeam")
public class YTeamController {
    //user.getTeam().getContest()
    //myTeamList.tpl
    //팀이름 대회

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ContestRepository contestRepository;

    @RequestMapping("")
    public ModelAndView showTeamList(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Team> teamList = teamRepository.findTeamListByUserId(loginUser.getId());
        System.out.println(teamList);

        for (Team t : teamList) {

            Map<String, Object> map = new HashMap<>();
            int num = t.getId();
            String name = t.getName();
            String admin = t.getAdmin().getName();
            String contest = "test";
            int contest_id = 0;

            if(t.getContest() != null) {
                contest = t.getContest().getName();
                contest_id = t.getContest().getId();

            }
            map.put("number", num);
            map.put("name", name);
            map.put("admin", admin);
            map.put("contest", contest);
            map.put("contest_id", contest_id + "");
            messages.add(map);
        }

        if (loginUser != null) {
            modelAndView.addObject("loginUser", loginUser.getName());
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("myTeamList");

        return modelAndView;
    }

}
