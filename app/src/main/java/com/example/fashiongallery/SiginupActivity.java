package com.example.fashiongallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.SignupResponse;
import com.victor.loading.rotate.RotateLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SiginupActivity extends AppCompatActivity {


    private Button alreadyLoginButton,signupButton;
    private EditText userNameEditText ,emailEditText , passwordEditText , confirmPasswordEditText;
    private RadioGroup radioGroup ;
    private RotateLoading rotateLoading  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup);

        alreadyLoginButton = (Button)findViewById(R.id.login_btn_signup);
        signupButton       = (Button)findViewById(R.id.signup_btn_signuup);


        userNameEditText  = (EditText)findViewById(R.id.user_name_signin);
        emailEditText  = (EditText)findViewById(R.id.email_et_signup);
        passwordEditText  = (EditText)findViewById(R.id.password_signup);
        confirmPasswordEditText  = (EditText)findViewById(R.id.confirm_password);
        radioGroup           = (RadioGroup)findViewById(R.id.radioGroup);
        rotateLoading = (RotateLoading)findViewById(R.id.rotateloading);


    }


    void goToLogin(View view)
    {
        Intent intent = new Intent(SiginupActivity.this,LoginActivity.class);
        startActivity(intent);

    }




    void siginupAction(View view)
    {


        showLoading(true);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
      int gender ;
      
      if (radioGroup.getCheckedRadioButtonId() == R.id.radioMale)
      { gender = 1 ;
      }else{
          gender=2;
          }



        if ( validate(email,password,userName,confirmPassword))
        {

            Retrofit retrofit  = Connection.instance().build();
            ClintApi clint = retrofit.create(ClintApi.class);
            Call<SignupResponse> call  =clint.signupUser(email,userName,password,gender);
            call.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {


                    SignupResponse signupResponse = response.body();
                    
                    if (signupResponse.getCode() == 200)
                    {
                        showLoading(false);
                        Toast.makeText(SiginupActivity.this, " email is created ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SiginupActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else if(signupResponse.getCode() == 201){

                        showLoading(false);
                        Toast.makeText(SiginupActivity.this, "Please try with another email ", Toast.LENGTH_SHORT).show();
                        
                    }else{

                        showLoading(false);
                        Toast.makeText(SiginupActivity.this, "unexpected error", Toast.LENGTH_SHORT).show();
                        
                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable throwable) {

                    showLoading(false);
                    Toast.makeText(SiginupActivity.this, "Check your Connection", Toast.LENGTH_SHORT).show();

                }
            });

        }else {

            showLoading(false);
        }
    }



    // validate User Data
    public boolean validate(String email,String password,String userName,String confirmPassword) {
        boolean valid = true;



        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        if (userName.isEmpty() || password.length() < 4 || userName.length() > 10)
        {
            userNameEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false ;
        } else{

            userNameEditText.setError(null);
        }


        if ( !confirmPassword .equals(password))
        {
            confirmPasswordEditText.setError("please match your password");
            valid = false ;
        } else{

            confirmPasswordEditText.setError(null);
        }


        return valid;
    }


    void showLoading(boolean show)
    {
        if (show)
        {
            rotateLoading.start();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }else {

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            rotateLoading.stop();
        }

    }






}
