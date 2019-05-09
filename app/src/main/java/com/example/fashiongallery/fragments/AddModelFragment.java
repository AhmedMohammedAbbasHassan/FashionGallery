package com.example.fashiongallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fashiongallery.R;
import com.example.fashiongallery.adapters.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;


public class AddModelFragment extends Fragment {


    private RadioGroup group  ;
    private List<String> myList  = new ArrayList<>();
    SpinnerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_add_model, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        menData();
        group  = (RadioGroup)view.findViewById(R.id.radioGroup_add_model);
        final Spinner spinner = (Spinner)view.findViewById(R.id.spinner_1);

         adapter = new SpinnerAdapter(myList,getActivity());
        spinner.setAdapter(adapter);



        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.men_wear){


                    myList.clear();
                    menData();

                    adapter = new SpinnerAdapter(myList,getActivity());
                    spinner.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else if (checkedId == R.id.women_wear){

                    myList.clear();
                    womenData();


                    adapter = new SpinnerAdapter(myList,getActivity());
                    spinner.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {

myList.clear();
                    childrenData();

                    adapter = new SpinnerAdapter(myList,getActivity());
                    spinner.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }



    void menData(){



        myList.add("Shoes");
        myList.add("T-Shirt");
        myList.add("Shirt");
        myList.add("Pants");
        myList.add("Sport");
        myList.add("Caps");
        myList.add("Accessories");

    }

    void womenData(){

        myList.add("Dresses");
        myList.add("Skirts");
        myList.add("T-shirt");
        myList.add("Pants");
        myList.add("Shose");
        myList.add("Purse");
        myList.add("Sport");
        myList.add("Accessories");



    }
    void childrenData(){


        myList.add("T-shirt");
        myList.add("Pants");
        myList.add("Shoses");




    }



}