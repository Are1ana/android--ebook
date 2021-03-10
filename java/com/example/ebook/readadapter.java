package com.example.ebook;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager.widget.PagerAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class readadapter extends PagerAdapter {
    private PopupWindow popupWindow;
    private View popupView;
    private TranslateAnimation animation;
    private List mCache;
    //每页字数
    private int[] mPage;
    private String mContent;

    TextView textView=null;
    rcontainerActivity mcontext;
    String pagecolor="#00881B";
    int fontnumber=22;


    readadapter(int[] page, String content,rcontainerActivity context) {
        mPage = page;
        mContent = content;
        mcontext=context;
    }
    @Override
    public int getCount() {
        //页数
        return mPage.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
//由page数组截取每一页现实的文字
    private String getText(int page) {
        if (page == 0) {
            return mContent.substring(0, mPage[0]);
        }
        return mContent.substring(mPage[page - 1], mPage[page]);
    }


    @NonNull
    @Override
//给rcontainer添加页面view
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //
        if (mCache == null) {
            mCache = new LinkedList();
        }
        if (mCache.size() > 0) {
            textView = (TextView) mCache.remove(0);
        } else {
            textView = new TextView(container.getContext());
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popmenu();
            }
        });
        textView.setTextSize(fontnumber);
        textView.setBackgroundColor(Color.parseColor(pagecolor));
        textView.setText(getText(position));
        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mCache.add(object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
    //为view设置popupwindow工具栏修改页面属性
    private void popmenu() {

        TextView color_b = null,color_g = null,color_y = null,size_s = null,size_m = null,size_l = null;
            LinearLayout linearLayout=new LinearLayout(mcontext);
            popupView = View.inflate(mcontext, R.layout.poplayout, linearLayout);

            color_b=popupView.findViewById(R.id.color_b);
            color_g=popupView.findViewById(R.id.color_g);
            color_y=popupView.findViewById(R.id.color_y);

            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);
            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow.setOutsideTouchable(true);
            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(200);

        // 在点击之后设置popupwindow的销毁
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow.showAtLocation(mcontext.findViewById(R.id.readpagemain), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView.startAnimation(animation);


        color_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagecolor="#4D4D4D";
                notifyDataSetChanged();

            }
        });
        color_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagecolor="#00881B";
                notifyDataSetChanged();
            }
        });
        color_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagecolor="#998566";
                notifyDataSetChanged();
            }
        });

    }


}