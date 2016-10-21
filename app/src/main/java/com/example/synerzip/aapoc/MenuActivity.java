package com.example.synerzip.aapoc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mProgressDialog=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminateDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.facebook));

        mProgressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
            }
        },5000);
    }

    @OnClick(R.id.button_open_bottom_sheet)
    public void openBottomSheet(View v){
        startActivity(new Intent(this,BottomSheetActivity.class));
    }

    @OnClick(R.id.button_snackbar)
    public void openSnackbarActivity(View view){
     startActivity(new Intent(this,SnackBarActivity.class));
    }

    @OnClick(R.id.button_progress)
    public void openProgressIndicatiors(View view){
        startActivity(new Intent(this,ProgressActivity.class));
    }

    @OnClick(R.id.button_introslider)
    public void openIntroslider(View view){
        startActivity(new Intent(this,IntrosliderActivity.class));
    }

}
