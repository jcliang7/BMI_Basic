package com.demo.bmi_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {
    private static final String TAG = "Bmi";
    TextView result;
    TextView fieldsuggest;
    Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        findViews();
        double BMI = calcBMI();
        showResult(BMI);

    }

    private double calcBMI() {
        Log.d(TAG, "calcBMI");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double height = Double.parseDouble(bundle.getString("KEY_HEIGHT")) / 100;
        double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
        double BMI = weight / (height * height);
        return BMI;
    }

    private void showResult(double BMI) {
        Log.d(TAG, "showReslut()");
        DecimalFormat nf = new DecimalFormat("0.00");

        result.setText("Your BMI si " + nf.format(BMI));


        if (BMI > 25) {
            fieldsuggest.setText(R.string.advice_heavy);
        } else if (BMI < 16) {
            fieldsuggest.setText(R.string.advice_light);
        } else {
            fieldsuggest.setText(R.string.advice_average);
        }
    }

    private void findViews() {
        Log.d(TAG, "findView()");
        result = findViewById(R.id.result);
        fieldsuggest = findViewById(R.id.suggest);
        button_back = findViewById(R.id.report_back);
    }
}