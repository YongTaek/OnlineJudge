package kr.jadekim.oj.mainserver.controller.WebController;


import kr.jadekim.oj.mainserver.entity.Answer;

import kr.jadekim.oj.mainserver.entity.GradeResult;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.Testcase;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.TestcaseRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
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
    AnswerService answerService;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    TestcaseRepository testcaseRepository;

    @RequestMapping("{id}")
    public ModelAndView problem(ModelAndView modelAndView, @PathVariable("id") int problem_id,HttpSession session){
        Problem problem;
        System.out.println(problem_id);
        Map<String,Object> messages = new HashMap<>();
        User user;
        try {
            user = (User) session.getAttribute("loginUserInfo");
            problem = problemService.getProblem(problem_id).get();
            System.out.println(problem);
            int submit = answerService.countByProblemId(problem_id).get();
            int success_count = answerService.countSuccessByProblemId(problem_id).get();

            GradeResult label;
            if(user!=null) {
                label = answerService.findIsSuccessByUserId(user.getId(), problem_id).get();
                modelAndView.addObject("loginUser",user);
                System.out.println(user.getLoginId());

                try {
                    messages.put("label",label.getIsSuccess());
                }catch (NullPointerException e){
                }

            }
            int success_user_count = answerService.countUserByProblemId(problem_id).get();
            messages.put("num",problem_id);
            messages.put("title",problem.getName());

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

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public ModelAndView submitAnswer(HttpServletRequest request, HttpSession session, ModelAndView modelAndView){
        Problem problem;
        User loginUser;
        String content;

        try {
            problem = problemService.getProblem(Integer.parseInt(request.getParameter("problem_id"))).get();
            loginUser = (User) session.getAttribute("loginUserInfo");
            content = request.getParameter("code");
            Answer answer = new Answer(loginUser,content,new Date(),problem);
            answerService.saveAnswer(answer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }
    @RequestMapping(value = "submit/{id}",method = RequestMethod.GET)
    public ModelAndView submitAnswerForm(@PathVariable("id")int problem_id,HttpSession session,ModelAndView modelAndView){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user==null){
            modelAndView.setViewName("redirect:/problem/"+problem_id);
            return modelAndView;
        }else {
            Problem problem;
            try {
                problem = problemService.getProblem(problem_id).get();
                modelAndView.setViewName("submitAnswer");
                modelAndView.addObject("problem",problem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return modelAndView;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView ShowCreateProblem(ModelAndView modelAndView, HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");
        if(user == null){
            modelAndView.setViewName("redirect:/problem");
        }else{
            modelAndView.setViewName("problemCreate");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView CreateProblem(ModelAndView modelAndView, HttpServletRequest request, HttpSession session){
        String title = request.getParameter("problem_title");
        String contents = request.getParameter("problem_contents");
        int time_limit = Integer.valueOf(request.getParameter("problem_timeLimit"));
        int memory_limit = Integer.valueOf(request.getParameter("problem_memoryLimit"));
        String visibleInput = request.getParameter("problem_visibleInput");
        String visibleOutput = request.getParameter("problem_visibleOutput");
        Problem problem = new Problem(title, contents, 0);
        problem.setLimitMemory(memory_limit);
        problem.setLimitTime(time_limit);
        Testcase testcase = new Testcase(problem, visibleInput, visibleOutput);
        problemRepository.save(problem);
        testcaseRepository.save(testcase);
        problem.getTestcases().add(testcase);
        problemRepository.save(problem);
        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }


}
