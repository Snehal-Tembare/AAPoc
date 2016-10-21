package com.example.synerzip.aapoc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.synerzip.aapoc.R;

/**
 * Created by Snehal Tembare on 17/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class BottomSheetAdapter extends ArrayAdapter<Integer>{
    private Activity mContext;

    public BottomSheetAdapter(Activity context, int grid_item, Integer[] bottomItems) {
        super(context,grid_item,bottomItems);
        mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            LayoutInflater inflator= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.grid_item,parent,false);
            holder=new ViewHolder();

            holder.imageview= (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.imageview.setImageResource(getItem(position));
        return convertView;
    }

    public class ViewHolder{
        ImageView imageview;
    }
}
