package com.aemiot.android.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ListItem {
    public abstract int getLayout();
    public abstract boolean isClickable();
    public abstract View getView(Context context, View convertView, LayoutInflater inflater);
}