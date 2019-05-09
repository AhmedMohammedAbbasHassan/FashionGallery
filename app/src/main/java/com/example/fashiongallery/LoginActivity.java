package com.example.fashiongallery;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.responses.LoginResponse;
import com.example.fashiongallery.utils.SharedPreferenceUtils;
import com.victor.loading.rotate.RotateLoading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    private Button signupNowButton ,loginButton;
    private EditText emailLoginEditText , passwordLoginEditText;
    private RotateLoading rotateLoading  ;


    SharedPreferences sharedpreferences;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupNowButton = (Button)findViewById(R.id.signup_btn);
        loginButton     = (Button)findViewById(R.id.login_btn);
        emailLoginEditText  = (EditText)findViewById(R.id.email_et_login);
        passwordLoginEditText = (EditText)findViewById(R.id.password_login);
        rotateLoading  = (RotateLoading)findViewById(R.id.loading_login);

        sharedpreferences =getSharedPreferences("LoggedUserPref", Context.MODE_PRIVATE);



    }





     void login (View view)
    {


        showLoading(true);
        final String email     =  emailLoginEditText.getText().toString();
        String password  =  passwordLoginEditText.getText().toString();


        if (validate(email, password))

        {



            Retrofit retrofit  = Connection.instance().build();
            ClintApi clint = retrofit.create(ClintApi.class);
            Call<LoginResponse> call  =clint.loginUser(email,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getCode() == 200)
                    {

                        SharedPreferenceUtils.saveUserDate(loginResponse.getId(),loginResponse.getEmail(),loginResponse.getUserName(),loginResponse.getGender());
                       // saveUserDate(loginResponse.getEmail());
                       // SharedPreferenceUtils.saveUserDate(loginResponse.getId(),loginResponse.getEmail(),loginResponse.getUserName(),loginResponse.getGender() );
                        Toast.makeText(LoginActivity.this, "user name is :" + loginResponse.getUserName(), Toast.LENGTH_SHORT).show();
                        showLoading(false);
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity(intent);
                    }else if(loginResponse.getCode() == 201){

                        showLoading(false);
                        Toast.makeText(LoginActivity.this, "Please check your email or password", Toast.LENGTH_SHORT).show();

                    }else{

                        showLoading(false);
                        Toast.makeText(LoginActivity.this, "unexpected error", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable throwable) {

                    showLoading(false);
                    Toast.makeText(LoginActivity.this, "Check your Connection", Toast.LENGTH_SHORT).show();

                }
            });



        }else
        {

            showLoading(false);
        }



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



    public boolean validate(String email,String password) {


        boolean valid = true;


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLoginEditText.setError("enter a valid email address");
            valid = false;
        } else {
            emailLoginEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordLoginEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordLoginEditText.setError(null);
        }

        return valid;
    }





     void signUpNow(View view)
    {

        Intent intent  =new Intent(LoginActivity.this,SiginupActivity.class);
        startActivity(intent);
    }




    public  void saveUserDate(String email )

    {



        SharedPreferences.Editor editor = sharedpreferences.edit();

       // editor.putString("id", id);
       // editor.putString("userName", userName);
        editor.putString("email", email);
       // editor.putString("gender", gender);
        editor.commit();


    }


}
