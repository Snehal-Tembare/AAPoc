package com.example.synerzip.aapoc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntrosliderActivity extends AppCompatActivity {

    @BindView(R.id.layout_dots)
    public LinearLayout mDotsLayout;

    public TextView[] dots;
    public int[] layouts;

    @BindView(R.id.btn_next)
    public Button mBtnNext;

    @BindView(R.id.btn_skip)
    public Button mBtnSkip;

    @BindView(R.id.view_pager)
    public ViewPager mViewpager;

    public  ViewPagerAdapter mViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introslider);
        ButterKnife.bind(this);

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


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void addBottomDots(int currentPage) {

        dots = new TextView[layouts.length];

        int activecolor= ContextCompat.getColor(this,R.color.dot_active);
        int inactivecolor= ContextCompat.getColor(this,R.color.dot_inactive);
        mDotsLayout.removeAllViews();
        for (int i = 0; i <dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(inactivecolor);
            mDotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[currentPage].setTextColor(activecolor);
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
                mBtnNext.setText(R.string.next);
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
            View view= (View) object;
            container.removeView(view);
        }
    }


}
