package com.example.ebook;

import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import android.os.Environment;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class readActivity extends Activity {
    TextView mtv;
    String filename;
    String txt;
    public  static  int []number_24sp;

    Paint paint = new Paint();
    String bookname;
    int onelineheight_24sp=85;
    int onelineheight_22sp=78;
    int onelineheight_20sp=77;

    int []fontsize={20,22,24};

    int realheight_24sp=74;
    int realheight_22sp=68;
    int realheight_20sp=62;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        mtv = findViewById(R.id.tv);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bookname = bundle.getString("name");
            Log.v("readactivity", bookname);
        }
        txt = read(bookname + ".txt");
       mtv.setText(txt);

        //获取字符高度
//       mtv.measure(0,0);
//        Paint paint=mtv.getPaint();
//        Log.v("readactivityheight", String.valueOf(paint.descent()-paint.ascent()));

    }
    //重写onstart传递书名页数数组给rcontainer
    @Override
    protected void onStart() {
        super.onStart();
        final TextView textView = findViewById(R.id.tv);
        textView.post(new Runnable() {
            @Override
            public void run() {

                //获取字符+间隔高度
//                textView.measure(0,0);
//                float  onelineheight=textView.getMeasuredHeight();
//                Log.v("onelineheight", String.valueOf(onelineheight));
                number_24sp = getPage(textView,realheight_22sp);
                Intent intent  =new Intent(readActivity.this,rcontainerActivity.class);
                Bundle bundle=new Bundle();
                bundle.putIntArray("pages",number_24sp);
                bundle.putString("name",bookname);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //读取txt文件
    private String read(String filename) {
        FileInputStream fileinput = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            fileinput = new FileInputStream(file);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder("");
            int len = 0;
            while ((len = fileinput.read(buff)) > 0) {
                sb.append(new String(buff, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (fileinput != null) {
                try {
                    fileinput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
//得到page数组
    public int[] getPage(TextView textView,int realheight) {
        //获得总行数
        int count = textView.getLineCount();
        Log.v("readactivitycount", String.valueOf(count));

        //每页行数
        int h = textView.getHeight();
        int pCount = h/ realheight;
        Log.v("readactivityheight", String.valueOf(h));
        Log.v("readactivitypcount", String.valueOf(pCount));

        //页数
        int pageNum = count / pCount;
        Log.v("readactivitynumb", String.valueOf(pageNum));

        //每页字数
        int page[] = new int[pageNum];
        for (int i = 0; i < pageNum; i++) {
            page[i] = textView.getLayout().getLineEnd((i+1)*pCount-1);
            Log.v("readactivitypage", String.valueOf(page[i]));

        }
        return page;
    }
}

