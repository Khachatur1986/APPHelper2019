package com.example.apphelper2019.ex.view_design.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apphelper2019.R;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataModels;
    private Context context;

    public MyRecycleViewAdapter(ArrayList<DataModel> dataModels, Context context) {
        this.dataModels = dataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_recycle_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final DataModel dataModel = dataModels.get(i);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(context)
//                .asBitmap()
                .load(dataModel.getImageUrl())
                .apply(requestOptions)
                .into(myViewHolder.iv_recycle_view);

        myViewHolder.tv_recycle_view.setText(dataModel.getImageName());
        myViewHolder.rl_recycle_view_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataModel.getImageName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_recycle_view_row;
        ImageView iv_recycle_view;
        TextView tv_recycle_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_recycle_view_row = itemView.findViewById(R.id.rl_recycle_view___row);
            iv_recycle_view = itemView.findViewById(R.id.iv_recycle___view);
            tv_recycle_view = itemView.findViewById(R.id.tv_recycle___view);
        }
    }
}
