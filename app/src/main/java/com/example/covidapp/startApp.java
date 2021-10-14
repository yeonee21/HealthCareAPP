package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.ui.login.LoginActivity;

public class startApp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
    }

    public void start(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
