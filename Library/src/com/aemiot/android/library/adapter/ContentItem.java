package com.aemiot.android.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ContentItem extends ListItem {

    private String item;
    public ContentItem(String item){
        this.item = item;
    }
     
    @Override
    public int getLayout() {
        return android.R.layout.simple_list_item_1;
    }
 
    @Override
    public boolean isClickable() {
        return true;
    }
 
    @Override
    public View getView(Context context, View convertView, LayoutInflater inflater) {
        convertView = inflater.inflate(getLayout(), null);
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(item);
        return convertView;
    }

}
