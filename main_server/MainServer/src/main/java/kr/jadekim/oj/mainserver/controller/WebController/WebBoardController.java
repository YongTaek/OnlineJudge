package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Post;
import kr.jadekim.oj.mainserver.repository.PostRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 2. 24..
 */

@RequestMapping("/board")
public class WebBoardController {

    @Async
    public PostRepository postRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    @RequestMapping("/notice")
    public ModelAndView notice(ModelAndView modelAndView){
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Post> posts = postRepository.findAll();
        for(Post p : posts){
            Map<String,Object> map = new HashMap<>();
            int num = p.getId();
            String name = p.getTitle();
            String user = p.getAuthor().getName();
            String date = simpleDateFormat.format(p.getTime());
            map.put("number",num);
            map.put("user",user);
            map.put("date",date);
            map.put("title",name);
            messages.add(map);
        }
        modelAndView.addObject("messages",messages);
        return modelAndView;
    }
}
