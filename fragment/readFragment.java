package fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook.R;
import com.example.ebook.rcontainerActivity;
import com.example.ebook.readActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class readFragment extends Fragment {

    public RecyclerView review;
    private final String filename="record";
    private SharedPreferences mshare;
    private SharedPreferences.Editor meditor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.page_read,container,false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        return view;

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int []book_image=new int[]{R.drawable.lryh1,R.drawable.sy1,R.drawable.zzyhp1,R.drawable.scj1};
        final String []book_name = new String[]{"老人与海","鼠疫","战争与和平","双城记"};

        review=view.findViewById(R.id.rev);
        review.setLayoutManager(new GridLayoutManager(getActivity(),2));

        review.setAdapter(new read_recycleadapter(getActivity(), new read_recycleadapter.onitemlistener() {
            @Override
            public void onClick(int positon) {
//获取时间
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY)+8;
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                save("record_name",book_name[positon]+" ");
                save("record_name",year+"年"+month+"月"+day+"日"+hour+":"+minute+":"+second+" ");
                //save("record_name"," ");

                Intent intent = new Intent(getActivity(), readActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("name",book_name[positon]);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        },book_image,book_name));

        
    }
//存储浏览记录至record
    private void save(String filename,String content){
        FileOutputStream fileOutputStream=null;
        try {
            File dir=new File(Environment.getExternalStorageDirectory(),"ebooks");
            if(!dir.exists()){
                dir.mkdirs();
            }
            if(dir.exists())
            {
                Log.v("ebooks","success");

            }
            File file =new File(dir ,filename);
            if(!file.exists()){
                file.createNewFile();
                Log.v("ebooksfile","success");

            }
            fileOutputStream=new FileOutputStream(file,true);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.write("\r\n".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
