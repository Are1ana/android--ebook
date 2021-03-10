package fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.ebook.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;


public class mineFragment extends Fragment {
    private final String filename="record";
    private PopupWindow mpop;

    private TextView record,news,information,version;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.page_mine,container,false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        record=view.findViewById(R.id.record);
        news=view.findViewById(R.id.news);
        version=view.findViewById(R.id.version);
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"版本1.1.0",Toast.LENGTH_SHORT).show();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"暂无消息",Toast.LENGTH_SHORT).show();

            }
        });
//浏览记录
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view =getLayoutInflater().inflate(R.layout.record_pop,null);
                TextView record_time=view.findViewById(R.id.record_time);
                String string=read("record_name");
                record_time.setText(string);
                mpop=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                mpop.setBackgroundDrawable(new BitmapDrawable());
                mpop.setFocusable(true);
                // 设置点击popupwindow外屏幕其它地方消失
                mpop.setOutsideTouchable(true);
                mpop.showAsDropDown(record);

            }
        });

    }
    private String read(String filename) {
        FileInputStream fileinput = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ebooks",filename);
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

}
