package com.example.covidapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.covidapp.ui.home.HomeFragment;
import com.example.covidapp.ui.result.ResultFragment;
import com.example.covidapp.ui.test.TestFragment;
import com.example.covidapp.ui.wearable.WearableFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {
    HomeFragment fragment_home;
    WearableFragment fragment_wearable;
    TestFragment fragment_test;
    ResultFragment fragment_result;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment_home = new HomeFragment();
        fragment_wearable = new WearableFragment();
        fragment_test = new TestFragment();
        fragment_result = new ResultFragment();
        
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment_home).commit();




    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "코로나19 정보", Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if (id == R.id.nav_wearable) {
            Toast.makeText(this, "웨어러블 데이터 확인", Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if (id == R.id.nav_test) {
            Toast.makeText(this, "건강 및 증상 설문조사", Toast.LENGTH_LONG).show();
            onFragmentSelected(2, null);
        } else if (id == R.id.nav_result) {
            Toast.makeText(this, "자가진단 결과", Toast.LENGTH_LONG).show();
            onFragmentSelected(3, null);
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {

        if (position == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_home).commit();
            toolbar.setTitle("COVID19");
        } else  if (position == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_wearable).commit();
            toolbar.setTitle("웨어러블 데이터");
        } else  if (position == 2) {
            toolbar.setTitle("건강 및 증상 설문");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_test).commit();
        } else  if (position == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_result).commit();
            toolbar.setTitle("자가진단 결과");
        }
    }


}


