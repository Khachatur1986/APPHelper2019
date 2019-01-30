package com.example.apphelper2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends ArrayAdapter<MainModel> {
    private List<MainModel> models;
    private int lastPosition = -1;
    private Context context;

    public MainAdapter(Context context, int resource, List<MainModel> objects) {
        super(context, resource, objects);
        this.models = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.row_item_main_adapter, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.iv_Icon = convertView.findViewById(R.id.iv_icon);

            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //anim
        Animation animation = AnimationUtils.loadAnimation(
                context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;

        MainModel model = models.get(position);
        if (model != null) {
            viewHolder.iv_Icon.setImageResource(model.getImageResourceId());
            viewHolder.tv_name.setText(new StringBuilder().append(position + 1).append(") ").append(model.getName()).toString());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView tv_name;
        ImageView iv_Icon;
    }

    public void refreshEvents(List<MainModel> objects) {
        this.models.clear();
        this.models.addAll(objects);
        notifyDataSetChanged();
    }
}
