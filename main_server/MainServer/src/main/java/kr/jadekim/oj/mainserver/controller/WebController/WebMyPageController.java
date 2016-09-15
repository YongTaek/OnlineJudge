package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.GroupService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 3. 4..
 */

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/myPage")
public class WebMyPageController {

    Gson gson = new GsonBuilder().create();

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


    @RequestMapping()
    public ModelAndView mypage(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        Map<String, Object> map = new HashMap<>();
        try {
            List<Problem> submitProblems = problemService.findProblemsBySubmittedUser(loginUser).get();
            List<Answer> answers = loginUser.getAnswers();
            List<Integer> successProblems = answerService.findSuccessAnswerByUserId(loginUser.getId()).get();
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
        if (loginUser.getGroup() == null) {
            map.put("group", null);
        } else {
            map.put("group", loginUser.getGroup().getName());
        }
        map.put("user_name", loginUser.getName());
        map.put("user_id", loginUser.getloginId());

        modelAndView.addObject("messages", map);
        modelAndView.setViewName("mypage");
        return modelAndView;
    }


    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public ModelAndView showSetting(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if (loginUser == null) {
            modelAndView.setViewName("rediret:/notice");
            return modelAndView;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", loginUser.getLoginId());
            map.put("user_name", loginUser.getName());
            map.put("name", loginUser.getName());
            map.put("email", loginUser.getEmail());
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
        modelAndView.setViewName("redirect:/myPage/setting");
        return modelAndView;
    }

    @RequestMapping(value = "/setting/password", method = RequestMethod.GET)
    public ModelAndView showsettingpw(ModelAndView modelAndView, Authentication authentication) {
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getLoginId());
        map.put("user_name", user.getName());
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
            modelAndView.setViewName("redirect:/myPage/setting/password");
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

    @RequestMapping(value = "/setting/withdrawal", method = RequestMethod.GET)
    public ModelAndView showWithdrawl(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.setViewName("withdrawl");
        Map<String, Object> map = new HashMap<>();
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        map.put("user_id", user.getloginId());
        map.put("user_name", user.getName());
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
