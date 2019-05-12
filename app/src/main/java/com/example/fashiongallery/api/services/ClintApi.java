package com.example.fashiongallery.api.services;


import com.example.fashiongallery.responses.LoginResponse;
import com.example.fashiongallery.responses.ResponseInfo;
import com.example.fashiongallery.responses.ServerResponse;
import com.example.fashiongallery.responses.SignupResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by ahmedmike on 9/30/2017.
 */
public interface ClintApi {

    @FormUrlEncoded
    @POST("insertUser.php/")
    Call<SignupResponse> signupUser(@Field("email") String email, @Field("userName") String userName, @Field("password") String password, @Field("gender") int gender);


    @FormUrlEncoded
    @POST("userLogin.php/")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("password") String password);


    @Multipart
    @POST("uploadTest.php/")
    Call<ResponseInfo> upload(@Part MultipartBody.Part img, @Part("name") RequestBody name);




}
