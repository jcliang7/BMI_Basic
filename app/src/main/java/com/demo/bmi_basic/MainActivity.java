package com.demo.bmi_basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText field_height;
    EditText field_weight;
    private static final String TAG = "Bmi";
    public static final String PREF = "BMI_PREF";
    public static final String PREF_HEIGHT = "BMI_HEIGHT";
//    public static final String PREF_WEIGHT = "BMI_WEIGHT";

    private void restorePrefs(){
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        String pref_height = settings.getString(PREF_HEIGHT, "");
//        String pref_height = settings.getString(PREF_WEIGHT, "");
        if (!"".equals(pref_height))
        {
            field_height.setText(pref_height);
            field_weight.requestFocus();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        settings.edit().putString(PREF_HEIGHT, field_height.getText().toString()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        findViews();
        restorePrefs();
        setListeners();

    }

    private void findViews() {
        button = findViewById(R.id.submit);
        field_height = (EditText) findViewById(R.id.height);
        field_weight = (EditText) findViewById(R.id.weight);
        Log.d(TAG, "find Views()");
    }

    private void setListeners() {
        Log.d(TAG, "setListeners()");
        button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "OnClickListener - onClick");
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Report.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT", field_height.getText().toString());
            bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
//            try{
//                double BMI = calcBMI();
//                showResult(BMI);
//                openOptionsDialog();
//            } catch(NumberFormatException e){
//                e.printStackTrace();
//                Toast.makeText(MainActivity.this, R.string.input_error, Toast.LENGTH_SHORT).show();
//            }


        }
    };



    private void openOptionsDialog(){
        Log.d(TAG, "openOptionsDialog()");
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_btn_confirm, null)
                .setNeutralButton(R.string.homepage_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Uri uri = Uri.parse("http://tw.yahoo.com");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton(R.string.dialog_btn_cancel, null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        menu.add(0, 10, 0, "關於")
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(0, 20, 0, "結束")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected()");
        switch(item.getItemId()){
            case 10:
                openOptionsDialog();
                break;
            case 20:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
