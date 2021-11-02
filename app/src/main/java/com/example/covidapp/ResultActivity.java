package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class ResultActivity extends AppCompatActivity {
    Button score_btn;
    LinearLayout result1;
    ConstraintLayout result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result1 = findViewById(R.id.resultlayout1);
        result2 = findViewById(R.id.resultlayout2);
        result1.setVisibility(View.VISIBLE);
        result2.setVisibility(View.INVISIBLE);
        score_btn = (Button) findViewById(R.id.my_result);

        score_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result1.setVisibility(View.INVISIBLE);
                result2.setVisibility(View.VISIBLE);
            }
        });
    }

    public void btn_timeline(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btn_wearable(View view){
        startActivity(new Intent(this, WearableActivity.class));
    }

    public void btn_test(View view){
        startActivity(new Intent(this, TestActivity.class));
    }

    public void btn_result(View view){

    }
}