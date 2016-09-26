package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Contest;
import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
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

}
