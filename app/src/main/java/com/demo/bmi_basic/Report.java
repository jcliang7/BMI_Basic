package com.demo.bmi_basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {
    TextView result;
    TextView fieldsuggest;
    Button button_back;
    private static final String TAG = "Bmi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Report-onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        findViews();

        double BMI = calcBMI();
        showResult(BMI);
        setListeners();

    }

    private double calcBMI() {
        Log.d(TAG, "Reoprt-calcBMI");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double height = Double.parseDouble(bundle.getString("KEY_HEIGHT") )/ 100;
        double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT") + "");
        double BMI = weight / (height * height);
        return BMI;
    }

    private void showResult(double BMI) {
        Log.d(TAG, "Report-showResult");
        DecimalFormat nf = new DecimalFormat("0.00");

        result.setText("Your BMI si " + nf.format(BMI));


        if (BMI > 25) {
            showNotification(BMI);
            fieldsuggest.setText(R.string.advice_heavy);
        } else if (BMI < 16) {
            fieldsuggest.setText(R.string.advice_light);
        } else {
            fieldsuggest.setText(R.string.advice_average);
        }
    }

    private void findViews() {
        Log.d(TAG, "Roport-findViews()");
        result = findViewById(R.id.result);
        fieldsuggest = findViewById(R.id.suggest);
        button_back = (Button)findViewById(R.id.report_back);
    }

    private void setListeners(){
        Log.d(TAG, "Report-setListeners()");
        button_back.setOnClickListener(backMain);
    }
    private Button.OnClickListener backMain = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Report-OnClickListener backMain-OnClicd");
            Report.this.finish();
        }


    };

    protected void showNotification (double BMI){
        NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        String CHANNEL_ID = "my_channel_01";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "my_channel", importance);
            mChannel.setDescription("This is my channel");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            barManager.createNotificationChannel(mChannel);
        }



        Notification barMsg = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("您的BMI值過高")
                .setContentText("通知監督人")
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        barManager.notify(0, barMsg);
    }

}