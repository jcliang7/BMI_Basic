package com.demo.bmi_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double BMI = calcBMI();
//            showResult(BMI);
            TextView result = findViewById(R.id.result);
            result.setText(BMI+"");
        }
    };

    double calcBMI(){
        EditText fieldheight = (EditText) findViewById(R.id.height);
        EditText fieldweight = (EditText) findViewById(R.id.weight);
        double height = Double.parseDouble(fieldheight.getText()+"")/100;
        double weight = Double.parseDouble(fieldweight.getText()+"");
        double BMI = weight / (height * height);
        return BMI;
    }
}
