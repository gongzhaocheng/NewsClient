package com.cgz.newsclient;

import android.text.TextUtils;
import android.util.Xml;

import com.cgz.newsclient.domain.NewsItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * 用来解析新闻的信息
 * @author cgz
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
class NewsInfoParser {
    public static ArrayList<NewsItem> getAllNewsInfos(InputStream is) throws Exception {

        ArrayList<NewsItem> newsItems = null;
        NewsItem newsItem = null;
        // 1.获取xml文件的解析器
        XmlPullParser parser = Xml.newPullParser();
        // 2.初始化xml文件解析器
        parser.setInput(is,"utf-8");//向上抛出异常，被调用类捕获。
        
        // 3.解析xml文件
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("channel".equals(parser.getName())) {
                        // 说明解析到了最根部的标签节点
                        newsItems = new ArrayList<>();
                    } else if ("item".equals(parser.getName())){
                        newsItem = new NewsItem();
                    } else if ("title".equals(parser.getName())){
                        newsItem.setTitle(parser.nextText());
                    } else if ("description".equals(parser.getName())) {
                        newsItem.setDesc(parser.nextText());
                    }else if ("image".equals(parser.getName())){
                            newsItem.setImagePath(parser.nextText());
                    }else if ("title".equals(parser.getName())) {
                        newsItem.setTitle(parser.nextText());
                    }else if ("comment".equals(parser.getName())){
                        String countstr = parser.nextText();  //防御式编程
                        if (TextUtils.isDigitsOnly(countstr)) {
                            newsItem.setCommentCount(Integer.parseInt(countstr));
                        }
                    }
                    
                    break;
                    
                case XmlPullParser.END_TAG:
                    // 如果是走到了一个item的结束标签，说明一个item的数据已经准备好了，要添加到集合当中
                    if ("item".equals(parser.getName())) {
                        newsItems.add(newsItem);
                    }
                    break;
            }
            //千万注意记得写这一句，负否则会死循环
            type = parser.next();
        }

        return newsItems ;
        
    }
}
