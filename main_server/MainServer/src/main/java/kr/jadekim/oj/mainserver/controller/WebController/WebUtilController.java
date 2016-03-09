package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ohyongtaek on 2016. 3. 4..
 */

@Controller
@SessionAttributes("loginUserInfo")
public class WebUtilController {

    Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "/userLogout",method = RequestMethod.GET)
    public java.lang.String logout(SessionStatus sessionStatus){

        sessionStatus.setComplete();

        return "redirect:/problem/list";

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView){
        String login_id = request.getParameter("login_id");
        String login_pw = request.getParameter("login_pw");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("login_id",login_id);
        map.add("login_pw",login_pw);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/api/v1/login",map, String.class);
        User user = gson.fromJson(result,User.class);
        if(user.getId() !=-1) {
            request.getSession().setAttribute("loginUserInfo",user);
            modelAndView.addObject("loginUserInfo",user);
            modelAndView.setViewName("redirect:/problem/list");
        }
        return modelAndView;
    }





}
