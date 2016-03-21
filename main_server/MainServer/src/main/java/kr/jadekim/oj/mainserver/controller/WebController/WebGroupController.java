package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.GroupRepository;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
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
 * Created by ohyongtaek on 2016. 3. 17..
 */
@Controller
@RequestMapping("/group")
public class WebGroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProblemSetRepository problemSetRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("info")
    public ModelAndView groupInfo(ModelAndView modelAndView, HttpSession session){
        ArrayList<Map> messages = new ArrayList<>();
        User user = (User) session.getAttribute("loginUserInfo");
        Group group;
        if(user==null){
            if(modelAndView.getView()==null){
                modelAndView.setViewName("redirect:/group");
            }
            return modelAndView;
        }else{

            group  = groupRepository.findGroupByUserId(user.getId());
            List<ProblemSet> problemSets = group.getMustProblemSet();
            for(ProblemSet p : problemSets){
                int success_count = problemSetRepository.countByUserSuccess(p.getId(),user.getId());
                int total_count = problemSetRepository.countById(p.getId());
                Map<String,Object> map = new HashMap<>();
                map.put("name",p.getName());
                map.put("rate",success_count+"/"+total_count);
                messages.add(map);
            }
            modelAndView.addObject("messages",messages);
            List<User> groupUsers = group.getUsers();
            ArrayList<Map> members = new ArrayList<>();
            for(User u: groupUsers){
                Map<String,Object> map = new HashMap<>();
                map.put("name",u.getName());

                map.put("prob_count",u.getSuccess_count());
                map.put("rate",u.getSuccess_count()+"/"+u.getAnswers().size());
                members.add(map);
            }
            modelAndView.addObject("loginUser",user);
            modelAndView.addObject("messages",messages);
            modelAndView.addObject("members",members);
            modelAndView.setViewName("groupInfo");
            return modelAndView;
        }
    }
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
        userRepository.save(jjang);
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView ShowGroup(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable,HttpSession session){

        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Group> groups = groupRepository.findAll(pageable);
        User loginUser = (User) session.getAttribute("loginUserInfo");
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
            if(loginUser!=null && loginUser.getGroup()!=null && loginUser.getGroup().getId()==group.getId()) {
                map.put("isMyGroup", true);
            }
            messages.add(map);
        }
        modelAndView.addObject("loginUser",loginUser);
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
            if(user.getGroup()==null) {
                List<User> group_user = group.getUsers();
                group_user.add(user);
                user.setGroup(group);
                groupRepository.save(group);
            }
        }
        modelAndView.setViewName("redirect:/group");
        return modelAndView;
    }

}
