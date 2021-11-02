package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.ui.result.ResultActivity;
import com.example.covidapp.ui.test.TestActivity;
import com.example.covidapp.ui.wearable.WearableActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=");


        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    Toast.makeText(MainActivity.this, "코로나19 정보", Toast.LENGTH_LONG).show();
                    break;
                case R.id.nav_wearable:
                    Toast.makeText(MainActivity.this, "웨어러블 데이터 확인", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, WearableActivity.class));
                    break;
                case R.id.nav_test:
                    Toast.makeText(MainActivity.this, "설문조", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                    break;
                case R.id.nav_result:
                    Toast.makeText(MainActivity.this, "자가진단 결과", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, ResultActivity.class));
                    break;
            }

            return false;
        });



    }

}


