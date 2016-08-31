package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.Question;
import kr.jadekim.oj.mainserver.entity.Ranking;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.RankingRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheonyujung on 2016. 8. 29..
 */

@Controller
@SessionAttributes("loginUserInfo")
@RequestMapping("/ranking")
public class WebRankingController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RankingRepository rankingRepository;

    @RequestMapping("/user")
    public ModelAndView showQuestion(ModelAndView modelAndView, @PageableDefault(sort = {"id"}, size = 25) Pageable pageable, Authentication authentication) {
        CurrentUser currentUser= null;
        if(authentication!=null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Ranking> rankingUser = rankingRepository.findAll(pageable);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        User loginUser = null;
        if(currentUser!=null) {
            loginUser = currentUser.getUser();
        }
        for (Ranking r : rankingUser) {
            Map<String, Object> map = new HashMap<>();
//            int num = r.getId();
//            String name = p.getPost().getTitle();
//            String user = p.getPost().getAuthor().getName();
//            String date = simpleDateFormat.format(p.getPost().getTime());
//            int  quest = p.getProblem().getId();
//            map.put("number", num);
//            map.put("user", user);
//            map.put("date", date);
//            map.put("title", name);
//            map.put("question", quest);
//            if(loginUser!= null && p.getPost().getAuthor().getId() == loginUser.getId()){
//                map.put("canModify",true);
//                map.put("canDelete",true);
//            }
//            messages.add(map);
        }
        if(loginUser!=null) {
            modelAndView.addObject("loginUser", loginUser);
        }
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("rankingUser");
        return modelAndView;
    }
}
