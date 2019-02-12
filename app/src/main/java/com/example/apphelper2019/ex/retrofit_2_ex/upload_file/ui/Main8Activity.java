package com.example.apphelper2019.ex.retrofit_2_ex.upload_file.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphelper2019.R;
import com.example.apphelper2019.ex.retrofit_2_ex.upload_file.api.service.FileUploadService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main8Activity extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.0.111/android_apis/login_reg_api/api/service/";

    private EditText etInputDescription;
    private Button btn_uploadFile;
    private final int PICK_IMAGE_FROM_GALLERY_REQUEST_CODE = 1;
    private final int MY_PERMISSION_REQUEST = 100;
    private MyOnclickListener myOnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        //checking the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }

        btn_uploadFile = findViewById(R.id.btn_uploadFile);
        etInputDescription = findViewById(R.id.inputDescription);

        myOnclickListener = new MyOnclickListener();

        btn_uploadFile.setOnClickListener(myOnclickListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST:
                //if request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                } else {
                    //permission denied
                }
                break;
        }
    }

    private void chooseFile() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            Main8Activity.this.startActivityForResult(
                    Intent.createChooser(i, "Select a File to Upload"),
                    PICK_IMAGE_FROM_GALLERY_REQUEST_CODE);

        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
//        Intent intent = new Intent(getActivity(), NormalFilePickActivity.class);
//        intent.putExtra(Constant.MAX_NUMBER, 1);
//        intent.putExtra(NormalFilePickActivity.SUFFIX, new String[] {"xlsx", "xls", "doc", "docx", "ppt", "pptx", "pdf"});
//        startActivityForResult(intent, Constant.REQUEST_CODE_PICK_FILE);
    }

    private void uploadFile(Uri fileUri) {
        String realPath = getRealPathFromURI(fileUri);
        if (realPath == null) {
            return;
        }
        File file = new File(realPath);


        if (!file.exists()) {
            Snackbar.make(etInputDescription, "Select a file", Snackbar.LENGTH_LONG)
                    .setAction(null, null)
                    .show();
            return;
        }
        etInputDescription.setText(file.getName());

        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, etInputDescription.getText().toString());
//        RequestBody descriptionPart = RequestBody.create(MediaType.parse("text/plain"), etInputDescription.getText().toString());

        RequestBody filePart = RequestBody.create(
                MediaType.parse(getContentResolver().getType(fileUri)), file);

        MultipartBody.Part b = MultipartBody.Part.createFormData("my_photo_file", file.getName(), filePart);

//        //The gson builder
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Main8Activity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        FileUploadService fileUploadService = retrofit.create(FileUploadService.class);

        Call<ResponseBody> uploadCall = fileUploadService.uploadPhoto(descriptionPart, b);

        uploadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String status = null;
                String message = null;
                try {

                    String jsonResponse = response.body().string();
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = (JsonObject) parser.parse(jsonResponse);
                    status = jsonObject.get("status").getAsString();
                    message = jsonObject.get("message").getAsString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Snackbar.make(etInputDescription, response.message() + message, Snackbar.LENGTH_LONG)
                        .setAction(null, null)
                        .show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(etInputDescription, "failure:" + t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction(null, null)
                        .show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_FROM_GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            //the image URI
            Uri selectedImage = data.getData();
//            String fPath = data.getDataString();

            //calling the upload file method after choosing the file
            uploadFile(selectedImage);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private class MyOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
//            Toast.makeText(getApplicationContext(), "Clicked btn", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent();
//            //Show only images, no videos or anything else
////                intent.setType("images/*");
//            intent.setType("file/*");
////            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            //Always show the chooser (if there are multiple options available)
//
//            startActivityForResult(
//                    Intent.createChooser(intent, "Select picture"),
//                    PICK_IMAGE_FROM_GALLERY_REQUEST_CODE);

            switch (v.getId()) {
                case R.id.btn_uploadFile:
                    chooseFile();
                    break;
            }
        }
    }
}
