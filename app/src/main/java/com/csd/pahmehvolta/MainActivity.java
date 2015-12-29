package com.csd.pahmehvolta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import login.LoginActivity;
import new_volta.NewVoltaActivity;
import user_profile.ProfileActivity;

public class MainActivity extends BaseActivity {

    Button btn;
    Button btnProf;
    Button btnTabs;
    Button btnLogin;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        View customView = getLayoutInflater().inflate(R.layout.action_bar, null);
//        actionBar.setCustomView(customView);
//        Toolbar parent =(Toolbar) customView.getParent();
//        parent.setContentInsetsAbsolute(0, 0);





        btn = (Button) findViewById(R.id.btnNewVolta);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewVoltaActivity.class);
                startActivity(intent);
            }
        });

        btnProf = (Button) findViewById(R.id.btnProf);

        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnTabs = (Button) findViewById(R.id.btnTabs);

        btnTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreeTabbedScreen.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_three_tabbed_screen, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
