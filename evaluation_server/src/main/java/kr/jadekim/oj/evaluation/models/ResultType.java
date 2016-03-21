package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public enum ResultType {

    @SerializedName("success")
    SUCCESS("success"),

    @SerializedName("oom")
    OOM("oom"),

    @SerializedName("timeout")
    TIMEOUT("timeout"),

    @SerializedName("wrong_answer")
    WRONG_ANSWER("wrong_answer"),

    @SerializedName("unknown")
    UNKNOWN("unknown"),

    @SerializedName("non_zero_exitcode")
    NON_ZERO_EXITCODE("non_zero_exitcode");

    @SerializedName("result_type")
    private String type;

    ResultType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}