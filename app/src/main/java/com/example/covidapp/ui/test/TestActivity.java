package com.example.covidapp.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.MainActivity;
import com.example.covidapp.R;
import com.example.covidapp.ui.result.ResultActivity;
import com.example.covidapp.ui.wearable.WearableActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TestActivity extends AppCompatActivity {
    private EditText edit_temp;
    private RadioGroup rgIllness, rgRunNose, rgBreath, rgExhaust, rgCough,
            rgThroat, rgCovidexp, rgStuffNose, rgDiarrhea, rgContact;

    int StateTemp, StateIllness, StateRunNose, StateBreath, StateExhaust, StateCough,
            StateThroat, StateCovidExp, StateStuffNose, StateDiarrhea, StateContact;


    private Button save_test;
    private int Pressbtn;

    public TestActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    Toast.makeText(TestActivity.this, "코로나19 정보", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(TestActivity.this, MainActivity.class));
                    return true;
                case R.id.nav_wearable:
                    Toast.makeText(TestActivity.this, "웨어러블 데이터 확인", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(TestActivity.this, WearableActivity.class));
                    return true;
                case R.id.nav_test:
                    Toast.makeText(TestActivity.this, "설문조", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.nav_result:
                    Toast.makeText(TestActivity.this, "자가진단 결과", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(TestActivity.this, ResultActivity.class));
                    return true;
            }

            return false;
        });


    }
}