package com.example.covidapp.ui.wearable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.MainActivity;
import com.example.covidapp.R;
import com.example.covidapp.ml.HRSpO2model;
import com.example.covidapp.ui.result.ResultActivity;
import com.example.covidapp.ui.test.TestActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class WearableActivity extends AppCompatActivity {
    int hr_value;
    int spo2_value;
    int hr_score;
    int spo2_score;
    int wearableScore;

    EditText hr_edit;
    EditText spo2_edit;

    TextView hr_condition;
    TextView spo2_condition;

    Button wearable_btn;

    SharedPreferences pref;

    float inputArray[][][];

    protected Interpreter interpreter;
    private File tfliteModel;

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
                hr_value = Integer.parseInt(hr_edit.getText().toString().trim());
                spo2_value = Integer.parseInt(spo2_edit.getText().toString().trim());
                setCondition();
                wearableScore();
                wearableScore = hr_score+spo2_score;
                saveState();

                println("HeartRate: ", String.valueOf(hr_value));
                println("SpO2: ", String.valueOf(spo2_value));
                println("Wearable Score: ", String.valueOf(wearableScore));

                Toast.makeText(getApplicationContext(),"저장되었습니다.", Toast.LENGTH_LONG).show();

            }

        });

        //loadModule();
        WearableArray();
        //makePrediction();

        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(WearableActivity.this, MainActivity.class));
                    return true;
                case R.id.nav_wearable:
                    return true;
                case R.id.nav_test:
                    startActivity(new Intent(WearableActivity.this, TestActivity.class));
                    return true;
                case R.id.nav_result:
                    startActivity(new Intent(WearableActivity.this, ResultActivity.class));
                    return true;
            }

            return false;
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
        } else if (spo2_value >=75 && spo2_value <90) {
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



    public void println(String key,String value) {
        Log.d(key,value);
    }

    protected void restoreState() {
        pref = getSharedPreferences("Data", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("HeartRate") && (pref.contains("SpO2")))) {
            String heartrate = String.valueOf(pref.getInt("HeartRate", 0));
            String spo2 = String.valueOf(pref.getInt("SpO2",0));
            hr_edit.setText(heartrate);
            spo2_edit.setText(spo2);
        }
    }

    protected void saveState() {
        pref = getSharedPreferences("Data", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HeartRate", hr_value);
        editor.putInt("SpO2", spo2_value);
        editor.putInt("Wearable", wearableScore);
        editor.apply();
    }

    private void loadModule() {
        try {
            interpreter = new Interpreter(loadModelFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        interpreter = new Interpreter(tfliteModel);

    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor=this.getAssets().openFd("HRSpO2model.tflite");
        FileInputStream inputStream=new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel=inputStream.getChannel();
        long startOffset=fileDescriptor.getStartOffset();
        long declareLength=fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startOffset,declareLength);
    }

    private void WearableArray(){
        inputArray = new float[1][120][2];
        Scanner scanner = null;
        String InputLine = "";


        int Rowc = 0;

        try {
            InputStreamReader sc  = new InputStreamReader(getAssets().open("wearable_data.txt"));
            scanner = new Scanner(sc);
            while (scanner.hasNextLine()){
                InputLine = scanner.nextLine();
                String[] InArray = InputLine.split(",");

                for (int x = 0; x< InArray.length; x++){
                    inputArray[0][Rowc][x] = Float.parseFloat(InArray[x]);
                }
                Rowc++;
            }
        } catch (IOException e){
            Log.wtf("WearableActivity", "Error reading data file", e);
        }

    }



    void makePrediction() {
        // 1 input(s): [  1 120   2] <class 'numpy.float32'>
        // 1 output(s): [1 1 2] <class 'numpy.float32'>
        try {
            HRSpO2model model = HRSpO2model.newInstance(WearableActivity.this);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(120 * 2 * 4).order(ByteOrder.nativeOrder());
            for (int y = 0; y < 2; y++){
                for (int x = 0; x <120; x++){
                    byteBuffer.putFloat(inputArray[0][x][y]);
                }

            }

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 120, 2}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            //HRSpO2model.Outputs outputs = model.process(inputFeature0);
            //TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            TensorBuffer outputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 1, 2}, DataType.FLOAT32);
            interpreter.run(inputFeature0.getBuffer(),outputFeature0.getBuffer());
            float outputData[] = outputFeature0.getFloatArray();
            Log.e("WearableActivity", "prediction: " + outputData);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            Log.d("WearableActivity", "Prediction failed ",e);
        }

    }





}
