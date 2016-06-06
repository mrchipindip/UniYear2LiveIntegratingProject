package com.prco203d.asthmaapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {


    private EditText peakFlowVariableEditText = null;
    private Button submitButton = null;
    private Button EditButton = null;
    private TextView FeelingTodayTextView = null;
    public int peakFlowToday;
    private String name;
    private String savedPFValues;
    private String savedPFDates;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("PFValues", "this is a value");
        editor.putString("PFDates", "this is a date");
        editor.apply();

        Toolbar toolbar          = (Toolbar) findViewById(R.id.toolbar);
        submitButton             = (Button) findViewById(R.id.submitButton);
        peakFlowVariableEditText = (EditText) findViewById(R.id.peakFlowVariableEditText);
        EditButton               = (Button) findViewById(R.id.EditButton);
        FeelingTodayTextView = (TextView) findViewById(R.id.FeelingTodayTextView);

        submitButton.setOnClickListener(this);
        peakFlowVariableEditText.setOnKeyListener(this);
        EditButton.setOnClickListener(this);

        setSupportActionBar(toolbar);

        String name = sharedPrefs.getString("Name", "User");
        FeelingTodayTextView.setText("How are you feeling today " + name + "?");

        EditButton.setEnabled(false);

        // Check if setup screen has been encountered
        if((sharedPrefs.getBoolean("isSetup", false) == false)){
            Intent intent = new Intent(this, SetupActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void openWell(View view) {
        Intent intent = new Intent(this, WellActivity.class);
        startActivity(intent);
    }

    public void openUnwell(View view) {
        Intent intent = new Intent(this, UnwellActivity.class);
        startActivity(intent);
    }

    public void openEmergency(View view) {
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivity(intent);
    }

    public void openMyData(View view) {
        Intent intent = new Intent(this, MyDataActivity.class);
        startActivity(intent);
    }

    public void openGraphAndCalendar(View view) {
        Intent intent = new Intent(this, GraphAndCalendarActivity.class);
        startActivity(intent);
    }

    public void openMedRef(View view) {
        Intent intent = new Intent(this, MedRefActivity.class);
        startActivity(intent);
    }

    public void openAppSettings(View view) {
        Intent intent = new Intent(this, AppSettingsActivity.class);
        startActivity(intent);
    }

    public void openSymptomsList(View view) {
        Intent intent = new Intent(this, SymptomsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                addPFData();
            try {
                peakFlowToday = Integer.valueOf(peakFlowVariableEditText.getText().toString());
                SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putInt("Peak", peakFlowToday);
                editor.apply();
                submitButton.setEnabled(false);
                EditButton.setEnabled(true);
                addPFData();
                Toast submitToast = Toast.makeText(this, "Peak flow submitted: " + peakFlowToday , Toast.LENGTH_LONG);
                submitToast.setGravity(Gravity.TOP, -430, 430);
                submitToast.show();

            } catch (NumberFormatException ex) {
                //Toast.makeText(this, R.string.no_value, Toast.LENGTH_SHORT).show();
                Toast noValueToast = Toast.makeText(this, R.string.no_value, Toast.LENGTH_LONG);
                noValueToast.setGravity(Gravity.TOP, -430, 430);
                noValueToast.show();
                addPFData();
            }
                break;

            case R.id.EditButton:
                try {
                    peakFlowToday = Integer.valueOf(peakFlowVariableEditText.getText().toString());
                    Toast editToast = Toast.makeText(this, "Todays peak flow has been changed to: " + peakFlowToday, Toast.LENGTH_LONG);
                    editToast.setGravity(Gravity.TOP, -430, 430);
                    editToast.show();
                } catch (NumberFormatException ex) {
                    Toast noValueToast = Toast.makeText(this, R.string.no_value, Toast.LENGTH_LONG);
                    noValueToast.setGravity(Gravity.TOP, -430, 430);
                    noValueToast.show();
                }
                break;
        }
    }

    public Integer GetTodaysPF()
    {
        return peakFlowToday;
    }
    void addPFData()
    {
//        //peakFlowToday
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("PFValues", "this is a value");
        editor.putString("PFDates", "this is a date");
        editor.apply();
//
//        //check if the prefs are present already
//        if (sharedPrefs.contains("PFValues") && sharedPrefs.contains("PFDates"))
//        {
//
//
//            SharedPreferences.Editor editor = sharedPrefs.edit();
//            //if they are then append the new information to the current pref
//            savedPFValues = sharedPrefs.getString("PFValues", "");
//            savedPFDates = sharedPrefs.getString("PFDates", "");
//
//
//
//            savedPFValues = new StringBuilder(savedPFValues).append("," + Integer.toString(peakFlowToday)).toString();
//            editor.putString("PFValues", savedPFValues);
//            savedPFDates = new StringBuilder(savedPFDates).append("," + Long.toString(new java.util.Date().getTime())).toString();
//            editor.putString("PFDates", savedPFDates);
//
//            editor.apply();
//        }
//        else
//        {
//            System.out.println("...................else clause initialted....................");
//            //if not create the strings and commit them to the pref
//            savedPFValues = Integer.toString(peakFlowToday);
//
//            SharedPreferences.Editor editor = sharedPrefs.edit();
//
//            editor.putString("PFValues", savedPFValues);
//            System.out.println(savedPFValues);
//
//            savedPFDates = Long.toString(new java.util.Date().getTime());
//            editor.putString("PFDates", savedPFDates);
//            System.out.println(savedPFDates);
//            editor.apply();
//
//            //cheekyTestMethod();
//
//        }
    }

    void cheekyTestMethod()
    {
        savedPFValues = new StringBuilder(savedPFValues).append("," + Integer.toString(400)).toString();
        savedPFDates = new StringBuilder(savedPFDates).append("," + Long.toString(new java.util.Date().getTime())).toString();

        savedPFValues = new StringBuilder(savedPFValues).append("," + Integer.toString(450)).toString();
        savedPFDates = new StringBuilder(savedPFDates).append("," + Long.toString(new java.util.Date().getTime())).toString();

        savedPFValues = new StringBuilder(savedPFValues).append("," + Integer.toString(500)).toString();
        savedPFDates = new StringBuilder(savedPFDates).append("," + Long.toString(new java.util.Date().getTime())).toString();

        savedPFValues = new StringBuilder(savedPFValues).append("," + Integer.toString(550)).toString();
        savedPFDates = new StringBuilder(savedPFDates).append("," + Long.toString(new java.util.Date().getTime())).toString();

        editor.putString("PFDates", savedPFDates);
        editor.putString("PFValues", savedPFValues);
        editor.commit();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }


}

