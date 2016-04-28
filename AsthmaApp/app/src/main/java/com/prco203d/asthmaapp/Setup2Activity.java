package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setup2Activity extends AppCompatActivity {

    private EditText editPeakFlowBest   = null;
    private EditText editPeakFlowWarning   = null;
    private EditText editPeakFlowCritical   = null;
    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);

        editPeakFlowBest = (EditText) findViewById(R.id.editTextPeak);
        editPeakFlowWarning = (EditText) findViewById(R.id.editTextWarning);
        editPeakFlowCritical = (EditText) findViewById(R.id.editTextCritical);
        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){

            // if we're editing, get the current values
            int peak = (sharedPrefs.getInt("Peak", 0));
            editPeakFlowBest.setText("" + peak);

            int warning = (sharedPrefs.getInt("Warning", 0));
            editPeakFlowWarning.setText("" + warning);

            int critical = (sharedPrefs.getInt("Critical", 0));
            editPeakFlowCritical.setText("" + critical);

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // Show next + previous buttons only
            buttonUpdate.setVisibility(View.INVISIBLE);
        }

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
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);
    }

    // Go to previous page
    public void previousPage(View view){
        finish();
    }

    public void submitData(View view) {

        saveData();

        // This is the same as "back" so should break the back loop situation
        finish();
    }

    public void saveData() {

        // write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPrefs.edit();

        int peak;
        if(editPeakFlowBest.getText().toString() != null && editPeakFlowBest.getText().toString() != ""){
            peak = Integer.parseInt(editPeakFlowBest.getText().toString());
        }
        else {
            peak = 0;
        }
        editor.putInt("Peak", peak);

        int warn;
        if(editPeakFlowWarning.getText().toString() != null && editPeakFlowWarning.getText().toString() != ""){
            warn = Integer.parseInt(editPeakFlowWarning.getText().toString());
        }
        else {
            warn = 0;
        }
        editor.putInt("Warning", warn);

        int critical;
        if(editPeakFlowCritical.getText().toString() != null && editPeakFlowCritical.getText().toString() != ""){
            critical = Integer.parseInt(editPeakFlowCritical.getText().toString());
        }
        else {
            critical = 0;
        }
        editor.putInt("Critical", critical);

        // Set that the app is now setup for use
        //editor.putBoolean("isSetup", true);

        editor.apply();

        // This is the same as "back" so should break the back loop situation
        finish();
    }
}
