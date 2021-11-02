package com.example.covidapp.ui.wearable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.ui.result.ResultActivity;

public class WearableActivity extends AppCompatActivity {
    int hr_value;
    int spo2_value;
    int hr_score;
    int spo2_score;

    EditText hr_edit;
    EditText spo2_edit;

    TextView hr_condition;
    TextView spo2_condition;

    Button wearable_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wearable);

        hr_edit = findViewById(R.id.HR_value);
        spo2_edit = findViewById(R.id.spo2_value);

        hr_condition = findViewById(R.id.HR_condition);
        spo2_condition = findViewById(R.id.SpO2_condition);

        wearable_btn = findViewById(R.id.wearable_save);
        wearable_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentW = new Intent(WearableActivity.this, ResultActivity.class);
                hr_value = Integer.parseInt(hr_edit.getText().toString().trim());
                spo2_value = Integer.parseInt(spo2_edit.getText().toString().trim());
                setCondition();
                wearableScore();

                int wearableScore = hr_score+spo2_score;

                intentW.putExtra("Wearable", wearableScore);

                startActivity(intentW);

            }

        });


    }

    public void wearableScore() {
        if (hr_value>=50 && hr_value < 75) {
            hr_score = 0;
        } else if (hr_value <50) {
            hr_score = 2;
        }else if (hr_value >=75 && hr_value <85) {
            hr_score = 5;
        } else if (hr_value >= 85 && hr_value <100) {
            hr_score = 7;
        } else if (hr_value >= 100 ) {
            hr_score = 10;
        }

        if (spo2_value>=95) {
            spo2_score = 0;
        } else if (spo2_value >=90 && spo2_value <95) {
            spo2_score = 5;
        } else if (spo2_value >=75 && spo2_value <89) {
            spo2_score = 7;
        }else if (spo2_value<75) {
            spo2_score = 10;
        }
    }

    public void setCondition() {
        if (hr_value>=50 && hr_value < 75) {
            hr_condition.setText("심박수 정상");
            hr_condition.setTextColor(Color.parseColor("#4EB3CF"));
        } else if (hr_value >=75) {
            hr_condition.setText("심박수 높음");
            hr_condition.setTextColor(Color.parseColor("#FF1206"));
        } else if (hr_value <50) {
            hr_condition.setText("심박수 낮음");
            hr_condition.setTextColor(Color.parseColor("#FF1206"));
        }

        if (spo2_value>=80) {
            spo2_condition.setText("혈중 산소포화도 정상");
            spo2_condition.setTextColor(Color.parseColor("#4EB3CF"));
        } else if (spo2_value >=40 && spo2_value <80) {
            spo2_condition.setText("혈중 산소포화도 낮음");
            spo2_condition.setTextColor(Color.parseColor("#FF1206"));
        } else if (spo2_value<40) {
            spo2_condition.setText("혈중 산소포화도 매우 낮음");
            spo2_condition.setTextColor(Color.parseColor("#FF1206"));
        }

    }


}
