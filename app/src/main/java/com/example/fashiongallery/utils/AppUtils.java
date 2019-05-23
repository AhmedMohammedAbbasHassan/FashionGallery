package com.example.fashiongallery.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import com.example.fashiongallery.AppController;
import com.victor.loading.rotate.RotateLoading;

public class AppUtils {



    public static String mCat ;
    public static String sCat ;


  public static void showLoading(boolean show, RotateLoading rotateLoading, Activity activity)
    {
        if (show)
        {
            rotateLoading.start();
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        }else {

            Context context = AppController.getContext() ;

            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            rotateLoading.stop();
        }

    }


}
