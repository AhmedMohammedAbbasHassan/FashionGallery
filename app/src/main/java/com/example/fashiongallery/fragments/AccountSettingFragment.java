package com.example.fashiongallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.fashiongallery.R;
import com.example.fashiongallery.utils.SharedPreferenceUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSettingFragment extends Fragment {

    private CircleImageView userImgCircleImageView;
    private EditText userNameEditText , userEmailEditText , userPasswordEditText , userPhoneEditText , userLocationEditText ;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_setting,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


     userImgCircleImageView  = (CircleImageView)view.findViewById(R.id.user_profile_image_setting);
     userNameEditText  = (EditText)view.findViewById(R.id.user_name_setting_et);
     userEmailEditText  = (EditText)view.findViewById(R.id.user_email_setting_et);
     userPasswordEditText  = (EditText)view.findViewById(R.id.user_password_setting_et);
     userPhoneEditText  = (EditText)view.findViewById(R.id.user_phone_setting_et);
     userLocationEditText  = (EditText)view.findViewById(R.id.user_location_setting_et);



        userNameEditText.setText(SharedPreferenceUtils.getUserNameFromPref());
        userEmailEditText.setText(SharedPreferenceUtils.getUserEmailFromPref());
        userPhoneEditText.setText(SharedPreferenceUtils.getUserPhoneFromPref());
        userLocationEditText.setText(SharedPreferenceUtils.getUserLocationFromPref());



    }
}
