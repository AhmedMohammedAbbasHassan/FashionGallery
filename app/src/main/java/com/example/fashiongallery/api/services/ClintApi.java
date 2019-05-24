package com.example.fashiongallery.api.services;


import android.support.annotation.Nullable;

import com.example.fashiongallery.responses.LoginResponse;
import com.example.fashiongallery.responses.ModelResponse;
import com.example.fashiongallery.responses.ResponseInfo;
import com.example.fashiongallery.responses.ServerResponse;
import com.example.fashiongallery.responses.SignupResponse;
import com.example.fashiongallery.responses.UpdateUserResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @POST("uploadModel.php/")
    Call<ResponseInfo> upload(@Part MultipartBody.Part img, @Part("name") RequestBody name, @Part("price") RequestBody price, @Part("desc") RequestBody desc, @Part("mCategory") RequestBody mCategory, @Part("sCategory") RequestBody sCategory, @Part("userId") RequestBody userId);
    @FormUrlEncoded
    @POST("getModels.php/")
    Call<ModelResponse>getModel( @Field("mCategory") String mCategory , @Field("sCategory") String sCategory ,@Field("userId") String userId);

    @FormUrlEncoded
    @POST("likeApi.php/")
    Call<ResponseInfo>doLike( @Field("user_id") String userId , @Field("model_id") String modelId ,@Field("likeOrDis") String likeOrDis);


    @Multipart
    @POST("updateUserInf.php/")
    Call<UpdateUserResponse> updateUser( @Part MultipartBody.Part img, @Part("userId") RequestBody userId, @Part("userEmail") RequestBody userEmail, @Part("userName") RequestBody userName, @Part("userPassword") RequestBody userPassword, @Part("userPhone") RequestBody userPhone, @Part("userLocation") RequestBody userLocation);
    @FormUrlEncoded
    @POST("updateUserWithoutImg.php/")
    Call<UpdateUserResponse> updateUserWithOutImg( @Field("userId") String userId, @Field("userEmail") String userEmail, @Field("userName") String userName, @Field("userPassword") String userPassword, @Field("userPhone") String userPhone, @Field("userLocation") String userLocation);

    @FormUrlEncoded
    @POST("getMyGallery.php/")
    Call<ModelResponse> getMyGallery( @Field("userId") String userId);

}
