package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 3. 5..
 */
@Controller
@SessionAttributes("loginUserInfo")
public class testController {

    @RequestMapping("/test/login")
    public ModelAndView testLogin(@ModelAttribute("loginUserInfo")User user, ModelAndView modelAndView){
        if(user == null){
            modelAndView.setViewName("problemList");
        }else{
            modelAndView.setViewName("problemRanking");
        }
        Map<String,Object> messages = new HashMap<>();
        modelAndView.addObject("messages",messages);
        return modelAndView;

    }
}
