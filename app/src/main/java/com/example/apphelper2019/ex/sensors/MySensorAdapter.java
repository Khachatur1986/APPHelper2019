package com.example.apphelper2019.ex.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.apphelper2019.R;

import java.util.List;

public class MySensorAdapter extends ArrayAdapter<Sensor> {
    private int textViewResourceId;

    public MySensorAdapter(Context context, int resource, List<Sensor> sensors) {
        super(context, resource, sensors);
        this.textViewResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(textViewResourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_sensor_content = convertView.findViewById(R.id.tv_sensor_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Sensor sensorItem = getItem(position);
        if (sensorItem != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("Name: " + sensorItem.getName() + "\n");
            builder.append("Int Type: " + sensorItem.getType() + "\n");
            builder.append("Vendor: " + sensorItem.getVendor() + "\n");
            builder.append("Version: " + sensorItem.getVersion() + "\n");
            builder.append("Resolution: " + sensorItem.getResolution() + "\n");
            builder.append("Power: " + sensorItem.getPower() + "/mAh\n");
            builder.append("Max range: " + sensorItem.getMaximumRange() + "\n");
            viewHolder.tv_sensor_content.setText(builder.toString());
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_sensor_content;
    }
}
