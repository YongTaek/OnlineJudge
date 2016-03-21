package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public class Submission {

    @SerializedName("id")
    private long id;

    @SerializedName("problem_id")
    private int problemId;

    private Problem problem;

    @SerializedName("language")
    private Language language;

    @SerializedName("code")
    private String code;

    public Submission(long id, Language language, String code) {
        this.id = id;
        this.language = language;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public int getProblemId() {
        return problemId;
    }

    public Problem getProblem() {
        return problem;
    }

    public Language getLanguage() {
        return language;
    }

    public String getCode() {
        return code;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
