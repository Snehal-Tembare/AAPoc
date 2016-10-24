package com.example.synerzip.aapoc;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snehal Tembare on 24/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class CustomDialog extends ProgressDialog {

    private AnimationDrawable animationDrawable;

    @BindView(R.id.imageView)
    public ImageView imageView;

    /*public CustomDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
    }*/

    public CustomDialog(Context context) {
        super(context);
    }

    public static ProgressDialog customProgressDialog(Context context) {
        CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
        customProgressDialog.setIndeterminate(true);
        customProgressDialog.setCancelable(false);
        return customProgressDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_dialog);

        ButterKnife.bind(this);

        imageView.setBackgroundResource(R.drawable.custom_progress_white);
        animationDrawable= (AnimationDrawable) imageView.getBackground();


    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animationDrawable.stop();
    }
}
