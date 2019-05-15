package com.example.fashiongallery.utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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

            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            rotateLoading.stop();
        }

    }


}
