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

public class CustomProgressDialog extends ProgressDialog {

    private AnimationDrawable animationDrawable;

    @BindView(R.id.imageView)
    public ImageView imageView;

    public CustomProgressDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
    }

    public CustomProgressDialog(Context context) {
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
        setContentView(R.layout.activity_custom_progress_dialog);

        ButterKnife.bind(this);

        imageView.setBackgroundResource(R.drawable.custom_progress_dialog);
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
