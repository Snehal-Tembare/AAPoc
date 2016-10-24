package com.example.synerzip.aapoc;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.layout_chat_parent)
    public RelativeLayout mLayoutParent;
    public FloatingActionMenu floatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        mLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floatingActionMenu.isOpen()){
                    floatingActionMenu.close(true);
                }
            }
        });

        //Create Icon
        ImageView icon=new ImageView(this);
        icon.setImageResource(R.drawable.ic_plus);
        FloatingActionButton floatingActionButton=new FloatingActionButton
                .Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder subActionButton=new SubActionButton.Builder(this);

        ImageView item1=new ImageView(this);
        item1.setImageResource(R.drawable.gmail);

        ImageView item2=new ImageView(this);
        item2.setImageResource(R.drawable.facebook_messenger);

        ImageView item3=new ImageView(this);
        item3.setImageResource(R.drawable.facebook);

        SubActionButton subButton1=subActionButton.setContentView(item1).build();
        SubActionButton subButton2=subActionButton.setContentView(item2).build();
        SubActionButton subButton3=subActionButton.setContentView(item3).build();

        //attach the sub buttons to the main button
        floatingActionMenu=new FloatingActionMenu
                .Builder(this)
                .addSubActionView(subButton1)
                .addSubActionView(subButton2)
                .addSubActionView(subButton3)
                .attachTo(floatingActionButton)
                .build();

        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked Gmail",Toast.LENGTH_SHORT).show();
            }
        });

        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked mesenger",Toast.LENGTH_SHORT).show();
            }
        });

        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked facebook",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
