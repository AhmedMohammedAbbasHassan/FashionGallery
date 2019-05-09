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
import com.example.fashiongallery.adapters.SubCategoryAdapter;
import com.example.fashiongallery.data.SubCategoryData;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryFragment extends Fragment {



    private RecyclerView recyclerView ;
    List<SubCategoryData> subCategoryData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_sub_category,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = this.getArguments();

        String strtext="";
        if(bundle != null){

            // handle your code here.


           strtext =getArguments().getString("type");


            Toast.makeText(AppController.getContext(), strtext, Toast.LENGTH_LONG).show();

        }



      dataList(strtext);



        recyclerView = (RecyclerView) view.findViewById(R.id.sub_category_recycler);
      recyclerView.setHasFixedSize(true);



        LinearLayoutManager llm = new LinearLayoutManager(AppController.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);




        SubCategoryAdapter adapter  = new SubCategoryAdapter(subCategoryData,getActivity());


        recyclerView.setAdapter(adapter);




    }


    void dataList(String mainType)

    {



        subCategoryData = new ArrayList<SubCategoryData>();



        if (mainType.equals("0"))
        {


            SubCategoryData shose = new SubCategoryData();
            shose.setTitle("Shoes");
            shose.setIconSrc(R.drawable.ic_shoe);

            SubCategoryData tShirt = new SubCategoryData();
            tShirt.setTitle("T-Shirt");
            tShirt.setIconSrc(R.drawable.ic_pattern);


            SubCategoryData shirt = new SubCategoryData();
            shirt.setTitle("Shirt");
            shirt.setIconSrc(R.drawable.ic_shirt);


            SubCategoryData pants = new SubCategoryData();
            pants.setTitle("Pants");
            pants.setIconSrc(R.drawable.ic_jeans);


            SubCategoryData sport = new SubCategoryData();
            sport.setTitle("Sport");
            sport.setIconSrc(R.drawable.ic_referee);



            SubCategoryData caps = new SubCategoryData();
            caps.setTitle("Caps");
            caps.setIconSrc(R.drawable.ic_cap);


            SubCategoryData accessories = new SubCategoryData();
            accessories.setTitle("Accessories");
            accessories.setIconSrc(R.drawable.ic_watch);



            subCategoryData.add(shirt);
            subCategoryData.add(tShirt);
            subCategoryData.add(pants);
            subCategoryData.add(shose);
            subCategoryData.add(sport);
            subCategoryData.add(caps);
            subCategoryData.add(accessories);


        }
        if (mainType.equals("1"))
                 {

                     SubCategoryData dress = new SubCategoryData();
                     dress.setTitle("Dresses");
                     dress.setIconSrc(R.drawable.ic_dress);

                     SubCategoryData skirts = new SubCategoryData();
                     skirts.setTitle("Skirts");
                     skirts.setIconSrc(R.drawable.ic_skirt);

                     SubCategoryData tShirt = new SubCategoryData();
                     tShirt.setTitle("T-shirt");
                     tShirt.setIconSrc(R.drawable.ic_pattern);

                     SubCategoryData pants = new SubCategoryData();
                     pants.setTitle("Pants");
                     pants.setIconSrc(R.drawable.ic_jeans);

                     SubCategoryData shose = new SubCategoryData();
                     shose.setTitle("Shose");
                     shose.setIconSrc(R.drawable.ic_high_heels);


                     SubCategoryData purse = new SubCategoryData();
                     purse.setTitle("Purse");
                     purse.setIconSrc(R.drawable.ic_purse);


                     SubCategoryData sport = new SubCategoryData();
                     sport.setTitle("Sport");
                     sport.setIconSrc(R.drawable.ic_referee);


                     SubCategoryData accessories = new SubCategoryData();
                     accessories.setTitle("Accessories");
                     accessories.setIconSrc(R.drawable.ic_watch);



                     subCategoryData.add(dress);
                     subCategoryData.add(skirts);
                     subCategoryData.add(tShirt);
                     subCategoryData.add(pants);
                     subCategoryData.add(shose);
                     subCategoryData.add(purse);
                     subCategoryData.add(sport);
                     subCategoryData.add(accessories);



                 }

    else if (mainType.equals("2")){




            SubCategoryData tShirt = new SubCategoryData();
            tShirt.setTitle("T-Shirt");
            tShirt.setIconSrc(R.drawable.ic_pattern);


            SubCategoryData pants = new SubCategoryData();
            pants.setTitle("Pants");
            pants.setIconSrc(R.drawable.ic_jeans);

            SubCategoryData shose = new SubCategoryData();
            shose.setTitle("Shoes");
            shose.setIconSrc(R.drawable.ic_shoe);

            subCategoryData.add(tShirt);
            subCategoryData.add(pants);
            subCategoryData.add(shose);



        }




    }


}
