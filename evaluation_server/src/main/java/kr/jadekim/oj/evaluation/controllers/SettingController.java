package kr.jadekim.oj.evaluation.controllers;

import kr.jadekim.oj.evaluation.ApiConstants;
import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.evaluator.SubmissionQueue;
import kr.jadekim.oj.evaluation.models.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
@org.springframework.stereotype.Controller
@RequestMapping(ApiConstants.BASE_URL+"/setting")
public class SettingController {

    @RequestMapping(value = "/evaluator/count", method = RequestMethod.GET)
    public SimpleResult getEvaluatorCount() {
        return new SimpleResult(true, Integer.toString(SubmissionQueue.getInstance().count()));
    }

    @RequestMapping(value = "/evaluator/count", method = RequestMethod.PUT)
    public SimpleResult setEvaluatorCount(@RequestParam(value = "count") int count) {
        Setting.get().EVALUATOR_COUNT = count;
        return new SimpleResult(true, Integer.toString(Setting.get().EVALUATOR_COUNT));
    }
}