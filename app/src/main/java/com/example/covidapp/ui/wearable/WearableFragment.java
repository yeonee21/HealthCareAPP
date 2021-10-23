package com.example.covidapp.ui.wearable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;

public class WearableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wearable, container, false);

        SeekBar HeartRateBar = (SeekBar) rootView.findViewById(R.id.HR_bar);
        EditText hr_condition = (EditText) rootView.findViewById(R.id.HR_condition);

        return inflater.inflate(R.layout.fragment_wearable, container, false);
    }

}