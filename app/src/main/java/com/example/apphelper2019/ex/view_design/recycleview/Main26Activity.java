package com.example.apphelper2019.ex.view_design.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.apphelper2019.R;

import java.util.ArrayList;

/*
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.github.bumptech.glide:glide:4.9.0'
 */
//https://youtu.be/Vyqz_-sJGFk
//https://github.com/mitchtabian/Recyclerview/blob/master/RecyclerViewStaggered/app/src/main/java/codingwithmitch/com/recyclerviewstaggered/MainActivity.java
public class Main26Activity extends AppCompatActivity {
    private static final int NUM_COLUMNS = 2;
    private ArrayList<DataModel> dataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);
        initDataModels();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.rv_recycle_view___ex);
        MyRecycleViewAdapter recyclerViewAdapter = new MyRecycleViewAdapter(dataModels, this);
        recyclerView.setAdapter(recyclerViewAdapter);

//        android:orientation = "horizontal"
//        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

//        android:orientation = "vertical"
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);

//        android:orientation = "vertical"
//        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initDataModels() {
        DataModel dataModel_1 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/opera-50-update-570x321.jpg");
        DataModel dataModel_2 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/opera-50-update.jpg");
        DataModel dataModel_3 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Galaxy-s10-570x321.jpg");
        DataModel dataModel_4 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Galaxy-s10.jpg");
        DataModel dataModel_5 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/password-crack-570x321.jpg");
        DataModel dataModel_6 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/password-crack.jpg");
        DataModel dataModel_7 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Super-Smash-Flash-2-570x244.png");
        DataModel dataModel_8 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Super-Smash-Flash-2-1024x438.png");
        DataModel dataModel_9 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Samsung-Galaxy-A50-1-570x321.png");
        DataModel dataModel_10 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Samsung-Galaxy-A50-1.png");
        DataModel dataModel_11 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2017/07/view-instagram-profile-images-570x380.jpg");
        DataModel dataModel_12 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2017/07/view-instagram-profile-images.jpg");
        DataModel dataModel_13 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Moto-G7-Power-570x321.png");
        DataModel dataModel_14 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/Moto-G7-Power.png");
        DataModel dataModel_15 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/whatsapp-update-570x517.png");
        DataModel dataModel_16 = new DataModel("test", "https://www.geekdashboard.com/wp-content/uploads/2019/02/whatsapp-update.png");
        dataModels.add(dataModel_1);
        dataModels.add(dataModel_2);
        dataModels.add(dataModel_3);
        dataModels.add(dataModel_4);
        dataModels.add(dataModel_5);
        dataModels.add(dataModel_6);
        dataModels.add(dataModel_7);
        dataModels.add(dataModel_8);
        dataModels.add(dataModel_9);
        dataModels.add(dataModel_10);
        dataModels.add(dataModel_11);
        dataModels.add(dataModel_12);
        dataModels.add(dataModel_13);
        dataModels.add(dataModel_14);
        dataModels.add(dataModel_15);
        dataModels.add(dataModel_16);
    }
}
