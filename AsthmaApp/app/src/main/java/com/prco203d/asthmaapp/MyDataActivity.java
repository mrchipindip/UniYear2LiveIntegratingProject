package com.prco203d.asthmaapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MyDataActivity extends AppCompatActivity {

    private TextView textName = null;
    private TextView textAge = null;
    private TextView textGender = null;
    private TextView textHeight = null;

    private TextView textPeak = null;
    private TextView textWarning = null;
    private TextView textCritical = null;

    private TextView textGPName = null;
    private TextView textGPNumber = null;
    private TextView textOoHName = null;
    private TextView textPeakNumber = null;

    private TextView textPreventerName = null;
    private TextView textRelieverName = null;

    int peak = 0;
    String age = "";
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //textTitle = (TextView) findViewById(R.id.textViewTitle);
        //textPeak = (TextView) findViewById(R.id.textViewPBPeakFlow);
        //ageGender = (TextView) findViewById(R.id.textViewName);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        //String name = sharedPrefs.getString("Name", "User");
        //textTitle.setText(name + "'s Data");

        //peak = (sharedPrefs.getInt("Peak", 0));
        //String peakString = "Peak Flow: " + peak;
        //textPeak.setText(peakString);

        //age = sharedPrefs.getString("Age", "User Age, ");
        //gender = sharedPrefs.getString("Gender", "User Gender");
        //ageGender.setText(age + gender);

    }


    @Override
    protected void onStart() {
        super.onStart();
        syncDisplayValues();
    }

    public void syncDisplayValues(){

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        //String name = sharedPrefs.getString("Name", "User");
        //textTitle.setText(name + "'s Data");

        //peak = (sharedPrefs.getInt("Peak", 0));
        //String peakString = "Peak Flow: " + peak;
        //textPeak.setText(peakString);

        //age = sharedPrefs.getString("Age", "User Age, ");
        //gender = sharedPrefs.getString("Gender", "User Gender");
        //ageGender.setText(age + gender);

    }

    public void openSetup(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
    }

    public void openSetup2(View view) {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);
    }

    public void openSetup3(View view) {
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);
    }

    public void openSetup4(View view) {
        Intent intent = new Intent(this, Setup4Activity.class);
        startActivity(intent);
    }

    public void openSetup5(View view) {
        Intent intent = new Intent(this, Setup5Activity.class);
        startActivity(intent);
    }

    public void openSetup6(View view) {
        Intent intent = new Intent(this, Setup6Activity.class);
        startActivity(intent);
    }

    public void deleteData(View view) {

        AlertDialog alertDialog = new AlertDialog.Builder(MyDataActivity.this).create();
        alertDialog.setTitle("Hard Reset Alert");
        alertDialog.setMessage("Are you sure you want to reset your data?");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // Delete everything
                        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        sharedPrefs.edit().clear().commit();
//                        finish();
//                        startActivity(getIntent());

                        Intent intent = new Intent(MyDataActivity.this, SetupActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

        alertDialog.show();
        
    }
    
}
