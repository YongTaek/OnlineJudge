package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 3. 29..
 */
@Controller
public class WebUserController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/login?logout", method = RequestMethod.GET)
    public java.lang.String logout(SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "redirect:/problem/list";

    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage");
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
}
