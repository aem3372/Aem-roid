package com.aemiot.test.fragment;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aemiot.android.sample.R;

public class SettingFragment extends Fragment {
    private View view;
    private ListView listView;
    ArrayAdapter<String> listAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) { 
        return inflater.inflate(R.layout.setting_fragment, container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = this.getView();
        
        listView = (ListView)view.findViewById(R.id.listview);
        String[] infos = {"123","234","345"};
        listAdapter = new ArrayAdapter<String>(this.getActivity().getBaseContext(),
                                               android.R.layout.simple_list_item_1,
                                               new ArrayList<String>(Arrays.asList(infos)));
        listView.setAdapter(listAdapter);
    }
}
