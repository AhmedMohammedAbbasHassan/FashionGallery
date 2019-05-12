package com.example.fashiongallery.responses;

import com.google.gson.annotations.SerializedName;

public class ResponseInfo {

@SerializedName("error")
String error ;

    public String getError() {
        return error;
    }
}
