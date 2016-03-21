package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public class SimpleResult {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public SimpleResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
