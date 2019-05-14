package com.example.fashiongallery.responses;

import com.google.gson.annotations.SerializedName;

public class ResponseInfo {

    @SerializedName("code")
    int code ;

    public int getCode() {
        return code;
    }
}

