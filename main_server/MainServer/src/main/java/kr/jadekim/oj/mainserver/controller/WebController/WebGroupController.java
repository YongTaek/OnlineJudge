package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.GroupRepository;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.GroupService;
import kr.jadekim.oj.mainserver.service.ProblemSetService;
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
 * Created by ohyongtaek on 2016. 3. 17..
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/group")
public class WebGroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProblemSetRepository problemSetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    ProblemSetService problemSetService;


    ArrayList<Map> makeMembersInfo(List<User> users) {
        ArrayList<Map> members = new ArrayList<>();
        for (User u : users) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", u.getName());
            map.put("prob_count", u.getSuccess_count());
            if (u.getAnswers().size() != 0) {
                map.put("rate", u.getSuccess_count() + "/" + u.getAnswers().size());
            }
            members.add(map);
        }
        return members;
    }

    @RequestMapping("info/{id}")
    public ModelAndView myGroupInfo(@PathVariable("id")int id, ModelAndView modelAndView, Authentication authentication) {
        Group group;
        ArrayList<Map> messages = new ArrayList<>();
        try {
            group = groupService.findOne(id).get();
            List<ProblemSet> problemSets = group.getMustProblemSet();
            for (ProblemSet p : problemSets) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", p.getName());
                messages.add(map);
            }
            List<User> groupUsers = group.getUsers();
            System.out.println(groupUsers.get(0).getName());
            ArrayList<Map> members = makeMembersInfo(groupUsers);
            System.out.println(members.get(0).get("name"));
            modelAndView.addObject("members", members);
            modelAndView.addObject("userGroup", group.getName());
            modelAndView.addObject("id",group.getId());
            modelAndView.addObject("messages",messages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("groupInfo");
        return modelAndView;
    }
    @RequestMapping("myGroup")
    public ModelAndView groupInfo(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        modelAndView = createMyPage(modelAndView,loginUser);
        modelAndView.setViewName("myGroupInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateGroup(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if (loginUser.getGroup() == null) {
            modelAndView.setViewName("groupCreate");
        } else {
            modelAndView.setViewName("redirect:/group");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView CreateGroup(ModelAndView modelAndView, HttpServletRequest request, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String group_name = request.getParameter("group_name");
        boolean isprivate = false;
        if (Integer.valueOf(request.getParameter("isprivate")) == 1) {
            isprivate = true;
        } else if (Integer.valueOf(request.getParameter("isprivate")) == 2) {
            isprivate = false;
        }
        modelAndView.setViewName("redirect:/group/list");
        if (loginUser == null) {
            return modelAndView;
        }
        Group group = new Group(loginUser, isprivate, group_name);
        groupRepository.save(group);
        loginUser.setGroup(group);
        userRepository.save(loginUser);
        return modelAndView;
    }


    @RequestMapping("list")
    public ModelAndView ShowGroup(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {


        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Group> groups = groupRepository.findAll(pageable);
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        for (Group group : groups) {
            Map<String, Object> map = new HashMap<>();
            String name = group.getName();
            String user = group.getJjang().getName();
            String isprivate;
            if (group.isPrivateJoin()) {
                isprivate = "O";
            } else {
                isprivate = "X";
            }
            map.put("id", group.getId());
            map.put("name", name);
            map.put("user", user);
            map.put("isprivate", isprivate);
            if (loginUser != null && loginUser.getGroup() != null && loginUser.getGroup().getId() == group.getId()) {
                map.put("isMyGroup", true);
            }
            messages.add(map);
        }
        modelAndView.addObject("userGroup", loginUser.getGroup());
        modelAndView.addObject("loginUser", loginUser);
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("groupList");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/join/{id}", method = RequestMethod.GET)
    public ModelAndView showJoinGroup(ModelAndView modelAndView, @PathVariable("id") int id, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        int group_id = id;
        if (loginUser == null) {
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

    @PreAuthorize("(hasAuthority('USER') or hasAuthority('ADMIN'))")
    @RequestMapping(value = "/join/{id}", method = RequestMethod.POST)
    public ModelAndView JoinGroup(ModelAndView modelAndView, @PathVariable("id") int id, HttpServletRequest request, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        Group group = groupRepository.findOne(id);
        if (group.isPrivateJoin()) {
            group.getWaitUsers().add(loginUser);
            groupRepository.save(group);
        } else {

            if (loginUser.getGroup() == null) {
                List<User> group_user = group.getUsers();
                group_user.add(loginUser);
                loginUser.setGroup(group);
                groupRepository.save(group);
            }
        }
        modelAndView.setViewName("redirect:/group");
        return modelAndView;
    }

    @RequestMapping("/myGroup/setting")
    public ModelAndView groupSetting(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if(loginUser.getGroup() == null || loginUser.getGroup().getJjang().getId() != loginUser.getId()) {
            modelAndView.addObject("error","권한이 없습니다.");
            modelAndView.setViewName("redirect:/group/info");
            return modelAndView;
        }
        modelAndView.addObject("me",loginUser.getName());
        modelAndView = createMyPage(modelAndView,loginUser);
        modelAndView.setViewName("groupSetting");
        return modelAndView;
    }
    
    @RequestMapping(value = "/myGroup/setting/requiredProblem",method = RequestMethod.GET)
    public ModelAndView modifyRequiredProblemSet(ModelAndView modelAndView, Authentication authentication,Pageable pageable) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if(loginUser.getGroup() == null || loginUser.getGroup().getJjang().getId() != loginUser.getId()) {
            modelAndView.addObject("error","권한이 없습니다.");
            modelAndView.setViewName("redirect:/group/info");
            return modelAndView;
        }
            List<ProblemSet> problemSets = problemSetService.findAllProblemSets(pageable);
            List<ProblemSet> groupProblemSets = loginUser.getGroup().getMustProblemSet();
            List<Map> addProblemSets = new ArrayList<>();
            List<Map> removeProblemSets = new ArrayList<>();
            for(ProblemSet set: problemSets) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",set.getName());
                map.put("id",set.getId());
                map.put("count",set.getProblemList().size());
                if(groupProblemSets.contains(set)) {
                    removeProblemSets.add(map);
                }else {
                    addProblemSets.add(map);
                }
            }
            ArrayList<Integer> pages = new ArrayList<>();
            modelAndView.addObject("addProblemsets",addProblemSets);
            modelAndView.addObject("removeProblemsets",removeProblemSets);
            modelAndView.addObject("pages",pages);
            modelAndView.addObject("userGroup",loginUser.getGroup().getName());


        modelAndView.setViewName("addRequiredProblemset");
        return modelAndView;
    }

    @RequestMapping(value = "/myGroup/setting/requiredProblem",method = RequestMethod.POST)
    public ModelAndView applyRequiredProblemSet(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if(loginUser.getGroup() == null || loginUser.getGroup().getJjang().getId() != loginUser.getId()) {
            modelAndView.addObject("error","권한이 없습니다.");
            modelAndView.setViewName("redirect:/group/info");
            return modelAndView;
        }
        String id = request.getParameter("id");
        try {
            ProblemSet problemSet = problemSetService.findOne(Integer.parseInt(id)).get();
            Group group = loginUser.getGroup();
            if(group.getMustProblemSet().contains(problemSet)) {
                group.getMustProblemSet().remove(problemSet);
            }else {
                group.getMustProblemSet().add(problemSet);
            }
            groupService.save(group);
        } catch (InterruptedException e) {
            modelAndView.addObject("error","오류가 발생하였습니다.");
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("redirect:/addRequiredProblemSet");
        return modelAndView;
    }

    public ModelAndView createMyPage(ModelAndView modelAndView, User loginUser) {
        Group group = null;
        ArrayList<Map> messages = new ArrayList<>();
        try {
            group = groupService.findByUserId(loginUser.getId()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (group != null) {
            List<ProblemSet> problemSets = group.getMustProblemSet();
            for (ProblemSet p : problemSets) {
                int success_count = problemSetRepository.countByUserSuccess(p.getId(), loginUser.getId());
                int total_count = problemSetRepository.countById(p.getId());
                Map<String, Object> map = new HashMap<>();
                map.put("name", p.getName());
                map.put("rate", success_count + "/" + total_count);
                messages.add(map);
            }
            List<User> groupUsers = group.getUsers();
            ArrayList<Map> members = makeMembersInfo(groupUsers);
            modelAndView.addObject("members", members);
            List<User> waitUsers = group.getWaitUsers();
            ArrayList<Map> waitMembers = makeMembersInfo(waitUsers);
            modelAndView.addObject("waitMembers", waitMembers);
            modelAndView.addObject("userGroup", group.getName());
            if(group.getJjang().getId() == loginUser.getId()) {
                modelAndView.addObject("jjang",loginUser.getName());
            }
            modelAndView.addObject("userGroup", group.getName());
        }
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

}
