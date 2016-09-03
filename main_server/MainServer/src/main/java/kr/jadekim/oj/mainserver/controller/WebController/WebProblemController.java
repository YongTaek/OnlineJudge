package kr.jadekim.oj.mainserver.controller.WebController;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.jadekim.oj.mainserver.config.Setting;
import kr.jadekim.oj.mainserver.entity.*;
import kr.jadekim.oj.mainserver.repository.ProblemRepository;
import kr.jadekim.oj.mainserver.repository.TestcaseRepository;
import kr.jadekim.oj.mainserver.service.AnswerService;
import kr.jadekim.oj.mainserver.service.ProblemService;
import kr.jadekim.oj.mainserver.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
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
    AnswerService answerService;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    TestcaseRepository testcaseRepository;

    Gson gson = new GsonBuilder().create();

    @RequestMapping("{id}")
    public ModelAndView problem( ModelAndView modelAndView, @PathVariable("id") int problem_id, Authentication authentication) {
        CurrentUser currentUser = null;
        if (authentication != null) {
            currentUser = (CurrentUser) authentication.getPrincipal();
        }
        Problem problem;
        Map<String, Object> messages = new HashMap<>();
        User loginUser = null;
        if (currentUser != null) {
            loginUser = currentUser.getUser();
        }
        try {
            problem = problemService.getProblem(problem_id).get();
            int submit = answerService.countByProblemId(problem_id).get();
            int success_count = answerService.countSuccessByProblemId(problem_id).get();

            GradeResult label;
            if (loginUser != null) {
                label = answerService.findIsSuccessByUserId(loginUser.getId(), problem_id).get();
                try {
                    messages.put("label", label.getIsSuccess());
                } catch (NullPointerException e) {
                }

            }
            int success_user_count = 0;
            Testcase testcase = testcaseRepository.findVisibleTestcaseByProbId(problem_id);
            messages.put("num",problem_id);
            messages.put("title",problem.getName());
            messages.put("content",problem.getContent());
            messages.put("inputData",testcase.getInput());
            messages.put("outputData",testcase.getOutput());
            messages.put("time",problem.getLimitTime());
            messages.put("memory",problem.getLimitMemory());
            messages.put("submit",submit);
            messages.put("success_count",success_count);
            messages.put("success_user_count",success_user_count);
            if(submit == 0){
                messages.put("rate", 0);
            }else {
                messages.put("rate", success_count / submit * 100);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("problem");
        modelAndView.addObject("messages", messages);

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView submitAnswer(HttpServletRequest request, ModelAndView modelAndView, Authentication authentication) {
        Problem problem;
        String content;
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        try {
            problem = problemService.getProblem(Integer.parseInt(request.getParameter("problem_id"))).get();
            content = request.getParameter("code");

            Answer answer = new Answer(loginUser, content, new Date(), problem);
            answerService.saveAnswer(answer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "submit/{id}", method = RequestMethod.GET)
    public ModelAndView submitAnswerForm(@PathVariable("id") int problem_id, ModelAndView modelAndView) {
        Problem problem;
        try {
            problem = problemService.getProblem(problem_id).get();
            modelAndView.setViewName("submitAnswer");
            modelAndView.addObject("problem", problem);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return modelAndView;

    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateProblem(ModelAndView modelAndView, Authentication authentication){
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        User loginUser = currentUser.getUser();
        if(loginUser == null){
            modelAndView.setViewName("redirect:/problem/list");
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", loginUser.getloginId());
            map.put("user_name", loginUser.getName());
            modelAndView.addObject("messages", map);
            modelAndView.setViewName("problemCreate");
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createProblem(ModelAndView modelAndView, HttpServletRequest request){
        String title = request.getParameter("problem_title");
        String contents = request.getParameter("problem-contents");
        int time_limit = Integer.valueOf(request.getParameter("problem_timeLimit"));
        int memory_limit = Integer.valueOf(request.getParameter("problem_memoryLimit"));
        String visibleInput = request.getParameter("problem_visibleInput");
        String visibleOutput = request.getParameter("problem_visibleOutput");
        int testcase_count = Integer.parseInt(request.getParameter("testcase_count"));
        Problem problem = new Problem(title, contents, 0);
        problem.setLimitMemory(memory_limit);
        problem.setLimitTime(time_limit);
        problemRepository.save(problem);
        for(int i =0;i<testcase_count;++i){
            String testInput = request.getParameter("testInput"+i);
            String testOutput = request.getParameter("testOutput"+i);
            Testcase testcase = new Testcase(problem,testInput,testOutput);
            testcase.setCanVisible(false);
            testcaseRepository.save(testcase);
        }
        Testcase testcase = new Testcase(problem, visibleInput, visibleOutput);
        testcase.setCanVisible(true);
        testcaseRepository.save(testcase);
        problem.getTestcases().add(testcase);
        problemRepository.save(problem);
        modelAndView.setViewName("redirect:/problem/list");
        return modelAndView;
    }

    public SimpleResult sendPost(String path,Map<String,Object> params) throws Exception {
        String url = Setting.EvaluationServer + path;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        String urlParameters = "";
        int count =0;
        for(String key : params.keySet()) {
            urlParameters += key + "=" + params.get(key);
            count++;
            if(count < params.keySet().size()) {
                urlParameters +="&";
            }
        }
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response);

        SimpleResult simpleResult = gson.fromJson(response.toString(),SimpleResult.class);
        return simpleResult;
    }
}
