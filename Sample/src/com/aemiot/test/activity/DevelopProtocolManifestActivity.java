package com.aemiot.test.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;

import com.aemiot.android.library.adapter.CategoryAdapter;
import com.aemiot.android.sample.R;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;

public class DevelopProtocolManifestActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.setContentView(R.layout.develop_protocolmanifest);
        
        listView = (SwipeMenuListView) findViewById(R.id.stringList);
        
        
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                case 0:
                    // create menu of type 0
                    break;
                case 1:
                    // create item
                    SwipeMenuItem modifyItem = new SwipeMenuItem(DevelopProtocolManifestActivity.this); 
                    modifyItem.setBackground(new ColorDrawable(
                            DevelopProtocolManifestActivity.this.getResources().getColor(R.color.gray)));
                    modifyItem.setWidth(dp2px(90));
                    modifyItem.setTitle("修改");
                    modifyItem.setTitleSize(18);
                    modifyItem.setTitleColor(Color.WHITE);

                    menu.addMenuItem(modifyItem);

                    // create item
                    SwipeMenuItem confirmItem = new SwipeMenuItem(DevelopProtocolManifestActivity.this);
                    confirmItem.setBackground(new ColorDrawable(
                            DevelopProtocolManifestActivity.this.getResources().getColor(R.color.red)));
                    confirmItem.setWidth(dp2px(90));
                    confirmItem.setTitle("确认");
                    confirmItem.setTitleSize(18);
                    confirmItem.setTitleColor(Color.WHITE);
                    
                    menu.addMenuItem(confirmItem);
                    break;
                }
            }

            private int dp2px(int dp) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                        getResources().getDisplayMetrics());
            }
        };
        
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                case 0:
                    // open
                    break;
                case 1:
                    // delete
                    break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        
        String words1[] = { "联发新天地 东区1号门 摄像机无图像 普通",
                            "国贸阳光 1号楼 门口机无法呼叫 紧急",
                            "联发新天地 消控中心 3号机无录像 普通",
                            "联发新天地 东区5号门 无法刷卡 普通"};
        String words2[] = { "幸福第二城 北区2号门 摄像机无图像 普通",
                            "幸福第二城 东区2号门 摄像机无图像 普通"};
        CategoryAdapter adapter = new CategoryAdapter(this);
        adapter.addCategoryData("2015/04", words1);
        adapter.addCategoryData("2015/03", words2);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    private SwipeMenuListView listView;
}
