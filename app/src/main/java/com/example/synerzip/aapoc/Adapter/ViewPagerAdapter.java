package com.example.synerzip.aapoc.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.synerzip.aapoc.R;

/**
 * Created by Snehal Tembare on 21/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class ViewPagerAdapter extends PagerAdapter {
    public int[] layouts=new int[]{R.layout.slide_one,R.layout.slide_two,R.layout.slide_three,R.layout.slide_four};
    public LayoutInflater mLayoutInflater;
    public Context mContext;
    public ViewPagerAdapter(Context context){
        mContext=context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
