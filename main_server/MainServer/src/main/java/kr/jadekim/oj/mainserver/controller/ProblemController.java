package kr.jadekim.oj.mainserver.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Controller
@RequestMapping("/api/v1/problem")
public class ProblemController {


    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public @ResponseBody String getProblem(@PathVariable("id") int problem_id) {
        Gson gson = new GsonBuilder().create();
        Problem problem = null;
        try {
            problem = problemService.getProblem(problem_id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("problem_id",problem_id);
        map.put("testcase",problem.getTestcases());
        map.put("limitTime",problem.getLimitTime());
        map.put("limitMemory",problem.getLimitMemory());

        return gson.toJson(map);
    }
}
