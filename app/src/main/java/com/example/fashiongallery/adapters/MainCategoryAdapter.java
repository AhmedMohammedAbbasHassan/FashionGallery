package com.example.fashiongallery.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.MainActivity;
import com.example.fashiongallery.R;
import com.example.fashiongallery.data.MainCategoryData;
import com.example.fashiongallery.fragments.HomeFragment;
import com.example.fashiongallery.fragments.SubCategoryFragment;

import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder> {

    private List<MainCategoryData> categoryDataList;
    private Context  context  ;

    public MainCategoryAdapter(List<MainCategoryData> mainCategoryData,Context context){

        this.categoryDataList = mainCategoryData;
        this.context   = context;
    }

    @NonNull
    @Override
    public MainCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_main_category, viewGroup, false);



        return new MainCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCategoryViewHolder mainCategoryViewHolder,final int i) {


        MainCategoryData data  = categoryDataList.get(i);

        mainCategoryViewHolder.imageView.setImageResource(data.getImageSrc());
        mainCategoryViewHolder.titleTextView.setText(data.getTitle());
        mainCategoryViewHolder.subTitle.setText(data.getSubTitle());


        mainCategoryViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AppController.getContext(), "img clicked", Toast.LENGTH_SHORT).show();

                Fragment fragment = new SubCategoryFragment();

                Bundle bundle  =new Bundle();
                bundle.putString("type", String.valueOf(i));

                fragment.setArguments(bundle);


                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.home_screen_area,fragment);
                fragmentTransaction.commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }



    public class MainCategoryViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView ;
        TextView titleTextView , subTitle;


        public MainCategoryViewHolder(View v){
            super(v);

            imageView  = (ImageView)v.findViewById(R.id.img_card_main);
            titleTextView  = (TextView)v.findViewById(R.id.title_main);
            subTitle    = (TextView)v.findViewById(R.id.sub_title_main);

        }



    }









}
