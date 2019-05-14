package com.example.fashiongallery.responses;

import com.example.fashiongallery.api.model.Model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResponse {

    @SerializedName("code")
  private  String code ;
    @SerializedName("models")
  private List<Model> models ;

    public String getCode() {
        return code;
    }

    public List<Model> getModels() {
        return models;
    }
}
