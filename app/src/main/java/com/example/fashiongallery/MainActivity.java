package com.example.fashiongallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashiongallery.fragments.AddModelFragment;
import com.theartofdev.edmodo.cropper.CropImage;


public class MainActivity extends AppCompatActivity {


    TextView editText;
    ImageView imageView;

    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        SharedPreferences prefs = this.getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);
        String userName  = prefs.getString("userEmail", "");
        editText  = (TextView) findViewById(R.id.txt);
        editText.setText(userName);
*/


        imageView  = (ImageView)findViewById(R.id.img_test);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(MainActivity.this);

            }
        });




    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(AppController.getContext(), "in on activity result  ", Toast.LENGTH_SHORT).show();


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {

            CropImage.ActivityResult result  = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            imageView.setImageURI(imageUri);

        }else {


            Toast.makeText(AppController.getContext(), "Can not crop this image..!", Toast.LENGTH_SHORT).show();



        }



    }







}
