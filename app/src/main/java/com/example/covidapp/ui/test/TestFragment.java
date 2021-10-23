package com.example.covidapp.ui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class TestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);
        RadioGroup rgTemp;
        RadioGroup rgIllness;
        RadioGroup rgRunNose;
        RadioGroup rgBreath;
        RadioGroup rgExhaust;
        RadioGroup rgCough;
        RadioGroup rgThroat;
        RadioGroup rgCovidexp;
        RadioGroup rgStuffNose;
        RadioGroup rgDiarrhea;
        RadioGroup rgContact;



        return inflater.inflate(R.layout.fragment_test, container, false);



    }
}