package com.example.fashiongallery.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashiongallery.R;

public class MyGalleryAdapter extends RecyclerView.Adapter<MyGalleryAdapter.MyGalleryViewHolder> {


    @NonNull
    @Override
    public MyGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_my_model, viewGroup, false);

        return new MyGalleryAdapter.MyGalleryViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyGalleryViewHolder myGalleryViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyGalleryViewHolder extends RecyclerView.ViewHolder{


        private TextView titleTextView , priceTextView  ;
        private ImageView img   ;
        private Button deleteButton ;

        public MyGalleryViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView)itemView.findViewById(R.id.title_my_model);
            priceTextView = (TextView)itemView.findViewById(R.id.price_my_model);
            img           = (ImageView)itemView.findViewById(R.id.img_my_model);
            deleteButton  = (Button)itemView.findViewById(R.id.delete_btn_my_model);

        }
    }
}
