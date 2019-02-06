package com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apphelper2019.R;
import com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.api.model.User;
import com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.api.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://www.youtube.com/watch?v=j7lRiTJ_-cI&index=2&list=PLpUMhvC6l7APq7y_FFfK-GEHvcUKqo6SC
//http://qaru.site/questions/252106/use-jsonreadersetlenienttrue-to-accept-malformed-json-at-line-1-column-1-path
//C:\Users\Khach\AndroidStudioProjects\helper_2018\RetrofitExample\app\src\main\java\example\am\retrofitexample
public class Main6Activity extends AppCompatActivity {
    private static final String BASE_URL = "http://192.168.0.111/android_apis/login_reg_api/api/service/";
    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private Button bt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        initViews();
        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        et_username.getText().toString(),
                        et_password.getText().toString());
                sendNetworkRequest(user);
            }
        });
    }

    private void sendNetworkRequest(User user) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Main6Activity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserService userService = retrofit.create(UserService.class);

        Call<User> userCall = userService.createAccount(user.getUsername(), user.getPassword());

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                if (u != null) {
                    Snackbar.make(et_username, "" + u.getId(), Snackbar.LENGTH_LONG)
                            .setAction(null, null)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(et_username, "" + t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction(null, null)
                        .show();
            }
        });
    }

    private void initViews() {
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        bt_signup = findViewById(R.id.bt_signup);
    }
}
