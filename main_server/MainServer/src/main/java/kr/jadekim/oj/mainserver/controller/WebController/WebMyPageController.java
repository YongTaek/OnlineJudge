package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    UserRepository userRepository;

    @RequestMapping()
    public ModelAndView mypage(ModelAndView modelAndView, Authentication authentication){
        ArrayList<Map> message = new ArrayList<>();
        CurrentUser currentUser= (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        ArrayList<Integer> solvedProblemnum = new ArrayList<>();
        ArrayList<Integer> unsolvedProblemnum = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(loginUser.getAnswers() == null){
            map.put("solvedProblemnum", null);
            map.put("unsolvedProblemnum", null);
            map.put("user_name", loginUser.getName());
            map.put("user_id", loginUser.getLoginId());
            map.put("solvedProblem", 0);
            map.put("submit", 0);
            if(loginUser.getGroup() == null){
                map.put("group", null);
            }else {
                map.put("group", loginUser.getGroup().getName());
            }
            map.put("correct", 0);
            map.put("incorrect", 0);
        }
        for(Answer answer : loginUser.getAnswers()){
            int temp_ans = answer.getId();
            boolean check = true;
            if(answer.getResult().getIsSuccess() == true){
                for(int i=0;i<solvedProblemnum.size();i++){
                    if(temp_ans == solvedProblemnum.get(i)){
                        check = false;
                    }
                }
                if(check == true){
                    solvedProblemnum.add(temp_ans);
                }
            }else{
                for(int i=0;i<unsolvedProblemnum.size();i++){
                    if(temp_ans == unsolvedProblemnum.get(i)){
                        check = false;
                    }
                }
                if(check == true){
                    unsolvedProblemnum.add(temp_ans);
                }
            }
        }
        map.put("solvedProblemnum", solvedProblemnum);
        map.put("unsolvedProblemnum", unsolvedProblemnum);
        map.put("solvedProblem", solvedProblemnum.size());
        map.put("submit", solvedProblemnum.size()+unsolvedProblemnum.size());
        if(loginUser.getGroup() == null){
            map.put("group", null);
        }else {
            map.put("group", loginUser.getGroup().getName());
        }
        int count = 0;
        for(Answer answer : loginUser.getAnswers()){
            if(answer.getResult().getIsSuccess()){
                count++;
            }
        }
        map.put("correct", count);
        map.put("incorrect", loginUser.getAnswers().size() - count);
        map.put("user_name", loginUser.getName());
        map.put("user_id", loginUser.getloginId());

        modelAndView.addObject("loginUser",loginUser.getName());
        modelAndView.addObject("messages", map);
        modelAndView.setViewName("mypage");
        return modelAndView;
    }


    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public ModelAndView showSetting(ModelAndView modelAndView, Authentication authentication) {
        CurrentUser currentUser= (CurrentUser) authentication.getPrincipal();
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
            modelAndView.setViewName("settinglist");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public ModelAndView modifyinfo(ModelAndView modelAndView, HttpServletRequest request, Authentication authentication){
        CurrentUser currentUser= (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        loginUser.setName(name);
        loginUser.setEmail(email);
        modelAndView.setViewName("redirect:/myPage/setting");
        return modelAndView;
    }

    @RequestMapping(value = "/setting/password", method = RequestMethod.GET)
    public ModelAndView showsettingpw(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("rediret:/notice");
            return modelAndView;
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", user.getLoginId());
            map.put("user_name", user.getName());
            modelAndView.addObject("messages", map);
            modelAndView.setViewName("settingPassword");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/setting/password", method = RequestMethod.POST)
    public ModelAndView settingpw(ModelAndView modelAndView, HttpServletRequest request, HttpSession session){
        String origin_password = request.getParameter("origin_password");
        String new_password = request.getParameter("new_password");
        String new_password1 = request.getParameter("new_password1");
        User user = (User) session.getAttribute("loginUserInfo");
        modelAndView.setViewName("redirect:/myPage/setting");
        if(user.getLoginPw().equals(origin_password)){
            return modelAndView;
        }else{
            if(new_password.equals(new_password1)){
                user.setLoginPw(new_password);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/setting/withdrawal", method = RequestMethod.GET)
    public ModelAndView showWithdrawl(ModelAndView modelAndView, HttpSession session){
        modelAndView.setViewName("withdrawl");
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute("loginUserInfo");
        map.put("user_id", user.getloginId());
        map.put("user_name", user.getName());
        modelAndView.addObject("messages",map);
        return modelAndView;
    }

    @RequestMapping(value = "/setting/withdrawal", method = RequestMethod.POST)
    public ModelAndView withdrawal(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user.getGroup() != null){
            List<User> groupUser = user.getGroup().getUsers();
            groupUser.remove(user);
        }
        userRepository.delete(user);
        return modelAndView;
    }
}
