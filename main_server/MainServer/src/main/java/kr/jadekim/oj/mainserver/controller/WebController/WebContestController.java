package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import kr.jadekim.oj.mainserver.service.ContestService;
import kr.jadekim.oj.mainserver.service.ProblemSetService;
import kr.jadekim.oj.mainserver.service.TeamService;
import kr.jadekim.oj.mainserver.service.UserService;
import kr.jadekim.oj.mainserver.util.Pagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 9. 14..
 */
@Controller
@RequestMapping("/contest")
public class WebContestController {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    ProblemSetService problemSetService;

    @Autowired
    ContestService contestService;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createContest(ModelAndView modelAndView) {
        modelAndView.setViewName("contestCreate");
        return modelAndView;
    }

//    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView createContestPost(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request) throws ParseException {

        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        User admin = user;

        String title = request.getParameter("contest_title");
        String rawStartDate = request.getParameter("contest_start_date");
        String rawStartTime = request.getParameter("contest_start_time");
        String rawEndDate = request.getParameter("contest_end_date");
        String rawEndTime = request.getParameter("contest_end_time");
//        String admins = request.getParameter("admins");
        String problemset = request.getParameter("problemset");
        long start = format.parse(rawStartDate+" "+rawStartTime).getTime();
        long end = format.parse(rawEndDate+" "+rawEndTime).getTime();
        if(title.equals("") || rawStartDate.equals("") || rawStartTime.equals("") || rawEndDate.equals("") || rawEndTime.equals("")){
            modelAndView.setViewName("redirect:/contest/create");
            return modelAndView;
        }else if(end - start <0){
            modelAndView.setViewName("redirect:/contest/create");
            return modelAndView;
        }
        try {
            Contest contest = new Contest();
            contest.setName(title);
            contest.setAdmin(admin);
            contest.setStartTime(format.parse(rawStartDate +" " +  rawStartTime));
            contest.setEndTime(format.parse(rawEndDate + " " + rawEndTime));
//            List<User> users = userService.findUsersByUserIdString(admins);
//            System.out.println(users.size());
//            List<User> deputies = contest.getDeputies();
//            for(User u : users) {
//                System.out.println(u.getName());
//                deputies.add(u);
//            }
            if(!problemset.equals("")) {
                ProblemSet problemSet = problemSetService.findOne(Integer.parseInt(problemset)).get();
                contest.setProblemSet(problemSet);
            }
            contestService.save(contest);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("redirect:/contest/list");

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("create/insert-admin")
    public ModelAndView insertAdmins(ModelAndView modelAndView, Authentication authentication, @PageableDefault(sort = { "id" }, size = 10) Pageable pageable) {

        try {
            User loginUser = ((CurrentUser)authentication.getPrincipal()).getUser();
            List<User> users = userService.findAll(pageable,loginUser).get();
            List<Map> addAdmins = new ArrayList<>();
            ArrayList<Integer> pages;
            for (User user : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("name",user.getName());
                map.put("id",user.getId());
                addAdmins.add(map);
            }
            modelAndView.addObject("addAdmins",addAdmins);
            pages = Pagenation.generatePagenation(addAdmins.size(),pageable.getPageSize());
            modelAndView.addObject("pages", pages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("createInsertAdmin");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("create/insert-set")
    public ModelAndView insertSet(ModelAndView modelAndView, Authentication authentication, Pageable pageable) {
        List<ProblemSet> problemSets = problemSetService.findAllProblemSets(pageable);
        List<Map> addProblemSets = new ArrayList<>();
        for (ProblemSet set : problemSets) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", set.getName());
            map.put("id", set.getId());
            map.put("count", set.getProblemList().size());
            if (!set.isCanModify()) {
                addProblemSets.add(map);
            }
        }
        ArrayList<Integer> pages = new ArrayList<>();
        modelAndView.addObject("addProblemSets", addProblemSets);
        modelAndView.addObject("pages", pages);

        modelAndView.setViewName("createInsertSet");
        return modelAndView;
    }
    @RequestMapping("/test")
    public ModelAndView testCreateContest(ModelAndView modelAndView, Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        Date startTime = new Date();
        Date endTime = new Date();
        Contest contest = new Contest(startTime, endTime,"test");
        List<User> examiner = contest.getExaminers();
        examiner.add(loginUser);
        contestRepository.save(contest);

        modelAndView.setViewName("redirect:/contest");
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public ModelAndView ContestList(ModelAndView modelAndView, @PageableDefault(size = 10) Pageable pageable, Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if(currentUser!=null){
           loginUser = currentUser.getUser();
        }
        User user = null;
        if(loginUser!=null) {
            user = userService.getUserByLoginId(loginUser.getLoginId()).get();
        }
        Iterable<Contest> contests = null;
        try {
            contests = contestService.findAllContest(pageable).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<Map> messages = makeMassages(contests,user);
        ArrayList<Integer> pages = Pagenation.generatePagenation(messages.size(),pageable.getPageSize());

        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        modelAndView.setViewName("contestList");
        return modelAndView;
    }

    public ArrayList<Map> makeMassages(Iterable<Contest> contests, User user){
        ArrayList<Map> massages = new ArrayList<>();
        for(Contest c:contests){
            Map<String,Object> map = new HashMap<>();
            Boolean isjoin = false;
            List<Team> teams = c.getTeams();
            if(teams !=null){
                int size = teams.size();
                int i = 0;
                if(user !=null) {
                    while (i < size) {
                        User admin = teams.get(i).getAdmin();
                        if (admin != null && teams.get(i).getAdmin() == user) {
                            isjoin = true;
                            break;
                        }
                        List<User> users = teams.get(i).getUsers();
                        for (int j = 0; j < users.size(); j++) {
                            if (users.contains(user)) {
                                isjoin = true;
                                i = size;
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
            map.put("id",c.getId());
            map.put("isjoin",isjoin);
            map.put("name",c.getName());
            User winner = c.getWinner();
            User subWinner = c.getSubwinner();
            if(winner != null && subWinner != null){
                map.put("winner",c.getWinner().getName());
                map.put("subwinner",c.getSubwinner().getName());
            }
            else{
                map.put("winner","");
                map.put("subwinner","");
            }
            User admin = c.getAdmin();
            if(admin != null){
                map.put("admin",c.getAdmin().getName());
            }
            else{
                map.put("admin","");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date today = new Date();
            Date startTime = c.getStartTime();
            Date endTime = c.getEndTime();
            if(startTime != null && endTime != null &&today.before(endTime)&&today.after(startTime)){
                map.put("isgoing",1);
                map.put("startTime",format.format(startTime));
            }
            else if(startTime != null && endTime != null &&today.before(startTime)){
                map.put("isgoing",2);
                map.put("startTime",format.format(startTime));
            }
            else{
                map.put("isgoing",false);
                map.put("startTime",format.format(startTime));
            }
            massages.add(map);
        }
        return massages;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/setting/{id}", method = RequestMethod.GET)
    public ModelAndView settingContestShow(ModelAndView modelAndView, @PathVariable("id") int contest_id, Authentication authentication) {
        try {
            Contest contest = contestService.getContest(contest_id).get();
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            User loginUser = currentUser.getUser();
            if(loginUser.getId() != contest.getAdmin().getId()){
                modelAndView.setViewName("redirect:/contest/info/"+contest_id);
                return modelAndView;
            }
            ArrayList<Map> messages = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("contest_name", contest.getName());
            map.put("contest_id", contest_id);
            if(contest.getProblemSet() != null) {
                map.put("contest_problemset", contest.getProblemSet().getName());
            }else{
                map.put("contest_problemset", "");
            }
            messages.add(map);
            ArrayList<Map> Deputies = new ArrayList<>();
            for(User user : contest.getDeputies()){
                Map<String, Object> deputy = new HashMap<>();
                deputy.put("deputy_name", user.getName());
                deputy.put("deputy_id", user.getId());
                Deputies.add(deputy);
            }
            modelAndView.addObject("Deputies",Deputies);
            ArrayList<Map> requestDeputies = new ArrayList<>();
            for(User user : contest.getRequestDeputy()){
                Map<String, Object> requestDeputy = new HashMap<>();
                requestDeputy.put("requestDeputy_name", user.getName());
                System.out.println(requestDeputy.get("requestDeputy_name"));
                requestDeputy.put("requestDeputy_id",user.getId());
                requestDeputies.add(requestDeputy);
            }
            System.out.println(requestDeputies.size());
            modelAndView.addObject("requestDeputies",requestDeputies);
            modelAndView.addObject("messages", map);
            modelAndView.setViewName("contestsetting");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/setting/{id}", method = RequestMethod.POST)
    public ModelAndView settingContest(ModelAndView modelAndView, @PathVariable("id") int contest_id, HttpServletRequest request,Authentication authentication) throws ExecutionException, InterruptedException {
        Contest contest = contestService.getContest(contest_id).get();
        ProblemSet problemSet;
        String contest_name = request.getParameter("contestname");
        String contest_problemset = request.getParameter("contestProblemset");
        if(!contest_problemset.equals("")){
            problemSet = problemSetService.findOne(Integer.valueOf(contest_problemset)).get();
            contest.setProblemSet(problemSet);
        }
        contest.setName(contest_name);
        contestService.save(contest);
        modelAndView.setViewName("redirect:/contest/info/"+contest_id);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("info/deputy")
    public @ResponseBody String askRequest(HttpServletRequest request, Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        int contest_id = Integer.parseInt(request.getParameter("contest_id"));
        Contest contest;
        try {
            contest = contestService.getContest(contest_id).get();
            contest.getRequestDeputy().add(loginUser);
            contestService.save(contest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "{'success' : true}";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("add/deputy")
    public @ResponseBody String addRequest(HttpServletRequest request, Authentication authentication) throws ExecutionException, InterruptedException {
        String[] temp =  request.getParameter("deputy_id").split("/");
        int contest_id = Integer.valueOf(temp[1]);
        System.out.println(contest_id+"*"+temp[0]);
        Contest contest = contestService.getContest(contest_id).get();
        User deputy = userService.findUserById(Integer.valueOf(temp[0]));
        contest.getRequestDeputy().remove(deputy);
        contest.getDeputies().add(deputy);
        contestService.save(contest);
        return "{'success' : true}";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("delete/requestDeputy")
    public @ResponseBody String deleteRequest(HttpServletRequest request, Authentication authentication) throws ExecutionException, InterruptedException {
        String[] temp =  request.getParameter("deputy_id").split("/");
        System.out.println(temp[0]+"!"+temp[1]);
        int contest_id = Integer.valueOf(temp[1]);
        System.out.println(contest_id+"*"+temp[0]);
        Contest contest = contestService.getContest(contest_id).get();
        User deputy = userService.findUserById(Integer.valueOf(temp[0]));
        contest.getRequestDeputy().remove(deputy);
        contestService.save(contest);
        return "{'success' : true}";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("delete/deputy")
    public @ResponseBody String deleteDeputy(HttpServletRequest request, Authentication authentication) throws ExecutionException, InterruptedException {
        String[] temp =  request.getParameter("deputy_id").split("/");
        System.out.println(temp[0]+"!"+temp[1]);
        int contest_id = Integer.valueOf(temp[1]);
        System.out.println(contest_id+"*"+temp[0]);
        Contest contest = contestService.getContest(contest_id).get();
        User deputy = userService.findUserById(Integer.valueOf(temp[0]));
        contest.getDeputies().remove(deputy);
        contestService.save(contest);
        return "{'success' : true}";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("delete")
    public @ResponseBody String deleteContest(HttpServletRequest request, Authentication authentication) throws ExecutionException, InterruptedException {
        String temp =  request.getParameter("contest_id");
        int contest_id = Integer.valueOf(temp);
        Contest contest = contestService.getContest(contest_id).get();
        contestService.deleteContest(contest);
        for(Team team : contest.getTeams()){
            teamService.deleteTeam(team);
        }
        return "{'success' : true}";
    }

    @RequestMapping("info/{id}")
    public ModelAndView Contest(ModelAndView modelAndView, @PathVariable("id") int contest_id, Authentication authentication){
        Map<String,Object> contestinfo = new HashMap<>();
        Contest contest = null;
        User user = null;
        if(authentication != null){
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            user = currentUser.getUser();
        }
        try {
            contest = contestService.getContest(contest_id).get();
            if(contest != null) {
                contestinfo.put("id", contest.getId());
                contestinfo.put("name", contest.getName());
                Date startTime = contest.getStartTime();
                Date endTime = contest.getEndTime();
                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
                contestinfo.put("start", format.format(startTime));
                contestinfo.put("end", format.format(endTime));
                User admin = contest.getAdmin();
                if(admin != null){
                    contestinfo.put("admin", admin.getName());
                }
                else{
                    contestinfo.put("admin","");
                }
                contestinfo.put("deputy", contest.getDeputies());
                contestinfo.put("participant", contest.getTeams());
                contestinfo.put("contest_id", contest_id);
                List<User> deputies = contest.getDeputies();
                for(User temp : contest.getRequestDeputy()){
                    deputies.add(temp);
                }
                if(user != null){
                    for(User temp : deputies){
                        if(temp.getId() == user.getId()){
                            contestinfo.put("canApply", false);
                            break;
                        }
                    }
                    if(contestinfo.get("canApply") == null){
                        contestinfo.put("canApply", true);
                    }
                }else{
                    contestinfo.put("canApply", true);
                }
                System.out.println(contest.getTeams().size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<Map> probleminfo = null;
        if(contest !=null){
            probleminfo = new ArrayList<>();
            ProblemSet problemset = contest.getProblemSet();
            List<Problem> problems = new ArrayList<>();
            if(problemset != null){
                problems = problemset.getProblemList();

            }
            for(int i=0;i<problems.size();i++){
                Map<String,Object> problem = new HashMap<>();
                Problem prob = problems.get(i);
                problem.put("id",prob.getId());
                problem.put("name",prob.getName());
                probleminfo.add(problem);
            }
        }
        modelAndView.addObject("message",contestinfo);
        modelAndView.addObject("problems",probleminfo);
        modelAndView.setViewName("contestInfo");
        return modelAndView;
    }


}
