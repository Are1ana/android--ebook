package com.example.ebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fragment.mypageAdapter;
import fragment.readpagefragment;

public class rcontainerActivity extends AppCompatActivity  {
    ViewPager mviewpager;
    int pages[];
    String txt;
    String bookname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcontainer);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        mviewpager=findViewById(R.id.read_container);

        Bundle bundle=getIntent().getExtras();
        //得到页面数组，书名
        if(bundle != null) {
            //bookname=bundle.getString("name");
            pages=bundle.getIntArray("pages");
            bookname=bundle.getString("name");
            Log.v("rcontainerpage", String.valueOf(pages.length));

        }
        txt=read(bookname+".txt");

        readadapter readadapter=new readadapter(pages,txt,this);
        mviewpager.setAdapter(readadapter);

    }
//重写返回键，返回到container
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(rcontainerActivity.this,containerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

//读取
    private String read(String filename){

        FileInputStream fileinput =null;
        try {
//            fileinput=openFileInput(filename);
            File file=new File(Environment.getExternalStorageDirectory(),filename);
            fileinput=new FileInputStream(file);
            byte[]buff=new byte[1024];
            StringBuilder sb=new StringBuilder("");
            int len=0;
            while((len=fileinput.read(buff))>0){
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (IOException e) {

            e.printStackTrace();
        }finally {
            if(fileinput!=null){
                try {
                    fileinput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}




