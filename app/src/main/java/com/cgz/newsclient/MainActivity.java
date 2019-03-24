package com.cgz.newsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLLoading;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 开发步骤
        // 1.初始化界面
        // 2.获取新闻信息
        // 3.解析新闻信息->解析为具体的实体对象
        // 4.将实体对象显示到界面

        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        mLLoading = findViewById(R.id.ll_loading); //快捷键 command +option + f
        mLv = findViewById(R.id.lv_news);
        mLLoading.setVisibility(View.VISIBLE);

    }
}
