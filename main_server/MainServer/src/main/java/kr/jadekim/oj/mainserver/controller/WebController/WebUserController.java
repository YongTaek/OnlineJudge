package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.GroupRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.GroupService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 3. 29..
 */
@Controller
public class WebUserController {


    @Autowired
    UserService userService;

    @Autowired
    ProblemService problemService;

    @Autowired
    AnswerService answerService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupService groupService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //    @PreAuthorize("hasAuthority('USER')")
//    @RequestMapping(value = "/login?logout", method = RequestMethod.GET)
//    public java.lang.String logout() {
//        System.out.println("logout Success");
//        return "redirect:/problem/list";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView login(ModelAndView modelAndView) {
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;
//    }
    @RequestMapping(value = "/login?logout", method = RequestMethod.POST)
    public ModelAndView logout(ModelAndView mav, HttpServletRequest request, HttpServletResponse response) {
        for(Cookie cookie : request.getCookies()){
            cookie.setMaxAge(0);
        }
        return mav;
    }
    @RequestMapping("/login")
    public ModelAndView loginPage(@RequestParam(value = "matcherror", required = false) boolean error, ModelAndView mav) {
        if(error) {
            mav.addObject("matcherror",true);
            System.out.println("error");
        }
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/join",method = RequestMethod.GET)
    public ModelAndView joinPage(){
        return new ModelAndView("join");
    }

    @RequestMapping(value = "/join",method = RequestMethod.POST)
    public ModelAndView join(HttpServletRequest request){
        User user;
        try {
            user = userService.join(request.getParameter("login_id"), request.getParameter("login_pw"), request.getParameter("name"), request.getParameter("email")).get();
            CurrentUser currentUser = new CurrentUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser,null,currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/problem/list");
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/user/change",method = RequestMethod.POST)
    public ModelAndView changeInfo(HttpServletRequest request,Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        User loginUser = currentUser.getUser();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        loginUser = userService.changeInfo(email,password,loginUser);
        return new ModelAndView("redirect:/home");
    }


    @RequestMapping("user/{id}")
    public ModelAndView userInfoShow(ModelAndView modelAndView, @PathVariable("id") int id, Authentication authentication){
        CurrentUser currentUser = null;
        boolean isMe = false;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        User user;
        if (currentUser != null) {
            user = currentUser.getUser();
            if(user.getId() == id){
                isMe = true;
            }
        }else{
            user = userService.findUserById(id);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            List<Problem> submitProblems = problemService.findProblemsBySubmittedUser(user).get();
            List<Answer> answers = user.getAnswers();
            List<Integer> successProblems = answerService.findSuccessAnswerByUserId(user.getId()).get();
            List<Integer> failProblems = new ArrayList<>();
            int successCount = 0;
            for (Answer a : answers) {
                if(a.getResult() != null) {
                    if (a.getResult().getIsSuccess()) {
                        successCount++;
                    }
                    if (!a.getResult().getIsSuccess()) {
                        if (!submitProblems.contains(a.getProblem().getId()) && !failProblems.contains(a.getProblem().getId())) {
                            failProblems.add(a.getProblem().getId());
                        }
                    }
                }
            }
            map.put("solvedProblem", submitProblems.size());
            map.put("submit", answers.size());
            map.put("correct", successCount);
            map.put("incorrect", answers.size() - successCount);
            map.put("solvedProblemnum", successProblems);
            map.put("unsolvedProblemnum", failProblems);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (user.getGroup() == null) {
            map.put("group", null);
        } else {
            map.put("group", user.getGroup().getName());
        }
        map.put("user_name", user.getName());
        map.put("user_loginid", user.getloginId());
        map.put("user_id", user.getId());
        map.put("isMe", isMe);
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("mypage");

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public ModelAndView showSetting(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        boolean isMe = true;
        if (loginUser == null) {
            modelAndView.setViewName("redirect:/board/notice");
            return modelAndView;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", loginUser.getLoginId());
            map.put("user_name", loginUser.getName());
            map.put("name", loginUser.getName());
            map.put("email", loginUser.getEmail());
            map.put("isMe", isMe);
            modelAndView.addObject("messages", map);
            modelAndView.setViewName("settingUserInfo");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public ModelAndView modifyinfo(ModelAndView modelAndView, HttpServletRequest request, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        loginUser.setName(name);
        loginUser.setEmail(email);
        userService.saveUser(loginUser);
        ((CurrentUser) authentication.getPrincipal()).setUser(loginUser);
        modelAndView.setViewName("redirect:/user/setting");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/setting/password", method = RequestMethod.GET)
    public ModelAndView showsettingpw(ModelAndView modelAndView, Authentication authentication) {
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getLoginId());
        map.put("user_name", user.getName());
        map.put("isMe", true);
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("settingPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/setting/password", method = RequestMethod.POST)
    public ModelAndView settingpw(ModelAndView modelAndView, HttpServletRequest request, Authentication authentication) {
        String origin_password = request.getParameter("origin_password");
        String new_password = request.getParameter("new_password");
        String new_password1 = request.getParameter("new_password1");
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (!bCryptPasswordEncoder.matches(origin_password, user.getLoginPw())) {
            modelAndView.setViewName("redirect:/user/setting/password");
            return modelAndView;
        } else {
            if (new_password.equals(new_password1)) {
                user.setLoginPw(bCryptPasswordEncoder.encode(new_password));
                userService.saveUser(user);
            }
        }
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/setting/withdrawal", method = RequestMethod.GET)
    public ModelAndView showWithdrawl(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.setViewName("withdrawl");
        Map<String, Object> map = new HashMap<>();
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        map.put("user_id", user.getloginId());
        map.put("user_name", user.getName());
        map.put("isMe", true);
        modelAndView.addObject("messages", map);
        return modelAndView;
    }

    @RequestMapping(value = "/setting/withdrawal", method = RequestMethod.POST)
    public ModelAndView withdrawal(ModelAndView modelAndView, Authentication authentication, HttpSession session) {
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (user.getGroup() != null) {
            user.getGroup().getUsers().remove(user);
            groupService.save(user.getGroup());
        }
        userRepository.delete(user);
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
