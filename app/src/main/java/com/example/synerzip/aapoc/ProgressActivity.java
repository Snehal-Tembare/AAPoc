package com.example.synerzip.aapoc;

import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgressActivity extends AppCompatActivity {
    public ProgressBar mProgressBar;
    private int mProgressStatus = 0;
    private int fileSize;
    public Handler handler;
    private AnimationDrawable loadingViewAnim=null;


    public ProgressDialog mProgressDialog;

    public ProgressDialog mCustomProgressDialog;

    public TransparentDialog mTransparentProgressDialog;

    public CustomDialog customDialog;

    @BindView(R.id.image_progress_dialog)
    public ImageView progressImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);
        handler=new Handler();
        mProgressDialog=new ProgressDialog(ProgressActivity.this);
        mCustomProgressDialog=new CustomProgressDialog(this,R.color.colorPrimary);

        //customDialog=new CustomDialog(this);



        mTransparentProgressDialog=new TransparentDialog(this,R.drawable.ic_spinner_frame_48);

        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mProgressDialog.dismiss();
            }
        },2000);
    }

    @OnClick(R.id.button_show_progressbar)
    public void showProgressBar(View view){
        mProgressBar= (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus<1000){
                    mProgressStatus=doOperation();
                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }

            }
        }).start();

    }
    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 100) {
                return 10;
            } else if (fileSize == 200) {
                return 20;
            } else if (fileSize == 300) {
                return 30;
            } else if (fileSize == 400) {
                return 40;
            }else if (fileSize == 500) {
                return 50;
            }else if (fileSize == 600) {
                return 60;
            }else if (fileSize == 700) {
                return 70;
            }else if (fileSize == 800) {
                return 80;
            }else if (fileSize == 900) {
                return 90;
            }else if (fileSize == 1000) {
                return 100;
            }
        }//end of while
        return 100;
    }//end of doOperation

    @OnClick(R.id.button_show_custom_progress_dialog)
    public void showCustomDialog(){

        mCustomProgressDialog.show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mCustomProgressDialog.dismiss();
            }
        },10000);
    }

    @OnClick(R.id.button_show_transparent_progress_dialog)
    public void showTransparentDialog(){
        mTransparentProgressDialog.show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTransparentProgressDialog.dismiss();
            }
        },5000);

    }

    @OnClick(R.id.button_image_dialog)
    public void showImageDialog(){
        progressImage.setBackgroundResource(R.drawable.custom_progress_white);
        loadingViewAnim = (AnimationDrawable) progressImage.getBackground();

        loadingViewAnim.start();


        progressImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingViewAnim.stop();
            }
        },1000);
    }

}
