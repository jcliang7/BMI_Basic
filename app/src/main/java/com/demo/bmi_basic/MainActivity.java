package com.demo.bmi_basic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText fieldheight;
    EditText fieldweight;
    TextView result;
    TextView fieldsuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();

    }

    private void findViews() {
        button = findViewById(R.id.submit);
        fieldheight = (EditText) findViewById(R.id.height);
        fieldweight = (EditText) findViewById(R.id.weight);
        result = findViewById(R.id.result);
        fieldsuggest = findViewById(R.id.suggest);
    }

    private void setListeners() {
        button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double BMI = calcBMI();
            showResult(BMI);
            openOptionsDialog();
        }
    };

    private double calcBMI() {
        double height = Double.parseDouble(fieldheight.getText() + "") / 100;
        double weight = Double.parseDouble(fieldweight.getText() + "");
        double BMI = weight / (height * height);
        return BMI;
    }

    private void showResult(double BMI) {
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

    private void openOptionsDialog(){
        Toast.makeText(MainActivity.this, "這是一個 Tost", Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, "這是另一個 Tost", Toast.LENGTH_SHORT).show();
    }



}
