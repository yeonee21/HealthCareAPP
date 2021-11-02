package com.example.covidapp.ui.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.covidapp.MainActivity;
import com.example.covidapp.R;
import com.example.covidapp.TestActivity;
import com.example.covidapp.WearableActivity;

public class ResultActivity extends AppCompatActivity {
    LinearLayout result1;
    ConstraintLayout result2;

    int wearableScore, surveyScore;
    int finalScore;

    Button result_btn;
    TextView tv_finalScore;
    TextView tv_finalCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result1 = findViewById(R.id.resultlayout1);
        result2 = findViewById(R.id.resultlayout2);
        result1.setVisibility(View.VISIBLE);
        result2.setVisibility(View.INVISIBLE);

        Intent intentW = getIntent();
        wearableScore = intentW.getIntExtra("Wearable", 0);
        surveyScore = intentW.getIntExtra("Survey", 0);
        finalScore = wearableScore+surveyScore;

        tv_finalScore = findViewById(R.id.finalScore);
        tv_finalCondition = findViewById(R.id.finalCondition);

        tv_finalScore.setText(String.valueOf(finalScore));
        setFinalCondition();


        result_btn = findViewById(R.id.result_btn);
        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result1.setVisibility(View.INVISIBLE);
                result2.setVisibility(View.VISIBLE);
            }
        });

    }
    public void setFinalCondition(){
        if (finalScore <= 40) {
            tv_finalCondition.setText("안전한 상태");
            tv_finalCondition.setTextColor(Color.parseColor("#4EB3CF"));
        } else if (finalScore>40 && finalScore<=60 ) {
            tv_finalCondition.setText("주의");
            tv_finalCondition.setTextColor(Color.parseColor("#FFE800"));
        } else if (finalScore>60 && finalScore<=80 ) {
            tv_finalCondition.setText("위험");
            tv_finalCondition.setTextColor(Color.parseColor("#FFA701"));
        } else if (finalScore>80 && finalScore<=100 ) {
            tv_finalCondition.setText("매우 위험");
            tv_finalCondition.setTextColor(Color.parseColor("#FF1206"));
        }
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

    public void btn_result(View view)
    {
    }
}