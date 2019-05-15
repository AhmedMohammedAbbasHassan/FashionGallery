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
