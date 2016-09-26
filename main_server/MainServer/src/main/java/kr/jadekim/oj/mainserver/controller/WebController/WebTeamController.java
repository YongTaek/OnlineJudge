package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.AnswerListRepository;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import kr.jadekim.oj.mainserver.repository.TeamRepository;
import kr.jadekim.oj.mainserver.service.ContestService;
import kr.jadekim.oj.mainserver.service.TeamService;
import kr.jadekim.oj.mainserver.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/contest/team")
public class WebTeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    AnswerListRepository answerListRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserService userService;

    @Autowired
    ContestService contestService;

    @RequestMapping("info/{id}")
    public ModelAndView TeamInfo(ModelAndView modelAndView, @PathVariable("id") int team_id, Authentication authentication) {

        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = currentUser.getUser();
        Team team = null;
        ArrayList<Map> members = new ArrayList<>();
        try {
            team = teamService.findOne(team_id).get();
            if (team != null) {
                modelAndView.addObject("teamname",team.getName());
                modelAndView.addObject("contestname",team.getContest().getName());
                List<User> users = team.getUsers();
                User admin = team.getAdmin();
                if(admin != null){
                    modelAndView.addObject("admin",admin.getName());
                    if(admin == loginUser ){
                        modelAndView.addObject("isadmin",true);
                    }
                    else{
                        modelAndView.addObject("isadmin",false);
                    }
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



    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView, Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        Team team = new Team(loginUser, "test");
        Contest contest = contestRepository.getOne(1);
        team.setContest(contest);
        team.setAdmin(loginUser);
        team.getUsers().add(loginUser);
        teamRepository.save(team);
        loginUser.getTeamList().add(team);
        userService.saveUser(loginUser);
        modelAndView.setViewName("redirect:/myPage");
        return modelAndView;
    }

    @RequestMapping("/myTeam/list")
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
            map.put("team_id", t.getId());
            map.put("admin_id", t.getAdmin().getId());
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


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public ModelAndView createTeamShow(ModelAndView modelAndView, @PathVariable("id") int id,Authentication authentication){
        Map<String, Object> map = new HashMap<>();
        map.put("contest_id", id);
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("teamCreate");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public ModelAndView createTeam(ModelAndView modelAndView, @PathVariable("id") int id,Authentication authentication, HttpServletRequest request){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String name = request.getParameter("team_name");
        Team team = new Team(loginUser, name);
        Contest contest = contestRepository.findOne(id);
        team.setContest(contest);
        team.setAdmin(loginUser);
        team.getUsers().add(loginUser);
        teamService.save(team);
        loginUser.getTeamList().add(team);
        userService.saveUser(loginUser);
        contest.getTeams().add(team);
        contestService.save(contest);
        modelAndView.setViewName("redirect:/contest/info/"+id);
        return modelAndView;
    }

}

