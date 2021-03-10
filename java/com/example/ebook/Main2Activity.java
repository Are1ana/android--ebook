package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
    Button mbt;
    EditText name,key,confirm,mail;
    private final String filename="data.txt";
    private SharedPreferences mshare;
    private SharedPreferences.Editor meditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mbt=findViewById(R.id.btn_register);
        name=findViewById(R.id.name);
        key=findViewById(R.id.key);
        confirm=findViewById(R.id.confirm);
        mail=findViewById(R.id.mail);

        mshare=getSharedPreferences(filename,MODE_PRIVATE);
        meditor=mshare.edit();
//将所注册信息存入data
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meditor.putString("name",name.getText().toString());
                meditor.putString("mail",mail.getText().toString());
                String password=key.getText().toString();//第一次输入的密码赋值给password
                String password2=confirm.getText().toString();//第二次输入的密码赋值给password2

                if (password.equals("")||password2.equals("")){	//判断两次密码是否为空
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password.equals(password2)){
                    Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();
                    meditor.putString("key",key.getText().toString());
                    Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if (!password.equals(password2) ){
                    Toast.makeText(getApplication(),"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }
                meditor.apply();

            }
        });

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }


}
