package com.example.synerzip.aapoc;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.synerzip.aapoc.R.string.next;

public class IntrosliderActivity extends AppCompatActivity {
    public LinearLayout mDotsLayout;
    public TextView[] dots;
    public int[] layouts;
    public Button mBtnNext;
    public Button mBtnSkip;
    public ViewPager mViewpager;
    public  ViewPagerAdapter mViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introslider);
        mDotsLayout= (LinearLayout) findViewById(R.id.layout_dots);
        mBtnNext= (Button) findViewById(R.id.btn_next);
        mBtnSkip= (Button) findViewById(R.id.btn_skip);
        mViewpager= (ViewPager) findViewById(R.id.view_pager);

        layouts=new int[]{R.layout.slide_one,R.layout.slide_two,R.layout.slide_three,R.layout.slide_four};

        addBottomDots(0);

        changeStatusBarColor();

        mViewpagerAdapter=new ViewPagerAdapter();
        mViewpager.setAdapter(mViewpagerAdapter);
        mViewpager.addOnPageChangeListener(viewPagerPageChangeListener);

        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current=getItem(+1);

                if (current<layouts.length){
                    mViewpager.setCurrentItem(current);
                }else {
                    launchHomeScreen();
                }

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor() {
        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }

    private void addBottomDots(int currentPage) {

        dots = new TextView[layouts.length];

        int[] activeColors = new int[]{R.array.array_dot_active};
        int[] inActiveColors = new int[]{R.array.array_dot_inactive};
        mDotsLayout.removeAllViews();
        for (int i = 0; i <dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(inActiveColors[currentPage]);
            mDotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[currentPage].setTextColor(activeColors[currentPage]);
        }
    }
        private int getItem(int i){
            return mViewpager.getCurrentItem() + i;    }

    private void launchHomeScreen(){
        startActivity(new Intent(this,MenuActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);
            if (position==layouts.length-1){
                mBtnNext.setText(R.string.start);
                mBtnSkip.setVisibility(View.GONE);
            }else {
                mBtnNext.setText(next);
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public class ViewPagerAdapter extends PagerAdapter {

        public LayoutInflater mLayoutInflater;

        public ViewPagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mLayoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=mLayoutInflater.inflate(layouts[position],container,false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            View view= (View) object;
            container.removeView(view);
        }
    }


}
