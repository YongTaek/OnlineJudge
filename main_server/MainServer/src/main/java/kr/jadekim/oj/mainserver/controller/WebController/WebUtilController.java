package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 3. 4..
 */

@Controller
@SessionAttributes("loginUserInfo")
public class WebUtilController {

    Gson gson = new GsonBuilder().create();

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login?logout", method = RequestMethod.GET)
    public java.lang.String logout(SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "redirect:/problem/list";

    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView) {
        String login_id = request.getParameter("login_id");
        String login_pw = request.getParameter("login_pw");
        User user = null;
        try {
            user = userService.login(login_id, login_pw).get();
            if (user != null) {
                request.getSession().setAttribute("loginUserInfo", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }

<<<<<<< HEAD
    @RequestMapping("/myPage")
    public ModelAndView mypage(ModelAndView modelAndView, HttpSession session){
        ArrayList<Map> message = new ArrayList<>();
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("redirect:/notice");
            return modelAndView;
        }
        ArrayList<Integer> solvedProblemnum = new ArrayList<>();
        ArrayList<Integer> unsolvedProblemnum = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(user.getAnswers() == null){
            map.put("solvedProblemnum", null);
            map.put("unsolvedProblemnum", null);
            map.put("user_name", user.getName());
            map.put("user_id", user.getLoginId());
            map.put("solvedProblem", 0);
            map.put("submit", 0);
            if(user.getGroup() == null){
                map.put("group", null);
            }else {
                map.put("group", user.getGroup().getName());
            }
            map.put("correct", 0);
            map.put("incorrect", 0);
        }
        for(Answer answer : user.getAnswers()){
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
        if(user.getGroup() == null){
            map.put("group", null);
        }else {
            map.put("group", user.getGroup().getName());
        }
        int count = 0;
        for(Answer answer : user.getAnswers()){
            if(answer.getResult().getIsSuccess()){
                count++;
            }
        }
        map.put("correct", count);
        map.put("incorrect", user.getAnswers().size() - count);
        map.put("user_name", user.getName());
        map.put("user_id", user.getloginId());

        modelAndView.addObject("messages", map);
        modelAndView.setViewName("mypage");
        return modelAndView;
    }

    @RequestMapping(value = "/myPage/setting", method = RequestMethod.GET)
    public ModelAndView showSetting(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("rediret:/notice");
            return modelAndView;
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("name", user.getName());
            map.put("email", user.getEmail());
            modelAndView.addObject("messages", map);
            modelAndView.setViewName("settinglist");
            return modelAndView;
        }
=======
    @RequestMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage");
>>>>>>> 766a9f6... [수정] 로그인 권한 수정 완료 (홈화면 제외)
    }

    @RequestMapping(value = "/myPage/setting", method = RequestMethod.POST)
    public ModelAndView modifyinfo(ModelAndView modelAndView, HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("loginUserInfo");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        user.setName(name);
        user.setEmail(email);
        modelAndView.setViewName("redirect:/myPage/setting");
        return modelAndView;
    }
}
