package kr.jadekim.oj.mainserver.controller.WebController;

import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 3. 4..
 */
@Controller
@RequestMapping("/problem")
public class WebProblemController {

    @Autowired
    ProblemService problemService;

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @RequestMapping("{id}")
    public ModelAndView problem(ModelAndView modelAndView, @PathVariable("id") int problem_id){
        Problem problem;
        System.out.println(problem_id);
        Map<String,Object> messages = new HashMap<>();
        try {
            User user = userService.findUser("ka123ak").get();
            problem = problemService.getProblem(problem_id).get();
            System.out.println(problem);
            int submit = answerService.countByProblemId(problem_id).get();
            int success_count = answerService.countSuccessByProblemId(problem_id).get();
            boolean label = answerService.findIsSuccessByUserId(user.getId(),problem_id).get();
            int success_user_count = answerService.countUserByProblemId(problem_id).get();
            messages.put("num",problem_id);
            messages.put("title",problem.getName());
            messages.put("label",label);
            messages.put("time",problem.getLimitTime());
            messages.put("memory",problem.getLimitMemory());
            messages.put("submit",submit);
            messages.put("success_count",success_count);
            messages.put("success_user_count",success_user_count);
            messages.put("rate",success_count/submit*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("problem");
        modelAndView.addObject("messages",messages);
        return modelAndView;
    }
}
