package com.example.apphelper2019.ex.login_reg_php_mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphelper2019.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
//read this https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
/*<manifest ...>
<uses-permission android:name="android.permission.INTERNET" />
<application
    ...
            android:usesCleartextTraffic="true"
            ...>
            ...
</application>
</manifest>*/

/**
 * http://www.vogella.com/tutorials/Retrofit/article.html#retrofit
 * http://www.vogella.com/tutorials/Retrofit/article.html
 * https://code.tutsplus.com/tutorials/an-introduction-to-volley--cms-23800
 */
public class Main4Activity extends AppCompatActivity {
    EditText editEmail, editPassword, editName;
    Button btnSignIn, btnRegister;
    final String URL = "http://192.168.0.111/login_registration_php_mysql/index.php";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        initViews();
    }

    private void initViews() {
        editEmail = (EditText) findViewById(R.id.editEmail);
        editName = (EditText) findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttemptLogin attemptLogin = new AttemptLogin();
                attemptLogin.execute(editName.getText().toString(), editPassword.getText().toString(), "");
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    i = 1;
                    editEmail.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnRegister.setText(getString(R.string.create_account));
                } else {
                    btnRegister.setText(getString(R.string.register));
                    editEmail.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    i = 0;

//                    usage();

                    AttemptLogin attemptLogin = new AttemptLogin();
                    attemptLogin.execute(editName.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString());
                }
            }
        });

    }

    //https://inducesmile.com/android-programming/how-to-close-or-hide-soft-keyboard-in-android/
    public void closeKeyboard(View v) {
        try {
            InputMethodManager editTextInput = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            editTextInput.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("AndroidView", "closeKeyboard: " + e);
        }
    }

    //    void usage() {
//        HttpUrlConnectionUtlity httpMethod = new HttpUrlConnectionUtlity(getApplicationContext(), new HttpUrlConnectionUtlity.MyCalBack() {
//            @Override
//            public void onSuccess(String response) {
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
//                Log.d("tag", errorMessage);
//            }
//        });
//
////        HttpUrlConnectionUtlity httpMethod = new HttpUrlConnectionUtlity (getApplicationContext());
//        JSONObject jsonEntity = new JSONObject();
//
//        try {
//            jsonEntity.put("username", "uname");
//            jsonEntity.put("password", "pass");
//            jsonEntity.put("email", "mail");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        httpMethod.setUrl(URL);
//        HashMap<String, String> headerMap = new HashMap<>();
//        headerMap.put("key", "hgf");
//        headerMap.put("key1", "poi");
//        httpMethod.setHeaderMap(headerMap);
//        httpMethod.setRequestType(HttpUrlConnectionUtlity.POST_METHOD); //specify POST/GET/DELETE/PUT
//        httpMethod.setEntityString(jsonEntity.toString());
//        httpMethod.execute();
//    }


    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            String email = args[2];
            String password = args[1];
            String name = args[0];

            HashMap<String, String> params = new HashMap<>();
            params.put("username", name);
            params.put("password", password);
            if (email.length() > 0) {
                params.put("email", email);
            }
            return API_Client.performPostCall(URL, params);
        }

        protected void onPostExecute(JSONObject result) {
            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(), result.getString("message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
