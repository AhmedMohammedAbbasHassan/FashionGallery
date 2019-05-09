package com.example.fashiongallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String userName  = prefs.getString("userEmail", "");
        editText  = (TextView) findViewById(R.id.txt);
        editText.setText(userName);


    }






}
