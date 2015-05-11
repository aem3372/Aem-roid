package com.aemiot.android.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aemiot.android.library.R;

public class LabelItem extends ListItem {

    private String label;
    
    public LabelItem(String label) {
        this.label = label;
    }

    @Override
    public int getLayout() {
        return R.layout.label_layout;
    }
 
    @Override
    public boolean isClickable() {
        return false;
    }

    @Override
    public View getView(Context context, View convertView, LayoutInflater inflater) {
        convertView = inflater.inflate(getLayout(), null);
        TextView title = (TextView) convertView;
        title.setText(label);
        title.setEnabled(false);
        return convertView;
    }
}
