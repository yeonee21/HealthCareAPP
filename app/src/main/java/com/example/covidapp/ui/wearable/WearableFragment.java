package com.example.covidapp.ui.wearable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covidapp.R;

public class WearableFragment extends Fragment {

    private WearableViewModel wearableViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wearableViewModel =
                new ViewModelProvider(this).get(WearableViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wearable, container, false);
        final TextView textView = root.findViewById(R.id.wearable);
        wearableViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}