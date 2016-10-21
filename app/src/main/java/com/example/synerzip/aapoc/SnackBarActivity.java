package com.example.synerzip.aapoc;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.ButterKnife;

/**
 * Created by Snehal Tembare on 18/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class SnackBarActivity extends AppCompatActivity {

    public RelativeLayout mLayoutSnackParent;
    public Button mBtnShowSimpleSnackbar;
    public Button mBtnShowActionSnackbar;
    public Button mBtnShowCustomeSnackbar;
    public Snackbar snackbar;

    //Floating action button
    public FloatingActionsMenu mFloatingMenu;
    public FloatingActionButton mFabCall;
    public FloatingActionButton mFabChat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);

        mLayoutSnackParent= (RelativeLayout) findViewById(R.id.layout_snack_parent);
        mBtnShowSimpleSnackbar = (Button) findViewById(R.id.button_simple_snackbar);
        mBtnShowActionSnackbar = (Button) findViewById(R.id.button_action_snackbar);
        mBtnShowCustomeSnackbar = (Button) findViewById(R.id.button_custom_snackbar);
        mFloatingMenu = (FloatingActionsMenu)findViewById(R.id.floating_menu);

        mFabCall = (FloatingActionButton) findViewById(R.id.fab_call);
        mFabChat = (FloatingActionButton) findViewById(R.id.fab_chat);

        mLayoutSnackParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFloatingMenu.isExpanded())
                {
                    mFloatingMenu.collapse();
                }
            }
        });

        //Simple SnackBar
        mBtnShowSimpleSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFloatingMenu.isExpanded())
                {
                    mFloatingMenu.collapse();
                }

                snackbar = Snackbar.make(mFloatingMenu, "Welcome Welcome Welcome WelcomeWelcome WelcomeWelcomeWelcomeWelcomeWelcomeWelcome", Snackbar.LENGTH_SHORT);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();}

        });

        //Snackbar with action
        mBtnShowActionSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFloatingMenu.isExpanded())
                {
                    mFloatingMenu.collapse();
                }
                snackbar = Snackbar.make(mFloatingMenu, "Message deleted", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar = Snackbar.make(v, "Message Restored", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });

                snackbar.show();
            }
        });

        //Custom Snackbar
        mBtnShowCustomeSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFloatingMenu.isExpanded())
                {
                    mFloatingMenu.collapse();
                }
                snackbar = Snackbar.make(mFloatingMenu, "No Internet connection", Snackbar.LENGTH_SHORT).setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                //Changing action text color
                snackbar.setActionTextColor(Color.RED);

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
                sbView.findViewById(R.id.snackbar_action);
                textView.setTextColor(Color.GREEN);
                snackbar.show();
            }
        });

        mFabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("000-000-0000"));
                try{
                    startActivity(phoneIntent);
                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"Activity is not founded",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mFabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent=new Intent(SnackBarActivity.this,ChatActivity.class);
                if (mFloatingMenu.isExpanded())
                {
                    mFloatingMenu.collapse();
                }
                startActivity(chatIntent);


            }
        });

    }
}
