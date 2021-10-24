package com.example.covidapp.ui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covidapp.R;


public class TestFragment extends Fragment {
    private SharedViewModel model;
    private EditText edit_temp;
    private RadioGroup rgIllness, rgRunNose, rgBreath, rgExhaust, rgCough,
            rgThroat, rgCovidexp, rgStuffNose, rgDiarrhea, rgContact;

    int StateIllness, StateRunNose, StateBreath, StateExhaust, StateCough,
        StateThroat, StateCovidExp, StateStuffNose, StateDiarrhea, StateContact;

    private Button save_test;

    public TestFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);
        edit_temp = rootView.findViewById(R.id.user_temp);
        rgIllness = rootView.findViewById(R.id.rg_Illness);
        rgIllness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.illness_O) {
                    StateIllness = 1;
                } else if (checkedId == R.id.illness_X) {
                    StateIllness = 0;
                } else {
                    StateIllness = 100;
                }
            }
        });

        rgRunNose = rootView.findViewById(R.id.rg_RunnyNose);
        rgRunNose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.runnynose_O) {
                    StateRunNose = 1;
                } else if (checkedId == R.id.runnynose_X) {
                    StateRunNose = 0;
                } else {
                    StateRunNose = 100;
                }
            }
        });

        rgBreath = rootView.findViewById(R.id.rg_ShortBreath);
        rgBreath.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.shortbreath_O) {
                    StateBreath = 1;
                } else if (checkedId == R.id.shortbreath_X) {
                    StateBreath = 0;
                } else {
                    StateBreath = 100;
                }
            }
        });

        rgExhaust = rootView.findViewById(R.id.rg_Exhaust);
        rgExhaust.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.exhaust_O) {
                    StateExhaust = 1;
                } else if (checkedId == R.id.exhaust_X) {
                    StateExhaust = 0;
                } else {
                    StateExhaust = 100;
                }
            }
        });

        rgCough = rootView.findViewById(R.id.rg_Cough);
        rgCough.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cough_O) {
                    StateCough = 1;
                } else if (checkedId == R.id.cough_X) {
                    StateCough = 0;
                } else {
                    StateCough = 100;
                }
            }
        });

        rgThroat = rootView.findViewById(R.id.rg_Throat);
        rgThroat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.throat_O) {
                    StateThroat = 1;
                } else if (checkedId == R.id.throat_X) {
                    StateThroat = 0;
                } else {
                    StateThroat = 100;
                }
            }
        });

        rgCovidexp = rootView.findViewById(R.id.rg_CovidExp);
        rgCovidexp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.covidexp_O) {
                    StateCovidExp = 1;
                } else if (checkedId == R.id.covidexp_X) {
                    StateCovidExp = 0;
                } else {
                    StateCovidExp = 100;
                }
            }
        });

        rgStuffNose = rootView.findViewById(R.id.rg_StuffyNose);
        rgStuffNose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.stuffynose_O) {
                    StateStuffNose = 1;
                } else if (checkedId == R.id.stuffynose_X) {
                    StateStuffNose = 0;
                } else {
                    StateStuffNose = 100;
                }
            }
        });

        rgDiarrhea = rootView.findViewById(R.id.rg_Diarrhea);
        rgDiarrhea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.diarrhea_O) {
                    StateDiarrhea = 1;
                } else if (checkedId == R.id.diarrhea_X) {
                    StateDiarrhea = 0;
                } else {
                    StateDiarrhea = 100;
                }
            }
        });

        rgContact = rootView.findViewById(R.id.rg_Contact);
        rgContact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.contact_O) {
                    StateContact = 1;
                } else if (checkedId == R.id.contact_X) {
                    StateContact = 0;
                } else {
                    StateContact= 100;
                }
            }
        });

        save_test = rootView.findViewById(R.id.test_button);


        return rootView;

    }

    public void onViewCreated(ViewGroup rootView, Bundle savedInstanceState){
        super.onViewCreated(rootView, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        save_test.setOnClickListener(item ->{
            Item illnessItem = new Item();
            model.select(illnessItem);
        });

        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            edit_temp.setText(item.getTemp());

        });
    };
}