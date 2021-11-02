package com.example.covidapp.ui.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;


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
        setContentView(R.layout.fragment_test);


}}