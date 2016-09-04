package kr.jadekim.oj.mainserver.util;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ohyongtaek on 2016. 9. 3..
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
