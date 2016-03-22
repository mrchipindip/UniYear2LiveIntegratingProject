package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MyDataActivity extends AppCompatActivity {

    private TextView textTitle = null;
    private TextView textPeak = null;
    private TextView ageGender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textTitle = (TextView) findViewById(R.id.textViewTitle);
        textPeak = (TextView) findViewById(R.id.textViewPeak);
        ageGender = (TextView) findViewById(R.id.textViewAgeGender);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String name = sharedPrefs.getString("Name", "User");
        textTitle.setText(name + "'s Data");

        int peak = (sharedPrefs.getInt("Peak", 0));
        String peakString = "Peak Flow: " + peak;
        textPeak.setText(peakString);

        String age = sharedPrefs.getString("Age", "User Age, ");
        String gender = sharedPrefs.getString("Gender", "User Gender");
        ageGender.setText(age + gender);

    }

    public void openSetup(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
    }

    public void deleteData(View view) {

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        sharedPrefs.edit().clear().commit();

        finish();
        startActivity(getIntent());

    }


}
