package com.example.covidapp.ui.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private RadioGroup rgIllness, rgRunNose, rgBreath, rgExhaust, rgCough,
            rgThroat, rgCovidexp, rgStuffNose, rgDiarrhea, rgContact;

    int StateIllness, StateRunNose, StateBreath, StateExhaust, StateCough,
            StateThroat, StateCovidExp, StateStuffNose, StateDiarrhea, StateContact = 100;
    float StateTemp_value, Temp_score, Illness_score, RunNose_score, Breath_score, Exhaust_score,
            Cough_score, Throat_score, CovidExp_score, StuffNose_score, Diarrhea_score, Contact_score;
    int Pressbtn, test_score;

    Button save_test;
    EditText StateTemp;
    SharedPreferences pref;

    public TestActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        StateTemp = findViewById(R.id.user_temp);

        rgIllness = findViewById(R.id.rg_Illness);
        rgIllness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.illness_O) {
                    StateIllness = 1;
                } else if (checkedId == R.id.illness_X) {
                    StateIllness = 0;
                }
            }
        });

        rgRunNose = findViewById(R.id.rg_RunnyNose);
        rgRunNose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.runnynose_O) {
                    StateRunNose = 1;
                } else if (checkedId == R.id.runnynose_X) {
                    StateRunNose = 0;
                }
            }
        });

        rgBreath = findViewById(R.id.rg_ShortBreath);
        rgBreath.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.shortbreath_O) {
                    StateBreath = 1;
                } else if (checkedId == R.id.shortbreath_X) {
                    StateBreath = 0;
                }
            }
        });

        rgExhaust = findViewById(R.id.rg_Exhaust);
        rgExhaust.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.exhaust_O) {
                    StateExhaust = 1;
                } else if (checkedId == R.id.exhaust_X) {
                    StateExhaust = 0;
                }
            }
        });

        rgCough = findViewById(R.id.rg_Cough);
        rgCough.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cough_O) {
                    StateCough = 1;
                } else if (checkedId == R.id.cough_X) {
                    StateCough = 0;
                }
            }
        });

        rgThroat = findViewById(R.id.rg_Throat);
        rgThroat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.throat_O) {
                    StateThroat = 1;
                } else if (checkedId == R.id.throat_X) {
                    StateThroat = 0;
                }
            }
        });

        rgCovidexp = findViewById(R.id.rg_CovidExp);
        rgCovidexp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.covidexp_O) {
                    StateCovidExp = 1;
                } else if (checkedId == R.id.covidexp_X) {
                    StateCovidExp = 0;
                }
            }
        });

        rgStuffNose = findViewById(R.id.rg_StuffyNose);
        rgStuffNose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.stuffynose_O) {
                    StateStuffNose = 1;
                } else if (checkedId == R.id.stuffynose_X) {
                    StateStuffNose = 0;
                }
            }
        });

        rgDiarrhea = findViewById(R.id.rg_Diarrhea);
        rgDiarrhea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.diarrhea_O) {
                    StateDiarrhea = 1;
                } else if (checkedId == R.id.diarrhea_X) {
                    StateDiarrhea = 0;
                }
            }
        });

        rgContact = findViewById(R.id.rg_Contact);
        rgContact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.contact_O) {
                    StateContact = 1;
                } else if (checkedId == R.id.contact_X) {
                    StateContact = 0;
                }
            }
        });



        save_test = findViewById(R.id.test_button);
        save_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StateTemp_value = Integer.parseInt(StateTemp.getText().toString().trim());

                Pressbtn = StateIllness + StateRunNose + StateBreath +
                        StateExhaust + StateCough + StateThroat + StateCovidExp + StateStuffNose + StateDiarrhea + StateContact;

                if ( Pressbtn >= 100 ){
                    Toast.makeText(getApplicationContext(),"옵션을 선택하세요!",Toast.LENGTH_LONG).show();
                } else {

////    Important feature of Logistic Regression Algorithm
////                          feature  coefficient(LR)
////            2           runnyNose         0.098425
////            7         Sore-Throat         0.064010
////            5           Tiredness         0.054597
////            12         Contact_No         0.002457
////            6           Dry-Cough        -0.000260
////            9        Experiencing        -0.001461
////            0            fever(C)        -0.004716
////            10           Diarrhea        -0.007512
////            8    Nasal-Congestion        -0.032832
////            11  Contact_Dont-Know        -0.046871
////            3          diffBreath        -0.071799
////            13        Contact_Yes        -0.077118
////            1            bodyPain        -0.092795
////            4       infectionProb        -0.101053
////     extract from python

                    Illness_score = StateIllness * 10;
                    Temp_score = (float) (StateTemp_value * 0.004716);
                    RunNose_score = (float) (StateRunNose * 0.098425);
                    Breath_score = (float) (StateBreath * 0.071799);
                    Exhaust_score = (float) (StateExhaust * 0.054597);
                    Cough_score = (float) (StateCough * 0.000260);
                    Throat_score = (float) (StateThroat * 0.064010);
                    CovidExp_score = (float) (StateCovidExp * 0.001461);
                    StuffNose_score = (float) (StateStuffNose * 0.032832);
                    Diarrhea_score = (float) (StateDiarrhea * 0.007512);
                    Contact_score = (float) (StateContact * 0.077118);

                    test_score = (int) (Illness_score + (Temp_score + RunNose_score + Breath_score+ Exhaust_score + Cough_score + Throat_score +
                                                CovidExp_score + StuffNose_score + Diarrhea_score + Contact_score) * 70);

                    saveState();

                    println("StateTemp: ", String.valueOf(StateTemp_value));
                    println("StateIllness: ", String.valueOf(StateIllness));
                    println("StateRunNose: ", String.valueOf(StateRunNose));
                    println("StateBreath: ", String.valueOf(StateBreath));
                    println("StateExhaust: ", String.valueOf(StateExhaust));
                    println("StateCough: ", String.valueOf(StateCough));
                    println("StateThroat: ", String.valueOf(StateThroat));
                    println("StateCovidExp: ", String.valueOf(StateCovidExp));
                    println("StateStuffNose: ", String.valueOf(StateStuffNose));
                    println("StateDiarrhea: ", String.valueOf(StateDiarrhea));
                    println("StateContact: ", String.valueOf(StateContact));
                    println("Survey Score: ", String.valueOf(test_score));

                    Toast.makeText(getApplicationContext(),"저장되었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });


        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(TestActivity.this, MainActivity.class));
                    return true;
                case R.id.nav_wearable:
                    startActivity(new Intent(TestActivity.this, WearableActivity.class));
                    return true;
                case R.id.nav_test:
                    return true;
                case R.id.nav_result:
                    startActivity(new Intent(TestActivity.this, ResultActivity.class));
                    return true;
            }

            return false;
        });
    }

    public void println(String key,String value) {
        Log.d(key,value);
    }

    protected void saveState() {
        pref = getSharedPreferences("Data", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putFloat("StateTemp", StateTemp_value);
        editor.putInt("StateIllness", StateIllness);
        editor.putInt("StateRunNose", StateRunNose);
        editor.putInt("StateBreath", StateBreath);
        editor.putInt("StateExhaust", StateExhaust);
        editor.putInt("StateCough", StateCough);
        editor.putInt("StateThroat", StateThroat);
        editor.putInt("StateCovidExp", StateCovidExp);
        editor.putInt("StateStuffNose", StateStuffNose);
        editor.putInt("StateDiarrhea", StateDiarrhea);
        editor.putInt("StateContact", StateContact);
        editor.putInt("Survey", test_score);

        editor.apply();
    }
}