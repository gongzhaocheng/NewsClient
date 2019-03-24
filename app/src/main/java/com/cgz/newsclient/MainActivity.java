package com.cgz.newsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cgz.newsclient.domain.NewsItem;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
        loadNewsInfo();
    }

    /**
     * 获取新闻信息
     */
    private void loadNewsInfo() {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.102.115:80/news.xml");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//                    设置一个读取超时时间20秒
                    conn.setReadTimeout(20000);

                    int code = conn.getResponseCode();
                    if (code == 200) {
                        // 请求成功
                        System.out.println("请求成功");
                        InputStream is = conn.getInputStream();

                    } else {
                        // 请求失败
                        System.out.println("请求失败");


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        mLLoading = findViewById(R.id.ll_loading); //快捷键 command +option + f
        mLv = findViewById(R.id.lv_news);
        mLLoading.setVisibility(View.VISIBLE);
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            NewsItem item = new NewsItem();
            item.setTitle("苹果发布了新手机");
            item.setDesc("今天库克发布了新手机，引起了粉丝轰动");
            item.setImagePath("http://192.168.102.115/img/a.jpg");
            item.setCommentCount(200);
            newsItems.add(item);
        }


    }
}
