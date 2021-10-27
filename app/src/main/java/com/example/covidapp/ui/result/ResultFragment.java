package com.example.covidapp.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class ResultFragment extends Fragment {
    Button score_btn;
    LinearLayout result1;
    ConstraintLayout result2;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);
        result1 = rootView.findViewById(R.id.resultlayout1);
        result2 = rootView.findViewById(R.id.resultlayout2);
        result1.setVisibility(View.VISIBLE);
        result2.setVisibility(View.INVISIBLE);
        score_btn = (Button) rootView.findViewById(R.id.my_result);

        score_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result1.setVisibility(View.INVISIBLE);
                result2.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }






}