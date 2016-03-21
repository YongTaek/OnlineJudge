package kr.jadekim.oj.mainserver.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import kr.jadekim.oj.mainserver.config.Setting;
import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.UserRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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


    @RequestMapping(value = "/api/v1/sendGrade", method = RequestMethod.POST)
    public
    @ResponseBody
    String gradeCode(String user_id, int problem_id, String code, String time, String lang) {
        Gson gson = new GsonBuilder().create();

        User user;
        Problem problem;
        Date submitTime;
        Answer answer;
        try {
            submitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            user = userRepository.findByloginId(user_id).get(0);
            problem = problemRepository.findOne(problem_id);
            answer = new Answer(user,code,submitTime,problem);
            answer = answerService.saveAnswer(answer).get();
            RestTemplate restTemplate = new RestTemplate();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("lang", lang);
            hashMap.put("code", code);
            hashMap.put("submitId", answer.getId());
            hashMap.put("problemId", problem_id);
            JsonObject message = restTemplate.postForObject(Setting.EvaluationServer + "/api/v1/evaluation", hashMap, JsonObject.class);
            if (message.get("success").getAsBoolean()) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            if (user == null || problem == null || answer == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            return "{ \"success\" : false , \"message\":\"\"}";
        }


        return "test";


    }
}
