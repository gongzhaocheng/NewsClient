package com.cgz.newsclient.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author chenggongzhao
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SmartImageView extends android.support.v7.widget.AppCompatImageView {


    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitMao = (Bitmap) msg.obj;
            setImageBitmap(bitMao);
        }
    };

    public SmartImageView(Context context) {
        super(context);
    }

    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置一个网络图片并显示到控件
     * @param imagePath
     */
    public void setImageUrlAndShow(final String imagePath) {
        new Thread(){
            @Override
            public void run() {

                try {
                    URL url = new URL(imagePath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Message msg = Message.obtain();
                    msg.what = 0;
                    msg.obj = bitmap;
                    mhandler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
