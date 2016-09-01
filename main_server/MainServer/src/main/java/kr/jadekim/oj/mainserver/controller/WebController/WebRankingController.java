package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.CurrentUser;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 8. 28..
 */
/**
 * Modified by cheonyujung on 2016. 8. 29..
 */

@Controller
@RequestMapping("/ranking")
public class WebRankingController {
    @Autowired
    UserService userService;

    @RequestMapping
    public ModelAndView userRanking(ModelAndView modelAndView, Authentication authentication, @PageableDefault(sort = {"success_count"}, size = 10)Pageable pageable){
        modelAndView.setViewName("rankingUser");
        User user = null;

        try {
            List<User> userList = userService.findUserOrderBySuccessCount(pageable).get();
            if(authentication != null ) {
                user = ((CurrentUser)authentication.getPrincipal()).getUser();
                int userRanking = userList.indexOf(user);
                if(userRanking != -1) {
                    modelAndView.addObject("loginUserRank",userRanking);
                }
                modelAndView.addObject("messages",userList);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
