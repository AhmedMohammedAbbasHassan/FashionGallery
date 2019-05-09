package com.example.fashiongallery.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fashiongallery.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private List<String> listData;
    private Activity activity ;
    private LayoutInflater inflater;

    public SpinnerAdapter(List<String> listData, Activity activity) {
        this.listData = listData;
        this.activity = activity;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (convertView == null)
                view = inflater.inflate(R.layout.spinner_item,null);

            TextView tv = (TextView)view.findViewById(R.id.spinner_tv);
            tv.setText(listData.get(position));

            return view;

    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view =  super.getDropDownView(position, convertView, parent);

        LinearLayout linearLayout = (LinearLayout)view;
        linearLayout.setBackgroundColor(Color.parseColor("#22333B"));
        TextView textView = (TextView)linearLayout.findViewById(R.id.spinner_tv);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        return view;

    }



}
