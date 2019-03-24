package com.cgz.newsclient;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cgz.newsclient.domain.NewsItem;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLLoading;
    private ListView mLv;
    private ArrayList<NewsItem> mNewsItems;

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


                        mNewsItems = NewsInfoParser.getAllNewsInfos(is);
                        System.out.println(mNewsItems);

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
        mNewsItems = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            NewsItem item = new NewsItem();
            item.setTitle("苹果发布了新手机");
            item.setDesc("今天库克发布了新手机，引起了粉丝轰动");
            item.setImagePath("http://192.168.102.115/img/a.jpg");
            item.setCommentCount(200);
            item.setType("3");
            mNewsItems.add(item);
        }
        //设置ListView的数据
        mLv.setAdapter(new MyNewsAdapter());
    }

    private class MyNewsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNewsItems.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.news_item, null);

            //千万要注意，一定要使用view点，默认是使用当前activity
            ImageView iv = view.findViewById(R.id.iv_img);
            TextView tv_title = view.findViewById(R.id.tv_title);
            TextView tv_desc =  view.findViewById(R.id.tv_desc);
            TextView tv_type =  view.findViewById(R.id.tv_type);

            tv_title.setText(mNewsItems.get(position).getTitle());
            tv_desc.setText(mNewsItems.get(position).getDesc());
            String type = mNewsItems.get(position).getType();

            if ("1".equals(type)) {
                tv_type.setText("评论："+ mNewsItems.get(position).getCommentCount());
                tv_type.setBackgroundColor(Color.TRANSPARENT);
                tv_type.setTextColor(Color.BLACK);
            } else if ("2".equals(type)){
                tv_type.setText("专题");
                tv_type.setBackgroundColor(Color.RED);
                tv_type.setTextColor(Color.WHITE);
            } else if ("3".equals(type)){
                tv_type.setText("直播");
                tv_type.setBackgroundColor(Color.BLUE);
                tv_type.setTextColor(Color.WHITE);
            }


            // 千万要注意！！！
            return view;
        }

        @Override
        public NewsItem getItem(int position) {
            return mNewsItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position ;
        }


    }
}
