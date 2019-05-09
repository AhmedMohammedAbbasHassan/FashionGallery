package com.example.fashiongallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = this.getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String email = prefs.getString("userEmail", "");


        if (email.equals("") || email == null) {

            Intent intent = new Intent(getApplicationContext(),
                    LoginActivity.class);
            startActivity(intent);
            finish();


        } else {

            Intent intent = new Intent(getApplicationContext(),
                    HomeActivity.class);
            startActivity(intent);
            finish();


        }


    }
}
