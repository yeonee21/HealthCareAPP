package com.example.covidapp.ui.result;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.covidapp.MainActivity;
import com.example.covidapp.R;
import com.example.covidapp.ui.test.TestActivity;
import com.example.covidapp.ui.wearable.WearableActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultActivity extends AppCompatActivity {
    LinearLayout result1;
    ConstraintLayout result2;

    int wearableScore, surveyScore;
    int finalScore;

    Button result_btn;
    TextView tv_finalScore;
    TextView tv_finalCondition;

    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result1 = findViewById(R.id.resultlayout1);
        result2 = findViewById(R.id.resultlayout2);
        result1.setVisibility(View.VISIBLE);
        result2.setVisibility(View.INVISIBLE);


        result_btn = findViewById(R.id.result_btn);
        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences("Data", Activity.MODE_PRIVATE);
                wearableScore = pref.getInt("Wearable", 0);
                surveyScore = pref.getInt("Survey",0);
                finalScore = wearableScore+surveyScore;

                tv_finalScore = findViewById(R.id.finalScore);
                tv_finalCondition = findViewById(R.id.finalCondition);

                tv_finalScore.setText(String.valueOf(finalScore));
                setFinalCondition();

                saveState();

                result1.setVisibility(View.INVISIBLE);
                result2.setVisibility(View.VISIBLE);
                println("Wearable Score ", String.valueOf(wearableScore));
                println("Survey Score ", String.valueOf(surveyScore));
                println("Final Score ",String.valueOf(finalScore));
            }
        });


        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(ResultActivity.this, MainActivity.class));
                    return true;
                case R.id.nav_wearable:
                    startActivity(new Intent(ResultActivity.this, WearableActivity.class));
                    return true;
                case R.id.nav_test:
                    startActivity(new Intent(ResultActivity.this, TestActivity.class));
                    return true;
                case R.id.nav_result:
                    return true;
            }
            return false;

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

    public void println(String key,String value) {
        Log.d(key,value);
    }

    protected void saveState() {
        pref = getSharedPreferences("Data", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("Final", finalScore);
        editor.apply();
    }

}