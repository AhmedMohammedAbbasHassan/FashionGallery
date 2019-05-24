package com.example.fashiongallery.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.example.fashiongallery.api.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyGalleryAdapter extends RecyclerView.Adapter<MyGalleryAdapter.MyGalleryViewHolder> {



    private List<Model> myModelList ;
    private Context context ;


    public MyGalleryAdapter(List<Model> models , Context context){

        this.myModelList = models;
        this.context  = context ;

    }





    @NonNull
    @Override
    public MyGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_my_model, viewGroup, false);

        return new MyGalleryAdapter.MyGalleryViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyGalleryViewHolder myGalleryViewHolder, int i) {

        final  Model model = myModelList.get(i);



        Picasso.get().load(model.getImg_url()).into(myGalleryViewHolder.img);
        myGalleryViewHolder.titleTextView.setText(model.getName());
        myGalleryViewHolder.priceTextView.setText(model.getPrice());

        myGalleryViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppController.getContext(), "model Id " + model.getId() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myModelList.size();
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
