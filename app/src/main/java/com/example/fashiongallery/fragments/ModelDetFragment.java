package com.example.fashiongallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ModelDetFragment extends Fragment {



    private ImageView modelImageView  ;
    private TextView userNameTextView ,modelNameTextView , modelPriceTextView  , modelDsscTextView ;
    private CircleImageView userImgCircleImageView  ;
    private Button phoneButton , locationButton ;
    private String userPhone ;
    private String userLocation ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_model_det,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String userName = getArguments().getString("userName");
        String userImg = getArguments().getString("userImg");
         userPhone = getArguments().getString("userPhone");
         userLocation = getArguments().getString("userLocation");


        String modelImg = getArguments().getString("modelImg");
        String modelName = getArguments().getString("modelName");
        String modelPrice = getArguments().getString("modelPrice");
        String modelDesc = getArguments().getString("modelDesc");


        userNameTextView  = (TextView)view.findViewById(R.id.user_name_det);


        modelNameTextView  = (TextView)view.findViewById(R.id.model_name_det);
        modelPriceTextView  = (TextView)view.findViewById(R.id.model_price_det);
        modelDsscTextView  = (TextView)view.findViewById(R.id.desc_model_det);
        modelImageView     = (ImageView)view.findViewById(R.id.img_det) ;
        userImgCircleImageView = (CircleImageView)view.findViewById(R.id.user_profile_image_det);
        phoneButton = (Button)view.findViewById(R.id.call_btn_det);
        locationButton = (Button)view.findViewById(R.id.location_btn_det);



        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppController.getContext(), userPhone, Toast.LENGTH_SHORT).show();
            }
        });


        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppController.getContext(), userLocation, Toast.LENGTH_SHORT).show();
            }
        });




        if (userImg!= null){


            Picasso.get().load(userImg).into(userImgCircleImageView);

        }


        userNameTextView.setText(userName);
        Picasso.get().load(modelImg).into(modelImageView);

        modelNameTextView.setText(modelName);
        modelPriceTextView.setText(modelPrice+" $");
        modelDsscTextView.setText(modelDesc);



    }
}
