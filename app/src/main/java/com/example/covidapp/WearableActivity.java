package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class WearableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearable);

        TextView hr_condition = (TextView) findViewById(R.id.HR_condition);
        TextView spo2_condition = (TextView) findViewById(R.id.SpO2_condition);

    }

    public void btn_timeline(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btn_wearable(View view){

    }

    public void btn_test(View view){
        startActivity(new Intent(this, TestActivity.class));
    }

    public void btn_result(View view)
    {
        startActivity(new Intent(this, ResultActivity.class));
    }

}