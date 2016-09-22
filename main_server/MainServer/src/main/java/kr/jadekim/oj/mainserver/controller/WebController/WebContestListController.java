package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Contest;
import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.ContestService;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by vmffkxlgnqh1 on 2016. 9. 22..
 */

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/contest")
public class WebContestListController {

    @Autowired
    ContestService contestService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView ContestList(ModelAndView modelAndView, @PageableDefault(size = 10) Pageable pageable, Authentication authentication){
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        Iterable<Contest> contests = null;
        User user = null;
        try {
            contests = contestService.findAllContest(pageable).get();
            if(currentUser!=null) {
                user = currentUser.getUser();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<Map> messages = makeMassages(contests,user);
        ArrayList<Integer> pages = Pagenation.generatePagenation(messages.size(),pageable.getPageSize());

        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        modelAndView.setViewName("allContest");
        return modelAndView;
    }
    public ArrayList<Map> makeMassages(Iterable<Contest> contests, User user){
        ArrayList<Map> massages = new ArrayList<>();
        for(Contest c:contests){
            Map<String,Object> map = new HashMap<>();
            Boolean isjoin = false;
            List<User> users = c.getExaminers();
            for(int i =0; i< users.size();i++){
                if(users.get(i) == user){
                    isjoin = true;
                    break;
                }
            }
            map.put("number",c.getId());
            map.put("isjoin",isjoin);
            map.put("name",c.getName());
            map.put("winner",c.getWinner().getName());
            map.put("subwinner",c.getSubwinner().getName());
            map.put("admin",c.getAdmin().getName());
            Date today = new Date();
            if(today.before(c.getEndTime())&&today.after(c.getStartTime())){
                map.put("isgoing",true);
            }
            massages.add(map);
        }
        return massages;
    }
    @RequestMapping("/{id}")
    public ModelAndView Contest(ModelAndView modelAndView,@PathVariable("id") int contest_id, Authentication authentication){

        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("contest");
        return modelAndView;
    }


    @RequestMapping("/join/{id}")
    public ModelAndView JoinConest(ModelAndView modelAndView,@PathVariable("id") int contest_id, Authentication authentication){


        ArrayList<Map> messages = new ArrayList<>();
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("allContest");
        return modelAndView;
    }
}
