package com.aemiot.test.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.aemiot.android.sample.R;
import com.aemiot.test.activity.DevelopProtocolManifestActivity;

public class HomeFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) { 
        return inflater.inflate(R.layout.home_fragment, container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = this.getView();
        ListView listView = (ListView) view.findViewById(R.id.listview);
        
        initData();
        ArrayList<String> nameList = new ArrayList<String>();
        for(Class<?> cls : clsList) {
            nameList.add(cls.getSimpleName());
        }
        
        ListAdapter listAdapter = new ArrayAdapter<String>(this.getActivity().getBaseContext(), android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(listAdapter);
        
        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), clsList.get(position));
                HomeFragment.this.startActivity(intent);
            }
            
        });
    }
    
    private void initData() {
        if(clsList == null)
            clsList = new ArrayList<Class<?>>();
        else
            clsList.clear();
        
        clsList.add(DevelopProtocolManifestActivity.class);
    }
    
    private View view;
    private ArrayList<Class<?>> clsList;
}
