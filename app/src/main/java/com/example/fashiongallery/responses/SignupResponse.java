package com.example.fashiongallery.responses;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {


@SerializedName("code")
private int code;
@SerializedName("msg")
private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
