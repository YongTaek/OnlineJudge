package kr.jadekim.oj.mainserver.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.entity.Testcase;
import kr.jadekim.oj.mainserver.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Controller
public class ProblemController {


    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/api/v1/testcase",method = RequestMethod.GET)
    public @ResponseBody String getTestcase(int problem_id) {
        Gson gson = new GsonBuilder().create();
        List<Testcase> testcaseList = null;
        try {
            testcaseList = problemService.getTestcase(problem_id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("problem_id",problem_id);
        map.put("testcase",testcaseList);
        return gson.toJson(map);
    }
}
