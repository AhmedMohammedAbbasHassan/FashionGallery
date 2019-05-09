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

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.example.fashiongallery.adapters.MainCategoryAdapter;
import com.example.fashiongallery.data.MainCategoryData;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment  extends Fragment {



    private RecyclerView recyclerView ;
    List<MainCategoryData> mainCategoryData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);





    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        MainCategoryData man = new MainCategoryData();
        man.setImageSrc(R.drawable.cover);
        man.setTitle("Man Wear");
        man.setSubTitle("Select from 50,0000 man wear shop");

        MainCategoryData women = new MainCategoryData();
        women.setImageSrc(R.drawable.cover2);
        women.setTitle("Women Wear");
        women.setSubTitle("Select from 80,0000 women wear shop");


        MainCategoryData children = new MainCategoryData();
        children.setImageSrc(R.drawable.cover3);
        children.setTitle("Children Wear");
        children.setSubTitle("Select from 30,0000 children wear shop");



        mainCategoryData = new ArrayList<MainCategoryData>();
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(AppController.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        mainCategoryData.add(man);
        mainCategoryData.add(women);
        mainCategoryData.add(children);


        MainCategoryAdapter adapter   = new MainCategoryAdapter(mainCategoryData,getActivity());
        recyclerView.setAdapter(adapter);




    }
}
