package com.example.covidapp.ui.wearable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class WearableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wearable, container, false);

        TextView hr_condition = (TextView) rootView.findViewById(R.id.HR_condition);
        TextView spo2_condition = (TextView) rootView.findViewById(R.id.SpO2_condition);

        return inflater.inflate(R.layout.fragment_wearable, container, false);
    }

}