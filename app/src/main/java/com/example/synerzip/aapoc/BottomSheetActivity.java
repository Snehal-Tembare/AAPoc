package com.example.synerzip.aapoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.synerzip.aapoc.Adapter.BottomSheetAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Snehal Tembare on 17/10/16.
 * Copyright © 2016 Synerzip. All rights reserved
 */
public class BottomSheetActivity extends AppCompatActivity {


    @BindView(R.id.button_modal_sheet)
    public Button mBtnModalSheet;

    @BindView(R.id.bottom_sheet)
    public GridView mGridBottomSheet;

    @BindView(R.id.persistent_sheet)
    public View mPersistentSheet;

    @BindView(R.id.coordinatorlayout)
    public CoordinatorLayout coordinatorLayout;

    BottomSheetAdapter bottomSheetAdapter;
    BottomSheetBehavior sheetBehavior;
    BottomSheetBehavior behavior;

    private int fileSize=0;

    private Integer[] bottomItems = {R.drawable.google_plus, R.drawable.gmail, R.drawable.facebook, R.drawable.facebook_messenger, R.drawable.plus};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);

        //Set bottomsheet adapter(GridView)
        bottomSheetAdapter = new BottomSheetAdapter(this, R.layout.grid_item, bottomItems);
        mGridBottomSheet.setAdapter(bottomSheetAdapter);

        mGridBottomSheet.setTranslationY(getStatusBarHeight());

        sheetBehavior = BottomSheetBehavior.from(mGridBottomSheet);
        behavior = BottomSheetBehavior.from(mPersistentSheet);

        coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        mBtnModalSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        mPersistentSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d("MainActivity", "onStateChanged: " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("MainActivity", "onSlide: ");
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first=true;
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });

        mGridBottomSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @OnClick(R.id.button_persistent_sheet)
    public void openPersistentBottomSheet() {
        mPersistentSheet.setVisibility(View.VISIBLE);
        if (sheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (behavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);}
        else {
            super.onBackPressed();
        }
    }

}
