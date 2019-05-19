package com.example.fashiongallery.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fashiongallery.AppController;

public class SharedPreferenceUtils {





  public static void saveUserDate(String id,String email,String userName,String gender,String phone,String location , String userImg )

    {
        SharedPreferences sharedPreferences = AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", id);
        editor.putString("userEmail", email);
        editor.putString("userName", userName);
        editor.putString("userGender", gender);
        editor.putString("userPhone", phone);
        editor.putString("userLocation", location);
        editor.putString("userImg", userImg);
        editor.commit();
    }

   public static String getUserId(){


        SharedPreferences prefs = AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String userId  = prefs.getString("userId", "");

        return userId;
    }




   public static String getUserNameFromPref(){


        SharedPreferences prefs =  AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String userName  = prefs.getString("userName", "");

        return userName;
    }



    public static String getUserEmailFromPref(){


        SharedPreferences prefs =  AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String email  = prefs.getString("userEmail", "");

        return email;
    }




    public static String getUserPhoneFromPref(){


        SharedPreferences prefs =  AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String phone  = prefs.getString("userPhone", "");

        return phone;
    }


    public static String getUserLocationFromPref(){


        SharedPreferences prefs =  AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String location  = prefs.getString("userLocation", "");

        return location;
    }

    public static String getUserImgFromPref(){


        SharedPreferences prefs =  AppController.getContext().getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String img  = prefs.getString("userImg", "");

        return img;
    }


}
