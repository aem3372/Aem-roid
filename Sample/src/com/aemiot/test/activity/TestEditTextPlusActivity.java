package com.aemiot.test.activity;

import android.app.Activity;
import android.os.Bundle;

import com.aemiot.android.sample.R;

public class TestEditTextPlusActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.setContentView(R.layout.test_edittextplus);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }   
}
