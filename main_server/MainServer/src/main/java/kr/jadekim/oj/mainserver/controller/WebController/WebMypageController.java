package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheonyujung on 2016. 3. 21..
 */
public class WebMypageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/{id}")
    public ModelAndView ShowMypage(ModelAndView modelAndView, @PathVariable("id")String id){
        User user = (User) userRepository.findByloginId(id);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        map.put("user_group", user.getGroup().getName());

        return modelAndView;
    }
}
