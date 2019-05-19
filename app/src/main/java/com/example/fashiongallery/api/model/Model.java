package com.example.fashiongallery.api.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id")
   private String id ;
    @SerializedName("name")
    private String name ;
    @SerializedName("price")
    private String price  ;
    @SerializedName("description")
    private String description ;
    @SerializedName("mCategory")
    private String mCategory ;
    @SerializedName("sCategory")
    private String sCategory;
    @SerializedName("img_url")
    private String img_url  ;
    @SerializedName("user_id")
    private String user_id  ;
    @SerializedName("likes")
    private String likes;
    @SerializedName("favorites")
    private String favorites;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("user_img")
    private String user_img;
    @SerializedName("location")
    private String location;


    public String getUser_name() {
        return user_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUser_img() {
        return user_img;
    }

    public String getLocation() {
        return location;
    }

    public String getFavorites() {
        return favorites;
    }

    public String getLikes() {
        return likes;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getsCategory() {
        return sCategory;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getUser_id() {
        return user_id;
    }
}
