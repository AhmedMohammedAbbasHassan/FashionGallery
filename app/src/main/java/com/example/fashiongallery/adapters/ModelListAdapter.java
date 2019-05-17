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
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.ModelResponse;
import com.example.fashiongallery.responses.ResponseInfo;
import com.example.fashiongallery.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        Picasso.get().load(model.getImg_url()).into(modelListViewHilder.imageView);

        modelListViewHilder.titleTextView.setText(model.getName());
        modelListViewHilder.priceTextView.setText(model.getPrice());

        if (model.getLikes()!=null && !model.getLikes().equals("0")){

            modelListViewHilder.likeButton.setBackgroundResource(R.drawable.ic_like2);
            modelListViewHilder.likeClick = true;
        }else if(model.getLikes()=="1"){

            modelListViewHilder.likeButton.setBackgroundResource(R.drawable.ic_like2);
            modelListViewHilder.likeClick = true;


        }


        if (model.getFavorites()!=null && !model.getFavorites().equals("0")){

            modelListViewHilder.addFavoriteButton.setBackgroundResource(R.drawable.ic_lover);
            modelListViewHilder.favoriteClick = true;
        }else if (model.getFavorites()=="1"){

            modelListViewHilder.addFavoriteButton.setBackgroundResource(R.drawable.ic_lover);
            modelListViewHilder.favoriteClick = true;

        }

        modelListViewHilder.addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (modelListViewHilder.favoriteClick)
                {

                    likeOrDisLiske(model.getId(),modelListViewHilder.addFavoriteButton,modelListViewHilder,"4");


                }else{

                    likeOrDisLiske(model.getId(),modelListViewHilder.addFavoriteButton,modelListViewHilder,"3");


                }


            }
        });


        modelListViewHilder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (modelListViewHilder.likeClick)
                {
                    likeOrDisLiske(model.getId(),modelListViewHilder.likeButton,modelListViewHilder,"2");

                  }else{

                    likeOrDisLiske(model.getId(),modelListViewHilder.likeButton,modelListViewHilder,"1");

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
        boolean likeClick = false ;
        boolean favoriteClick =false;

       public ModelListViewHilder(View view) {
      super(view);


      imageView = (ImageView)view.findViewById(R.id.model_img);
      titleTextView = (TextView)view.findViewById(R.id.title_tv);
      priceTextView  = (TextView)view.findViewById(R.id.price_tv);
      likeButton  = (Button)view.findViewById(R.id.like_btn);
      addFavoriteButton = (Button)view.findViewById(R.id.favorite_btn);




       }


    }



    void  likeOrDisLiske(String mId, final Button button, final ModelListViewHilder holder, final String likeOrDis){




        Retrofit retrofit = Connection.instance().build();
        ClintApi clint = retrofit.create(ClintApi.class);
        Call<ResponseInfo> call = clint.doLike("14",mId,likeOrDis);
        call.enqueue(new Callback<ResponseInfo>() {
    @Override
    public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

        ResponseInfo data = response.body();

        if (data.getCode() == 200)
        {

            if (likeOrDis.equals("1"))
            {
            button.setBackgroundResource(R.drawable.ic_like2);
            holder.likeClick = true;
            }else if (likeOrDis.equals("2"))
            {

                button.setBackgroundResource(R.drawable.ic_like);
                holder.likeClick = false;

            }else if (likeOrDis.equals("3"))
            {

                button.setBackgroundResource(R.drawable.ic_lover);
                holder.favoriteClick = true;

            }else if (likeOrDis.equals("4"))
            {

                button.setBackgroundResource(R.drawable.ic_loving_heart);
                holder.favoriteClick = false;


            }

        }else{

            Toast.makeText(AppController.getContext(), "201 error", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onFailure(Call<ResponseInfo> call, Throwable t) {


        Toast.makeText(AppController.getContext(), "in on failer ", Toast.LENGTH_SHORT).show();

    }
});


    }



}
