package com.example.fashiongallery.responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("code")
    private int code ;
    @SerializedName("id")
    private String id ;
    @SerializedName("email")
    private String email ;
    @SerializedName("userName")
    private String userName ;
    @SerializedName("gender")
    private String gender  ;
    @SerializedName("phone")
    private String phone   ;

    public int getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }
}
