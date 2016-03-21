package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ohyongtaek on 2016. 3. 4..
 */

@Controller
@SessionAttributes("loginUserInfo")
public class WebUtilController {

    Gson gson = new GsonBuilder().create();

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userLogout",method = RequestMethod.GET)
    public java.lang.String logout(SessionStatus sessionStatus){

        sessionStatus.setComplete();

        return "redirect:/problem/list";

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView){
        String login_id = request.getParameter("login_id");
        String login_pw = request.getParameter("login_pw");
        User user = null;
        try {
            user = userService.login(login_id,login_pw).get();
            if(user !=null) {
                request.getSession().setAttribute("loginUserInfo",user);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }





}
