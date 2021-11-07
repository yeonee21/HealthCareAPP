package com.example.covidapp.ui.wearable;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.nnapi.NnApiDelegate;
import org.tensorflow.lite.support.common.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.Scanner;


public class LSTM extends AppCompatActivity {
    protected Interpreter interpreter;
    private MappedByteBuffer tfliteModel;
    protected Boolean useNnpai = false;
    protected int numThreads = 1;
    private NnApiDelegate nnApiDelegate = null;
    private final Interpreter.Options tfliteOptions = new Interpreter.Options();

    float[][][] inputData;
    float[][][] outputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadModule();
        WearableArray();
        makePrediction();
    }


    private void loadModule() {
        if(useNnpai) {
            nnApiDelegate = new NnApiDelegate();
            tfliteOptions.addDelegate(nnApiDelegate);
        }

        tfliteOptions.setNumThreads(numThreads);

        try {
            tfliteModel = FileUtil.loadMappedFile(LSTM.this, "app/src/main/ml/HRSpO2model.tflite");
        } catch (IOException e) {
            e.printStackTrace();
        }

        interpreter = new Interpreter(tfliteModel, tfliteOptions);

        }

        private void WearableArray(){
            inputData = new float[1][120][2];
            Scanner scanner = null;
            String InputLine = "";

            int Rowc = 0;

            try {
                scanner = new Scanner(new BufferedReader(new FileReader("app/src/main/res/raw/wearable_data.txt")));
                while (scanner.hasNextLine()){
                    InputLine = scanner.nextLine();
                    String[] InArray = InputLine.split(",");

                    for (int x = 0; x< InArray.length; x++){
                        inputData[0][Rowc][x] = Float.parseFloat(InArray[x]);
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

            //ByteBuffer input = ByteBuffer.allocateDirect(120 * 2 * 4).order(ByteOrder.nativeOrder());
            outputData = new float[1][1][2];

            interpreter.run(inputData,outputData);
            Log.e("outputs", "prediction: " + outputData[0][0][0]);



    }



}