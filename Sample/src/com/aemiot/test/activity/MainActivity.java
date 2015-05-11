package com.aemiot.test.activity;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.aemiot.android.sample.R;
import com.aemiot.test.fragment.HomeFragment;
import com.aemiot.test.fragment.SettingFragment;
import com.aemiot.test.fragment.TestFragment;

public class MainActivity extends FragmentActivity{
    
    private ViewPager pager;
    private PagerTabStrip tabStrip;
    ArrayList<Fragment> fragmentContainter = new ArrayList<Fragment>();
    ArrayList<String> titleContainer = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.setContentView(R.layout.main);
        Log.d("md", "onCreate");
        
        pager = (ViewPager)findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip)findViewById(R.id.tabstrip);
        
        tabStrip.setDrawFullUnderline(true);
        tabStrip.setBackgroundColor(this.getResources().getColor(android.R.color.black));
        tabStrip.setTabIndicatorColor(this.getResources().getColor(android.R.color.white));
        tabStrip.setTextColor(this.getResources().getColor(android.R.color.white));
        
        fragmentContainter.add(new HomeFragment());
        fragmentContainter.add(new TestFragment());
        fragmentContainter.add(new SettingFragment());
        
        titleContainer.add(HomeFragment.class.getSimpleName());
        titleContainer.add(TestFragment.class.getSimpleName());
        titleContainer.add(SettingFragment.class.getSimpleName());
        
        pager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentContainter.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragmentContainter.get(arg0);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
            
        });
        pager.setOffscreenPageLimit(fragmentContainter.size());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("md", "ConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("md", "onDestroy");
    }
    
}
