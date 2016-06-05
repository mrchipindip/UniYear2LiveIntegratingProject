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
import android.widget.Button;
import android.widget.EditText;

public class Setup6Activity extends AppCompatActivity {

    private EditText noteText = null;


    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Change to custom triggers
        //editPeakFlow = (EditText) findViewById(R.id.editTextPeak);
        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        noteText = (EditText) findViewById(R.id.editTextNote);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup6_done));

            String notes = (sharedPrefs.getString("Notes", ""));

            noteText.setText("" + notes);

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // Show previous button and finish
            buttonUpdate.setVisibility(View.INVISIBLE);
        }

        //int peak = (sharedPrefs.getInt("Peak", 0));
        //editPeakFlow.setText("" + peak);

    }

    // Overriding the back key, for first-time setup
    @Override
    public void onBackPressed() {

        // if the app is set up already, go back like normal
        if(isSetup()){
            super.onBackPressed();
        }
        // otherwise exit
        else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    // Function checks if the app is setup of not, by whether the final submit button has been pressed
    public Boolean isSetup(){

        Boolean result = false;
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        if((sharedPrefs.getBoolean("isSetup", false) == true)){
            result = true;
        }

        return result;
    }

    // Go to next page
    public void nextPage(View view){
        saveData();

        // Go to the main screen. Need to clear the back stack so users can't reverse into setup
        Intent intent = new Intent(this, NavDrawerActivity.class);
        startActivity(intent);
    }

    // Go to previous page
    public void previousPage(View view){
        finish();
    }

    public void submitData(View view) {

        saveData();

        // Go to the main screen. Need to clear the back stack so users can't reverse into setup
        finish();
    }

    public void saveData() {

        // write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPrefs.edit();

        if (noteText.getText().toString() != null && noteText.getText().toString() != "" && !noteText.getText().toString().isEmpty()) {
                editor.putString("Notes", noteText.getText().toString());
        }

        // Set that the app is now setup for use
        editor.putBoolean("isSetup", true);

        // Save something

        editor.apply();

    }





}
