package com.aemiot.android.library.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CategoryAdapter extends BaseAdapter {
    
    public CategoryAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    
    public void addCategoryData(String category, String[] datas) {
        items.add(new LabelItem(category));
        for(String data : datas) {
            items.add(new ContentItem(data));
        }
    }
    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
     
    @Override
    public boolean isEnabled(int position) {
        return items.get(position).isClickable();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return items.get(position).getView(context, convertView, inflater);
    }
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof LabelItem)
            return 0;
        return 1;
    }
    
    private List<ListItem> items = new ArrayList<ListItem>();
    private Context context;
    private LayoutInflater inflater;
}
