package com.example.synerzip.aapoc;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsibleToolbarActivity extends AppCompatActivity {


    @BindView(R.id.collapsible_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsible_toolbar);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setTitle("My Collapsible Title");
    }
}
