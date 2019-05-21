package com.example.fashiongallery.responses;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {


    @SerializedName("code")
    int code ;
    @SerializedName("updatedImgUrl")
    String updatedImgUrl ;

    public String getUpdatedImgUrl() {
        return updatedImgUrl;
    }

    public int getCode() {
        return code;
    }


}
