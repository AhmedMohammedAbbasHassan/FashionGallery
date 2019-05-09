package com.example.fashiongallery.api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmedmike on 10/1/2017.
 */
public class Connection {
    String baseUrl = "http://192.168.1.4:8082/fashionGallery/";


    static Retrofit.Builder builder;
    private  Connection() {
          this.builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

    }

    public  static Retrofit.Builder instance(){

        if(builder ==null){
            new Connection();
        }
        return builder;
    }



}
