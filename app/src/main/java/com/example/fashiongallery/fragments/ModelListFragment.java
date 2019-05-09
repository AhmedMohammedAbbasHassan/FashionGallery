package com.example.fashiongallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.example.fashiongallery.adapters.ModelListAdapter;
import com.example.fashiongallery.api.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelListFragment extends Fragment {


    private RecyclerView recyclerView ;
    private List<Model> myList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_model_list,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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



        myList = new ArrayList<Model>();

        recyclerView = (RecyclerView)view.findViewById(R.id.model_list_recycler);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(AppController.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        myList.add(model1);
        myList.add(model2);
        myList.add(model3);


        ModelListAdapter adapter  = new ModelListAdapter(myList,getActivity());

        recyclerView.setAdapter(adapter);
    }
}
