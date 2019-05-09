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

import com.example.fashiongallery.R;
import com.example.fashiongallery.data.SubCategoryData;
import com.example.fashiongallery.fragments.ModelListFragment;
import com.example.fashiongallery.fragments.SubCategoryFragment;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {


    private List<SubCategoryData> categoryDataList ;
    private Context mContext;

   public SubCategoryAdapter(List<SubCategoryData> data , Context context){

       this.categoryDataList = data;
       this.mContext = context;

    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_sub_category, viewGroup, false);

       return new SubCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder subCategoryViewHolder, int i) {


       SubCategoryData subData = categoryDataList.get(i);

       subCategoryViewHolder.titleTextView.setText(subData.getTitle());
       subCategoryViewHolder.imageView.setImageResource(subData.getIconSrc());

    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder{

       ImageView imageView  ;
       TextView titleTextView;
        public SubCategoryViewHolder(View view){
            super(view);

            imageView = (ImageView)view.findViewById(R.id.img_sub_category);
            titleTextView = (TextView)view.findViewById(R.id.title_sub_category);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new ModelListFragment();



                    FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.home_screen_area,fragment);
                    fragmentTransaction.commit();


                }
            });


        }
    }
}
