package com.example.fashiongallery;

import android.app.Application;
import android.content.Context;


/**
 * Created by PC on 19/12/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();


    private static AppController mInstance;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }



}