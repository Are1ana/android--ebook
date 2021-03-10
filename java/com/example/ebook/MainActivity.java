package com.example.ebook;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button mbt1,mbt2;
    EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbt1=findViewById(R.id.mbut1);
        mbt2=findViewById(R.id.mbut2);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        //登录
        mbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname=username.getText().toString();
                String upass=password.getText().toString();
                SharedPreferences sp = getSharedPreferences("data.txt", Context.MODE_PRIVATE);
                String username=sp.getString("name","");
                String password=sp.getString("key","");

                if(!username.equals(uname)||!password.equals(upass)){
                    Toast.makeText(MainActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MainActivity.this,containerActivity.class);
                    startActivity(intent1);
                }

            }
        });
//注册
        mbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}



