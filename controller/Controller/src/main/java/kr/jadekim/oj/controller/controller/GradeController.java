package kr.jadekim.oj.controller.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.controller.entity.Answer;
import kr.jadekim.oj.controller.entity.GradeResult;
import kr.jadekim.oj.controller.entity.Problem;
import kr.jadekim.oj.controller.entity.User;
import kr.jadekim.oj.controller.repository.GradeResultRepository;
import kr.jadekim.oj.controller.repository.ProblemRepository;
import kr.jadekim.oj.controller.repository.UserRepository;
import kr.jadekim.oj.controller.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Controller
public class GradeController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private GradeResultRepository gradeResultRepository;

    @RequestMapping(value = "/api/v1/grade",method = RequestMethod.POST)
    public @ResponseBody String gradeCode(String user_id, int problem_id, String code,String time){
        Gson gson = new GsonBuilder().create();

        User user;
        Problem problem;
        Date submitTime;
        Answer answer;
        GradeResult gradeResult;
        try {
            submitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            user = userRepository.findByloginId(user_id).get(0);
            problem = problemRepository.findOne(problem_id);
            answer  = answerService.saveAnswer(user,code,submitTime,problem).get();

            if(user == null || problem == null || answer == null){
                throw new Exception();
            }
        } catch (Exception e) {
            return "{ \"success\" : false , \"message\":\"\"}";
        }

        RestTemplate restTemplate = new RestTemplate();

        return "test";


    }
}
