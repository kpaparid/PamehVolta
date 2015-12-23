package com.csd.pahmehvolta;


import android.app.Activity;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

/**
 * Created by sAnideos on 23/12/2015.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.action_bar, null);
        actionBar.setCustomView(customView);
        Toolbar parent =(Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        return false;
    }
}
