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
import org.springframework.boot.Banner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (team != null) {
            modelAndView.addObject("teamname",team.getName());
            modelAndView.addObject("contestname",team.getContest().getName());

            List<User> users = team.getUsers();
            User admin = team.getAdmin();
            User user = userService.getUserByLoginId(loginUser.getloginId()).get();

            List<User> requestUsers = team.getRequestUsers();
            if(admin !=null){
                modelAndView.addObject("admin",admin.getName());
                if(admin == user){
                    modelAndView.addObject("ismember",true);
                    modelAndView.addObject("isadmin",true);
                    modelAndView.addObject("requestperson",false);
                    ArrayList<Map> requestUser = makeRequestUserList(requestUsers);
                    modelAndView.addObject("request",requestUser);
                }
                else if(requestUsers.contains(user)){
                    modelAndView.addObject("ismember",false);
                    modelAndView.addObject("isadmin",false);
                    modelAndView.addObject("request",null);
                    modelAndView.addObject("requestperson",true);
                }
                else if(users.contains(user)){
                    modelAndView.addObject("ismember",true);
                    modelAndView.addObject("isadmin",false);
                    modelAndView.addObject("request",null);
                    modelAndView.addObject("requestperson",false);
                }
                else{
                    modelAndView.addObject("isadmin",false);
                    modelAndView.addObject("request",null);
                    modelAndView.addObject("requestperson",false);
                }
            }
            else{
                modelAndView.addObject("admin","");
                modelAndView.addObject("ismember",false);
                modelAndView.addObject("isadmin",false);
                modelAndView.addObject("request",null);
                modelAndView.addObject("requestperson",false);
            }
            for (User u : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", u.getName());
                map.put("id",u.getId());
                members.add(map);
            }
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
        modelAndView.addObject("action","/contest/team/info/"+team_id);
        modelAndView.setViewName("teamInfo");
        return modelAndView;
    }
    @RequestMapping(value = "/info/{id}/reject",method = RequestMethod.POST)
    public ModelAndView rejectRequest(ModelAndView modelAndView, @PathVariable("id") int team_id,HttpServletRequest request){
        int userid = Integer.valueOf(request.getParameter("id"));
        User user = userService.findUserById(userid);
        Team team = null;
        try {
            team = teamService.findOne(team_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<User> requestUser = null;
        if(team != null && user !=null){
            requestUser = team.getRequestUsers();
            requestUser.remove(user);
            teamRepository.save(team);
        }
        modelAndView.setViewName("redirect:/contest/team/info/"+team_id);
        return modelAndView;
    }
    @RequestMapping(value = "info/{id}/accept",method = RequestMethod.POST)
    public ModelAndView acceptRequest(ModelAndView modelAndView, @PathVariable("id") int team_id,HttpServletRequest request){
        Team team = null;
        int userId = Integer.valueOf(request.getParameter("id"));
        User user = userService.findUserById(userId);
        try {
            team = teamService.findOne(team_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(team != null && user != null){
            List<User> requestUser = team.getRequestUsers();
            requestUser.remove(user);
            List<User> teamMember = team.getUsers();
            teamMember.add(user);
            teamRepository.save(team);
        }
        modelAndView.setViewName("redirect:/contest/team/info/"+team_id);
        return modelAndView;
    }
    @RequestMapping("info/request")
    public @ResponseBody String askRequest(HttpServletRequest request, Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        User user = null;
        if(loginUser !=null){
            user = userService.getUserByLoginId(loginUser.getLoginId()).get();
        }
        int team_id = Integer.parseInt(request.getParameter("team_id"));
        Team team = null;
        try {
            team = teamService.findOne(team_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Contest contest = null;
        if(team !=null){
            contest = team.getContest();
        }
        boolean isjoined = false;
        if(user !=null && contest !=null){
            List<Team> teams = contest.getTeams();
            for(int i=0; i< teams.size();i++){
                List<User> users = teams.get(i).getUsers();
                List<User> users1 = teams.get(i).getRequestUsers();
                if(users.contains(user)){
                    isjoined = true;
                    break;
                }
                if(users1.contains(user)){
                    isjoined = true;
                    break;
                }
            }
        }

        if(isjoined){
            return "이미 가입하거나 가입을 요청한 팀이 있습니다.";
        }
        else if(team!=null){
            List<User>requestUser = team.getRequestUsers();
            requestUser.add(user);
            teamRepository.save(team);
            return "가입신청이 완료되었습니다.";
        }
        else{
            return "오류가 발생했습니다.";
        }
    }
    @RequestMapping("info/{id}/test")
    public ModelAndView requestTest(ModelAndView modelAndView, @PathVariable("id") int team_id,Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = currentUser.getUser();
        Team team = null;
        try {
            team = teamService.findOne(team_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(team !=null){
            List<User> Users = team.getRequestUsers();
            Users.add(loginUser);
        }
        teamService.save(team);
        modelAndView.setViewName("redirect:/contest/team/info/"+team_id);
        return modelAndView;
    }

    public ArrayList<Map> makeRequestUserList(List<User> requestUsers){
        ArrayList<Map> requestUser = new ArrayList<>();
        for(int i=0; i<requestUsers.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("requestName",requestUsers.get(i).getName());
            map.put("requestId",requestUsers.get(i).getId());
            requestUser.add(map);
        }
        return requestUser;
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
        teamService.save(team);
        loginUser.getTeamList().add(team);
        userService.saveUser(loginUser);
        contest.getTeams().add(team);
        contestService.save(contest);
        modelAndView.setViewName("redirect:/contest/info/"+id);
        return modelAndView;
    }

}

