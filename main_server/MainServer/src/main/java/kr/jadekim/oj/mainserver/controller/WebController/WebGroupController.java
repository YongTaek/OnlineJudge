package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cheonyujung on 2016. 3. 16..
 */
@Controller
@RequestMapping("/group")
public class WebGroupController {

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateGroup(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null) {
            modelAndView.setViewName("redirect:/group");
            return modelAndView;
        }
        if(user.getGroup() == null){
            modelAndView.setViewName("groupCreate");
        }else{
            modelAndView.setViewName("redirect:/group");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView CreateGroup(ModelAndView modelAndView, HttpServletRequest request, HttpSession session){
        String group_name = request.getParameter("group_name");
        boolean isprivate = false;
        if(Integer.valueOf(request.getParameter("isprivate")) == 1){
            isprivate = true;
        }else if(Integer.valueOf(request.getParameter("isprivate")) == 2){
            isprivate = false;
        }
        User jjang = (User) session.getAttribute("loginUserInfo");
        modelAndView.setViewName("redirect:/group");
        if(jjang == null){
            return modelAndView;
        }
        Group group = new Group(jjang, isprivate, group_name);
        groupRepository.save(group);
        jjang.setGroup(group);
        return modelAndView;
    }

    @RequestMapping("")
    public ModelAndView ShowGroup(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable){

        ArrayList<Map> messages = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for(Group group : groups){
            Map<String , Object> map = new HashMap<>();
            String name = group.getName();
            String user = group.getJjang().getName();
            String isprivate;
            if(group.isPrivateJoin()){
                isprivate = "O";
            }else{
                isprivate = "X";
            }
            map.put("id", group.getId());
            map.put("name", name);
            map.put("user", user);
            map.put("isprivate", isprivate);
            messages.add(map);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("groupList");
        return modelAndView;
    }

    @RequestMapping(value = "/join/{id}", method = RequestMethod.GET)
    public ModelAndView showJoinGroup(ModelAndView modelAndView, @PathVariable("id")int id, HttpSession session){
        int group_id = id;
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("redirect:/group");
            return modelAndView;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", group_id);
        ArrayList<Map> messages = new ArrayList<>();
        messages.add(map);
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("groupJoin");
        return modelAndView;
    }

    @RequestMapping(value = "/join/{id}", method = RequestMethod.POST)
    public ModelAndView JoinGroup(ModelAndView modelAndView, @PathVariable("id")int id, HttpServletRequest request, HttpSession session){
        Group group = groupRepository.findOne(id);
        if(group.isPrivateJoin()){
            User user = (User) session.getAttribute("loginUserInfo");
        }else{
            User user = (User) session.getAttribute("loginUserInfo");
            List<User> group_user = group.getUsers();
            group_user.add(user);
            group.setUsers(group_user);
        }
        modelAndView.setViewName("redirect:/group");
        return modelAndView;
    }

}
