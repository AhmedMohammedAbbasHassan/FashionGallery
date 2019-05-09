package com.example.fashiongallery.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fashiongallery.AppController;

public class SharedPreferenceUtils {





  public static void saveUserDate(String id,String email,String userName,String gender )

    {
        SharedPreferences sharedPreferences = AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", id);
        editor.putString("userEmail", email);
        editor.putString("userName", userName);
        editor.putString("userGender", gender);
        editor.commit();
    }





}
