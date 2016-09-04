package kr.jadekim.oj.evaluation.controllers;

import kr.jadekim.oj.evaluation.ApiConstants;
import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.database.ProblemService;
import kr.jadekim.oj.evaluation.evaluator.Evaluator;
import kr.jadekim.oj.evaluation.evaluator.SubmissionQueue;
import kr.jadekim.oj.evaluation.models.*;
import kr.jadekim.oj.evaluation.network.Api;
import kr.jadekim.oj.evaluation.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
@org.springframework.stereotype.Controller
@RequestMapping(ApiConstants.BASE_URL+"/evaluation")
public class EvaluationController {

    @Autowired
    ProblemService problemService;

    public static volatile List<Evaluator> evaluators = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody SimpleResult submit(@RequestParam(value = "submitId", defaultValue = "-1") long id,
                        @RequestParam(value = "language") String language,
                        @RequestParam(value = "code") String code,
                        @RequestParam(value = "problemId") int problemId) {
        if (id <= 0) {
            return new SimpleResult(false, "Invalid `id` value");
        }
        if (language.length() <= 0) {
            return new SimpleResult(false, "Invalid `language` value");
        }
        System.out.println(language);
        try {
            Language.valueOf(language);
        } catch (IllegalArgumentException e) {
            String message = "language value is only ";
            for (Language langEnum : Language.values()) {
                message += langEnum.name();
            }
            return new SimpleResult(false, message);
        }
        if (code.length() <= 0) {
            return new SimpleResult(false, "Invalid `code` value");
        }
        try {
            SubmissionQueue.getInstance().add(createSubmission(id, Language.valueOf(language),code,problemService.findProblemById(problemId)));
            setEvaluators();
            return new SimpleResult(true, Integer.toString(SubmissionQueue.getInstance().count()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleResult(false,"fail to evaluate answer");
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public SimpleResult connect(@RequestParam(value = "api_url", defaultValue = "http://localhost") String apiUrl) {
        ApiConstants.API_URL = apiUrl;
        return new SimpleResult(true, "connected");
    }

    protected void endEvaluate(GradeResult result) {
        Api.getInstance().endEvaluate(result);
    }

//    protected Problem getProblem(int problemId) {
//        Problem problem = problemRepository.findById(problemId);
//
//        if (problem == null) {
//            problem = BlockingObservable.from(Api.getInstance().getProblem(problemId)).first();
//            problemRepository.save(problem);
//        }
//        return problem;
//    }

    protected Submission createSubmission(long id, Language language, String code, Problem problem) {
        Submission submission = new Submission(id, language, code);
        System.out.println("submission object create");
        submission.setProblem(problem);
        return submission;
    }

    private void setEvaluators() {
        for (int i=evaluators.size(); i<Setting.get().EVALUATOR_COUNT; i++) {
            Evaluator evaluator = Evaluator.newInstance();
            evaluators.add(evaluator);
            evaluator.start().subscribe(this::endEvaluate, Logger::error, () -> {
                evaluators.remove(evaluator);
            });
        }
    }
}