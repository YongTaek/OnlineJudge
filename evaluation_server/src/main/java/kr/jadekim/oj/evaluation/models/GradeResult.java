package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jdekim43 on 2016. 1. 26..
 */
public class GradeResult {

    @SerializedName("id")
    private long id;

    @SerializedName("submission_id")
    private Submission submission;

    @SerializedName("is_success")
    private boolean isSuccess;

    @SerializedName("grade")
    private double grade;

    @SerializedName("tests")
    private List<TestResult> tests;

    @SerializedName("message")
    private String message;

    public GradeResult(Submission submission) {
        this.submission = submission;
    }

    public long getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<TestResult> getTests() {
        return tests;
    }

    public void setTests(List<TestResult> tests) {
        this.tests = tests;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}