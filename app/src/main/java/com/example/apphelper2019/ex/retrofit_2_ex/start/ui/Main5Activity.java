package com.example.apphelper2019.ex.retrofit_2_ex.start.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apphelper2019.R;
import com.example.apphelper2019.ex.retrofit_2_ex.start.api.model.GitHubRepo;
import com.example.apphelper2019.ex.retrofit_2_ex.start.api.service.GitHubClient;
import com.example.apphelper2019.ex.retrofit_2_ex.start.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://habr.com/ru/post/314028/
//http://www.vogella.com/tutorials/Retrofit/article.html
//https://www.youtube.com/watch?v=R4XU8yPzSx0&list=PLpUMhvC6l7APq7y_FFfK-GEHvcUKqo6SC
//https://github.com/futurestudio/android-retrofit-video/blob/master/app/src/main/java/io/futurestud/retrofit1/ui/MainActivity.java
public class Main5Activity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listView = findViewById(R.id.pagination_list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call = client.reposForUser("khachatur1986");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                listView.setAdapter(new GitHubRepoAdapter(Main5Activity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(Main5Activity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
