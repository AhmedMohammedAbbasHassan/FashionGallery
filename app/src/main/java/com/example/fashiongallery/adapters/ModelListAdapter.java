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

import com.example.fashiongallery.R;
import com.example.fashiongallery.api.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ModelListAdapter extends RecyclerView.Adapter<ModelListAdapter.ModelListViewHilder> {


    private List<Model> modelList;
    private Context mContext;
    boolean likeClicked = false;
    boolean favoriteClicked    = false;


    public ModelListAdapter(List<Model> models , Context context){

        this.mContext = context;
        this.modelList = models;

    }

    @NonNull
    @Override
    public ModelListViewHilder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_model_list, viewGroup, false);

        return new ModelListViewHilder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModelListViewHilder modelListViewHilder, int i) {



        final Model model  = modelList.get(i);
        //modelListViewHilder.imageView.setImageResource(model.getModelImage());
        Picasso.get().load(model.getImg_url()).into(modelListViewHilder.imageView);

        modelListViewHilder.titleTextView.setText(model.getName());
        modelListViewHilder.priceTextView.setText(model.getPrice());

        if (model.getLikes()!=null||model.getLikes()=="1"){

            modelListViewHilder.likeButton.setBackgroundResource(R.drawable.ic_like2);
            likeClicked = true;
        }

        if (model.getFavorites()!=null||model.getFavorites()=="1"){

            modelListViewHilder.addFavoriteButton.setBackgroundResource(R.drawable.ic_lover);
            favoriteClicked = true;
        }

        modelListViewHilder.addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (favoriteClicked)
                {


                    modelListViewHilder.addFavoriteButton.setBackgroundResource(R.drawable.ic_loving_heart);
                    favoriteClicked = false;

                }else{

                    modelListViewHilder.addFavoriteButton.setBackgroundResource(R.drawable.ic_lover);
                    favoriteClicked = true;


                }


            }
        });



        modelListViewHilder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeClicked)
                {


                    modelListViewHilder.likeButton.setBackgroundResource(R.drawable.ic_like);
                    likeClicked = false;

                }else{

                    modelListViewHilder.likeButton.setBackgroundResource(R.drawable.ic_like2);
                    likeClicked = true;


                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ModelListViewHilder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView titleTextView , priceTextView;
        Button   likeButton , addFavoriteButton;

       public ModelListViewHilder(View view) {
      super(view);


      imageView = (ImageView)view.findViewById(R.id.model_img);
      titleTextView = (TextView)view.findViewById(R.id.title_tv);
      priceTextView  = (TextView)view.findViewById(R.id.price_tv);
      likeButton  = (Button)view.findViewById(R.id.like_btn);
      addFavoriteButton = (Button)view.findViewById(R.id.favorite_btn);




       }


    }
}
