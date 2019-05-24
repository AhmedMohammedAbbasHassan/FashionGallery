package com.example.fashiongallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.example.fashiongallery.adapters.ModelListAdapter;
import com.example.fashiongallery.adapters.MyGalleryAdapter;
import com.example.fashiongallery.api.model.Model;
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.ModelResponse;
import com.example.fashiongallery.utils.AppUtils;
import com.example.fashiongallery.utils.SharedPreferenceUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyGalleryFragment extends Fragment {


    private List<Model> myModelList ;
    private RotateLoading rotateLoading ;
    private RecyclerView recyclerView  ;
    Call<ModelResponse> call ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mygallery,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rotateLoading  = (RotateLoading)view.findViewById(R.id.loading_my_gallery);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_gallery_recycler);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(AppController.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        getMyGallery();


    }




    void getMyGallery(){

        AppUtils.showLoading(true,rotateLoading,getActivity());
        Retrofit retrofit = Connection.instance().build();
        ClintApi clint = retrofit.create(ClintApi.class);
        call = clint.getMyGallery(SharedPreferenceUtils.getUserId());

        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                ModelResponse data = response.body();


                if (data.getCode().equals("200")){
                    Toast.makeText(AppController.getContext(), "done  /" + data.getCode(), Toast.LENGTH_SHORT).show();
                    myModelList = data.getModels();


                    AppUtils.showLoading(false,rotateLoading,getActivity());
                    MyGalleryAdapter adapter  = new MyGalleryAdapter(myModelList,getActivity());

                    recyclerView.setAdapter(adapter);
                }else{

                    AppUtils.showLoading(false,rotateLoading,getActivity());
                    Toast.makeText(AppController.getContext(), "no model in this category ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {


                Toast.makeText(AppController.getContext(), "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }




}

