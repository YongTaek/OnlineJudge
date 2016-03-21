package kr.jadekim.oj.mainserver.controller.WebController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.GradeResult;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.AnswerRepository;
import kr.jadekim.oj.mainserver.repository.GradeResultRepository;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by ohyongtaek on 2016. 2. 15..
 */

@Controller
@SessionAttributes("loginUserInfo")
@RequestMapping("/problem")
public class WebProblemListController {

    Gson gson = new GsonBuilder().create();

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    GradeResultRepository gradeResultRepository;

    @Autowired
    ProblemService problemService;

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @RequestMapping("/test")
    public @ResponseBody String createAnswer(){
        User user = null;
        try {
            user = userService.findUser("ka123ak1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        Problem problem = new Problem("aasd","내용",0);
        problem.getSubmitUsers().add(user);
        problemRepository.save(problem);
        Answer answer = new Answer(user,"asdf",date,problem);
        GradeResult gradeResult = new GradeResult();
        gradeResult.setSuccess(true);
        gradeResultRepository.save(gradeResult);
        answer.setResult(gradeResult);
        answerRepository.save(answer);
        user.addAnswer(answer);
        userService.saveUser(user);


        int count = answerRepository.countBySuccessAndProblemId(1);
        return count+"";
    }


    @RequestMapping("/query")
    public @ResponseBody  String getUser(){
        List<Problem> problems = problemRepository.findByName("test");
        User user = userRepository.findByloginId("ka123ak1").get(0);
        for(Problem p : problems){
            p.getSubmitUsers().add(user);
            problemRepository.save(p);
        }

        return "wait";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView , @PageableDefault(sort = { "id" }, size = 10) Pageable pageable,HttpSession session){
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = null;
        User user = null;
        int total_count = 0;
        try {
            problems = problemService.findAllProblem(pageable).get();
            user = (User) session.getAttribute("loginUserInfo");
            total_count = problemService.countAllProblem().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        messages = makeMessages(messages,problems,user);
        ArrayList<Integer> pages = new ArrayList<>();

        if(total_count%pageable.getPageSize()==0){
            for(int i=0;i<total_count/pageable.getPageSize();++i){
                pages.add(i+1);
            }
        }else{
            for(int i=0;i<total_count/pageable.getPageSize()+1;++i){
                pages.add(i+1);
            }
        }

        modelAndView.setViewName("problemList");
        if(user!=null){
            modelAndView.addObject("loginUser",user);
            System.out.println(user.getLoginId());
        }
        modelAndView.addObject("messages",messages);
        modelAndView.addObject("pages",pages);
        return modelAndView;
    }
    @RequestMapping("recent")
    public ModelAndView recentList(ModelAndView modelAndView,HttpSession session){

        User loginUser = (User) session.getAttribute("loginUserInfo");
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = problemService.findProblemRecent();
        messages = makeMessages(messages,problems,loginUser);
        ArrayList<Integer> pages = new ArrayList<>();
        modelAndView.setViewName("problemList");
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("pages", pages);
        if(loginUser!=null){
            modelAndView.addObject("loginUser",loginUser);
            System.out.println(loginUser.getLoginId());
        }

        return modelAndView;
    }

    @RequestMapping("ranking")
    public ModelAndView probRanking(ModelAndView modelAndView,HttpSession session){
        User user = (User) session.getAttribute("loginUserInfo");

        try {
            modelAndView = problemService.getSortedProbByrank(modelAndView, user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(user!=null){
            modelAndView.addObject("loginUser",user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request,HttpSession session,ModelAndView modelAndView){

        String problem_name = request.getParameter("search");
        ArrayList<Map> messages = new ArrayList<>();
        Iterable<Problem> problems = null;
        try {
            problems = problemService.findByName(problem_name).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        User loginUser = (User) session.getAttribute("loginUserInfo");
        messages = makeMessages(messages,problems,loginUser);

        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("problemList");
        return modelAndView;
    }

    public ArrayList<Map> makeMessages(ArrayList<Map> messages,Iterable<Problem> problems,User user){
        for(Problem p : problems){
            Map<String,Object> map = new HashMap<>();
            int success_count = answerRepository.countBySuccessAndProblemId(p.getId());
            int total_count = answerRepository.countByProblemId(p.getId());
            GradeResult isSuccess;
            double rate;
            if(total_count == 0){
                rate = 0;
            }else {
                rate = success_count / total_count * 100;
            }
            if(user != null) {
                isSuccess = answerRepository.findIsSuccessTop1ByUserId(user.getId(), p.getId());
                try {
                    map.put("result", isSuccess.getIsSuccess());
                }catch (NullPointerException e){
                }
            }
            map.put("id",p.getId());
            map.put("name",p.getName());
            map.put("count",success_count);
            map.put("rate",rate);
            messages.add(map);
        }
        return messages;
    }
}
