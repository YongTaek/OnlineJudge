package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.ContestRepository;
import kr.jadekim.oj.mainserver.service.ContestService;
import kr.jadekim.oj.mainserver.service.ProblemSetService;
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
        User user = null;
        if(currentUser!=null) {
            user = currentUser.getUser();
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
            List<User> users = c.getExaminers();
            if(users != null) {
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getloginId().matches(user.getloginId())) {
                        isjoin = true;
                        break;
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
    @RequestMapping("info/{id}")
    public ModelAndView Contest(ModelAndView modelAndView, @PathVariable("id") int contest_id, Authentication authentication){
        Map<String,Object> contestinfo = new HashMap<>();
        Contest contest = null;
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
                contestinfo.put("deputy", contest.getDeputies());
//                contestinfo.put("participant", contest.getExaminers());
                contestinfo.put("participant", contest.getTeams());
                System.out.println(contest.getTeams().size());
                System.out.println(contest.getTeams());
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
