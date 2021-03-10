package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import fragment.mineFragment;
import fragment.mypageAdapter;
import fragment.readFragment;

public class containerActivity extends AppCompatActivity {

    TabLayout mtablayout;
    ViewPager mviewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Fragment> list=new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mviewpager=findViewById(R.id.vp_container);
        TabLayout mtablayout=findViewById(R.id.tab_layout);
        list.add(new readFragment());
        list.add(new mineFragment());
        mypageAdapter madapter=new mypageAdapter(getSupportFragmentManager(),list);
        mviewpager.setAdapter(madapter);

        mviewpager.setOffscreenPageLimit(2);
        mviewpager.setCurrentItem(0,false);
//给tablayout添加元素

        for(int i=0;i<2;i++) {
            mtablayout.addTab(mtablayout.newTab().setCustomView(getTabView(this,i)));
        }

        mtablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mviewpager.setCurrentItem(tab.getPosition(),true);
                View view =tab.getCustomView();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });
        mviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtablayout));
    }
    public static View getTabView(Context context, int position){
        int []mTabRes = new int[]{R.drawable.read2,R.drawable.mine1};
        String []mTabTitle = new String[]{"读书","我的"};

        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        TextView tabText = view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        tabText.setTextColor(Color.parseColor("#333333"));
        return view;
    }



}
