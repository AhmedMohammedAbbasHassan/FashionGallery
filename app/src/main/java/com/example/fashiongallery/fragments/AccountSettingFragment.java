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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashiongallery.AppController;
import com.example.fashiongallery.R;
import com.example.fashiongallery.api.services.ClintApi;
import com.example.fashiongallery.api.services.Connection;
import com.example.fashiongallery.responses.ResponseInfo;
import com.example.fashiongallery.responses.UpdateUserResponse;
import com.example.fashiongallery.utils.AppUtils;
import com.example.fashiongallery.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.victor.loading.rotate.RotateLoading;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class AccountSettingFragment extends Fragment {

    private CircleImageView userImgCircleImageView;
    private EditText userNameEditText , userEmailEditText , userPasswordEditText , userPhoneEditText , userLocationEditText ;
    private Uri imageUri;
    String imagePath;
    private RotateLoading rotateLoading ;
    private Button button ;

    private TextView pickTextView ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_setting,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


     userImgCircleImageView  = (CircleImageView)view.findViewById(R.id.user_profile_image_setting);
     userNameEditText  = (EditText)view.findViewById(R.id.user_name_setting_et);
     userEmailEditText  = (EditText)view.findViewById(R.id.user_email_setting_et);
     userPasswordEditText  = (EditText)view.findViewById(R.id.user_password_setting_et);
     userPhoneEditText  = (EditText)view.findViewById(R.id.user_phone_setting_et);
     userLocationEditText  = (EditText)view.findViewById(R.id.user_location_setting_et);
     button  = (Button)view.findViewById(R.id.update_setting_btn);
     rotateLoading = (RotateLoading)view.findViewById(R.id.loading_Setting);

     pickTextView = (TextView)view.findViewById(R.id.pick_tv);


     pickTextView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Toast.makeText(AppController.getContext(), "path : "+imagePath+" file Name :  " , Toast.LENGTH_SHORT).show();

         }
     });



        if (SharedPreferenceUtils.getUserImgFromPref()!=null && !SharedPreferenceUtils.getUserImgFromPref().isEmpty() ){

            Picasso.get().load(SharedPreferenceUtils.getUserImgFromPref()).into(userImgCircleImageView);

        }

        userNameEditText.setText(SharedPreferenceUtils.getUserNameFromPref());
        userEmailEditText.setText(SharedPreferenceUtils.getUserEmailFromPref());
        userPhoneEditText.setText(SharedPreferenceUtils.getUserPhoneFromPref());
        userLocationEditText.setText(SharedPreferenceUtils.getUserLocationFromPref());
        userPasswordEditText.setText(SharedPreferenceUtils.getUserPassword());


        userImgCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cropimg();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadForm();

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

            userImgCircleImageView.setImageURI(imageUri);

            imagePath = getRealPathFromUri(imageUri,getActivity());

        }else {


            Toast.makeText(AppController.getContext(), "noo", Toast.LENGTH_SHORT).show();


            imageUri = null;
            imagePath=null;
            userImgCircleImageView.setImageResource(R.drawable.profile);


            cropimg();




        }



    }





// get a path from image uri

    public String getRealPathFromUri(final Uri uri, Context mContext) {
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





    void cropimg(){

        CropImage.activity(imageUri)
                .setAspectRatio(1,1)
                .start(AppController.getContext(),AccountSettingFragment.this);


    }





    void uploadForm(){

        AppUtils.showLoading(true,rotateLoading,getActivity());
        final String userName = userNameEditText.getText().toString();
        final String email    = userEmailEditText.getText().toString();
        final String password = userPasswordEditText.getText().toString();
        final String phone = userPhoneEditText.getText().toString();
        final String location = userLocationEditText.getText().toString();


        if (validate(email,password,userName,phone,location)) {

            MultipartBody.Part body=null ;
            if (imagePath!=null&&!imagePath.isEmpty()) {
                File file = new File(imagePath);

                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                body = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
            }
            RequestBody name = RequestBody.create(MultipartBody.FORM, userName);
            RequestBody pass = RequestBody.create(MultipartBody.FORM, password);
            RequestBody mail = RequestBody.create(MultipartBody.FORM, email);
            RequestBody number = RequestBody.create(MultipartBody.FORM, phone);
            RequestBody locationn = RequestBody.create(MultipartBody.FORM, location);
            RequestBody userId = RequestBody.create(MultipartBody.FORM, SharedPreferenceUtils.getUserId());

            Retrofit retrofit = Connection.instance().build();
            ClintApi clint = retrofit.create(ClintApi.class);
            Call<UpdateUserResponse> call = null ;
            if (imagePath!=null&&!imagePath.isEmpty()){
                call = clint.updateUser(body,userId,mail,name,pass,number,locationn);

            }else{

                 call = clint.updateUserWithOutImg(SharedPreferenceUtils.getUserId(),email,userName,password,phone,location);

            }

            call.enqueue(new Callback<UpdateUserResponse>() {
                @Override
                public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {

                    UpdateUserResponse data = response.body();

                    if (data.getCode() == 200){
                        AppUtils.showLoading(false,rotateLoading,getActivity());

   SharedPreferenceUtils.saveUserDate(SharedPreferenceUtils.getUserId(),email,userName,SharedPreferenceUtils.getUserGenderFromPref(),phone,location,data.getUpdatedImgUrl(),password);

                        Toast.makeText(AppController.getContext(), "Setting Updating successfully", Toast.LENGTH_SHORT).show();
                    }else{

                        AppUtils.showLoading(false,rotateLoading,getActivity());

                        Toast.makeText(AppController.getContext(), "un unexpected  please try again ", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                    AppUtils.showLoading(false,rotateLoading,getActivity());

                    Toast.makeText(AppController.getContext(), "check your connection "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }else{

            AppUtils.showLoading(false,rotateLoading,getActivity());

        }



    }





    public boolean validate(String email,String password,String name , String phone , String location) {


        boolean valid = true;


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            userEmailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            userPasswordEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            userPasswordEditText.setError(null);
        }


        if (phone.isEmpty() || phone.length() < 4 || phone.length() > 10) {
            userPhoneEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            userPhoneEditText.setError(null);
        }


        if (name.isEmpty() || name.length() < 4 || name.length() > 10) {
            userNameEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            userNameEditText.setError(null);
        }


        if (location.isEmpty() || location.length() < 4 || location.length() > 10) {
            userLocationEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            userLocationEditText.setError(null);
        }


        return valid;
    }



}
