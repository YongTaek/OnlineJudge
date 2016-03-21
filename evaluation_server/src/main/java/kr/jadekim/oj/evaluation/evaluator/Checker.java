package kr.jadekim.oj.evaluation.evaluator;

import kr.jadekim.oj.evaluation.utils.Logger;
import kr.jadekim.oj.evaluation.models.ResultType;
import kr.jadekim.oj.evaluation.models.TestCase;
import kr.jadekim.oj.evaluation.models.TestResult;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.io.IOException;

/**
 * Created by jdekim43 on 2016. 2. 13..
 */
public class Checker {

    private Runner runner;
    private TestCase testCase;
    private int limitTime;

    public Checker(String path, String[] command, TestCase testCase, int limitTime, int limitMemory) {
        runner = new Runner(path, command, limitMemory);
        this.testCase = testCase;
        this.limitTime = limitTime;
    }

    public TestResult run() {
        TestResult result = new TestResult(testCase);
        try {
            if (!runner.run(limitTime, testCase.getInput())) {
                result.setResultType(ResultType.TIMEOUT);
                result.setErrorMessage("Time out");
            } else if (runner.getResult().endsWith("Killed") || runner.getError().endsWith("Killed")) {
                result.setResultType(ResultType.OOM);
                result.setErrorMessage("Out Of Memory");
            } else if (runner.getExitValue() != 0) {
                result.setResultType(ResultType.NON_ZERO_EXITCODE);
                result.setErrorMessage("Non zero exitcode");
            } else if (!"".equals(runner.getError())) {
                result.setErrorMessage(runner.getError());
            } else if (!testCase.getOutput().equals(runner.getResult())) {
                result.setResultType(ResultType.WRONG_ANSWER);
                result.setErrorMessage("wrong answer");
            } else if (runner.getExitValue() == 0 && testCase.getOutput().equals(runner.getResult())) {
                result.setResultType(ResultType.SUCCESS);
            }
        } catch (IOException | InterruptedException e) {
            Logger.error(e);
            e.printStackTrace();
        }
        return result;
    }
}