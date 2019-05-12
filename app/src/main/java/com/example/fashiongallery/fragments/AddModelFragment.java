package com.example.fashiongallery.fragments;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.MainActivity;
import com.example.fashiongallery.R;
import com.example.fashiongallery.adapters.SpinnerAdapter;
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.ResponseInfo;
import com.example.fashiongallery.responses.ServerResponse;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;


public class AddModelFragment extends Fragment {


    private RadioGroup group  ;
    private List<String> myList  = new ArrayList<>();
    SpinnerAdapter adapter;
    private ImageView imageView ;
    private Uri imageUri ;
    private Button button ;
    String imagePath;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_add_model, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

   imageView  = (ImageView)view.findViewById(R.id.add_model_img);
   button  =  (Button)view.findViewById(R.id.add_model_btn);
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



imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cropimg();

    }
});


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

       if (imagePath!=null)
       {

           uploadForm();
       }

    }
});


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Toast.makeText(AppController.getContext(), "in on activity result  ", Toast.LENGTH_SHORT).show();


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {

            CropImage.ActivityResult result  = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            imageView.setImageURI(imageUri);

            imagePath = getRealPathFromUri(imageUri,getActivity());

        }else {


            Toast.makeText(AppController.getContext(), "noo", Toast.LENGTH_SHORT).show();


            imageUri = null;
            imagePath=null;
            imageView.setImageResource(R.drawable.ic_menu_camera);


            cropimg();




        }



    }

    void menData(){

        myList.add("Choose Category");

        myList.add("Shoes");
        myList.add("T-Shirt");
        myList.add("Shirt");
        myList.add("Pants");
        myList.add("Sport");
        myList.add("Caps");
        myList.add("Accessories");

    }

    void womenData(){


        myList.add("Choose Category");
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

        myList.add("Choose Category");
        myList.add("T-shirt");
        myList.add("Pants");
        myList.add("Shoses");

    }

    void cropimg(){

        CropImage.activity(imageUri)
                .setAspectRatio(1,1)
                .start(AppController.getContext(),AddModelFragment.this);


    }




// get a path from image uri

    public String getRealPathFromUri(final Uri uri,Context mContext) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(mContext, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(mContext, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(mContext, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(mContext, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }



    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }






void uploadForm(){



    File file = new File(imagePath);

    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("img",file.getName(),requestBody);
    RequestBody name = RequestBody.create(MultipartBody.FORM,"ahmed");
    Retrofit retrofit  = Connection.instance().build();
    ClintApi clint = retrofit.create(ClintApi.class);
    Call<ResponseInfo> call  =clint.upload(body,name);

    call.enqueue(new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

            Toast.makeText(AppController.getContext(), "done", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {

            Toast.makeText(AppController.getContext(), "error", Toast.LENGTH_SHORT).show();

            Log.d("mike",t.getMessage());
        }
    });


}








}