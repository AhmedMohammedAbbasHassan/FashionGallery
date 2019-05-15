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
import com.example.fashiongallery.api.model.Model;
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.LoginResponse;
import com.example.fashiongallery.responses.ModelResponse;
import com.example.fashiongallery.utils.AppUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModelListFragment extends Fragment {

   private RotateLoading rotateLoading;
    private RecyclerView recyclerView ;
     List<Model> myList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_model_list,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


/*
        Model model1 = new Model();
        model1.setModelImage(R.drawable.model2);
        model1.setModelTitle("Blue Dress");
        model1.setModelPrice("50 $");


        Model model2 = new Model();
        model2.setModelImage(R.drawable.model);
        model2.setModelTitle("red Dress");
        model2.setModelPrice("150 $");


        Model model3 = new Model();
        model3.setModelImage(R.drawable.model2);
        model3.setModelTitle("Blue Dress 2");
        model3.setModelPrice("75 $");

*/

        //myList = new ArrayList<Model>();

        recyclerView = (RecyclerView)view.findViewById(R.id.model_list_recycler);
        recyclerView.setHasFixedSize(true);
          rotateLoading = (RotateLoading)view.findViewById(R.id.loading_model_list);

        LinearLayoutManager llm = new LinearLayoutManager(AppController.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        getModelList();
       // myList.add(model1);
       // myList.add(model2);
      //  myList.add(model3);

    }




    void getModelList(){


        AppUtils.showLoading(true,rotateLoading,getActivity());
        Retrofit retrofit = Connection.instance().build();
        ClintApi clint = retrofit.create(ClintApi.class);
        Call<ModelResponse> call = clint.getModel(AppUtils.mCat,AppUtils.sCat,"14");
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {


                ModelResponse data = response.body();
                 myList = data.getModels();


                 Toast.makeText(AppController.getContext(), "done  /" + data.getCode(), Toast.LENGTH_SHORT).show();


                AppUtils.showLoading(false,rotateLoading,getActivity());
                ModelListAdapter adapter  = new ModelListAdapter(myList,getActivity());

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {

                AppUtils.showLoading(false,rotateLoading,getActivity());
                Toast.makeText(AppController.getContext(), "error"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}
