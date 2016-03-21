package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public class TestResult {

    @SerializedName("testCase")
    private TestCase testCase;

    @SerializedName("result_type")
    private ResultType resultType;

    @SerializedName("error_message")
    private String errorMessage;

    public TestResult(TestCase testCase) {
        this.testCase = testCase;
        this.resultType = ResultType.UNKNOWN;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
