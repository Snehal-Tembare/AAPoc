package com.example.synerzip.aapoc;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Snehal Tembare on 24/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class TransparentDialog extends Dialog {
    private ImageView iv;
    private TextView textView;

    public TransparentDialog(Context context, int themeResId) {
        super(context, R.style.TransparentProgressDialog);

        WindowManager.LayoutParams layoutParams= getWindow().getAttributes();
        layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(layoutParams);

        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);

        LinearLayout layout=new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        iv=new ImageView(context);
        textView=new TextView(context);

        iv.setImageResource(themeResId);
        textView.setText(R.string.please_wait);
        textView.setTextColor(Color.BLACK);

        layout.addView(iv,params);
        layout.addView(textView,params);

        addContentView(layout,params);

    }

    @Override
    public void show() {
        super.show();
        RotateAnimation rotateAnim=new RotateAnimation(0.0f,360.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnim.setInterpolator(new LinearInterpolator());
        rotateAnim.setRepeatCount(Animation.INFINITE);
        rotateAnim.setDuration(5000);
        iv.setAnimation(rotateAnim);
        iv.startAnimation(rotateAnim);

    }
}
