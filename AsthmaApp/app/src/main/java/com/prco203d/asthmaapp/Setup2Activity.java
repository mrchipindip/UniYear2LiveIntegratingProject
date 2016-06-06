package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setup2Activity extends AppCompatActivity {

    private EditText editPeakFlowBest   = null;
    private EditText editPeakFlowWarning   = null;
    private EditText editPeakFlowCritical   = null;

    private TextView textViewEPF;
    private TextView textViewEWPF;
    private TextView textViewECPF;

    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        editPeakFlowBest = (EditText) findViewById(R.id.editTextPeak);
        editPeakFlowWarning = (EditText) findViewById(R.id.editTextWarning);
        editPeakFlowCritical = (EditText) findViewById(R.id.editTextCritical);

        textViewEPF = (TextView) findViewById(R.id.textViewEstimatedPF);
        textViewEWPF = (TextView) findViewById(R.id.textViewEstimatedWPF);
        textViewECPF = (TextView) findViewById(R.id.textViewEstimatedCPF);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        // Editing setup mode
        if(isSetup()){
            // Enable up button and use non-numbered activity title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup2_done));

            // As we're editing, get the current values
            int peak = (sharedPrefs.getInt("Peak", 0));
            int warning = (sharedPrefs.getInt("Warning", 0));
            int critical = (sharedPrefs.getInt("Critical", 0));

            // If the value is zero it has come from the sharedPref defaults, and not been entered
            // We can then try to locate an estimate, if enough info was entered in Setup1
            Boolean peakCalcPossible = (sharedPrefs.getBoolean("peakCalcPossible", false));

            // PB
            if (peak != 0) {
                editPeakFlowBest.setText("" + peak);
                textViewEPF.setText("");
            }
            else {
                editPeakFlowBest.setText("");
                if (sharedPrefs.getInt("EstimatedPF_Best", 0) > 0){
                    textViewEPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Best", 0));
                }
            }

            // Warning
            if (warning != 0) {
                editPeakFlowWarning.setText("" + warning);
                textViewEWPF.setText("");
            }
            else {
                editPeakFlowWarning.setText("");
                if (sharedPrefs.getInt("EstimatedPF_Warning", 0) > 0){
                    textViewEWPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Warning", 0));
                }
            }

            // Critical
            if (critical != 0) {
                editPeakFlowCritical.setText("" + critical);
                textViewECPF.setText("");
            }
            else {
                editPeakFlowCritical.setText("");
                if (sharedPrefs.getInt("EstimatedPF_Critical", 0) > 0){
                    textViewECPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Critical", 0));
                }
            }



            // it doesn't help to put a zeroes there though
            // Also check if we can display estimated values

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // When not setup, you'll always want these coming from setup1
            if (sharedPrefs.getInt("EstimatedPF_Best", 0) > 0){
                textViewEPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Best", 0));
            }
            if (sharedPrefs.getInt("EstimatedPF_Warning", 0) > 0){
                textViewEWPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Warning", 0));
            }
            if (sharedPrefs.getInt("EstimatedPF_Critical", 0) > 0){
                textViewECPF.setText("estimated: " + sharedPrefs.getInt("EstimatedPF_Critical", 0));
            }

            // Show next + previous buttons only
            buttonUpdate.setVisibility(View.INVISIBLE);
        }

    }

    // Overriding the back key, for first-time setup
//    @Override
//    public void onBackPressed() {
//
//        // if the app is set up already, go back like normal
//        if(isSetup()){
//            super.onBackPressed();
//        }
//        // otherwise exit
//        else{
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//    }

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

        // Save the changes
        saveData();

        // Move to next screen
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
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

        // Peak Flow isn't here any more...
//        int peak;
//        try {
//            if(editPeakFlow.getText().toString() != null && editPeakFlow.getText().toString() != ""){
//                peak = Integer.parseInt(editPeakFlow.getText().toString());
//            }
//            else {
//                peak = 0;
//            }
//
//        editor.putInt("Peak", peak);
//        }catch (NumberFormatException ex){
//            Toast.makeText(this, R.string.no_value, Toast.LENGTH_SHORT).show();
//        }


        int peak;

        try {
            if (editPeakFlowBest.getText().toString() != null && editPeakFlowBest.getText().toString() != "") {
                peak = Integer.parseInt(editPeakFlowBest.getText().toString());
                editor.putInt("Peak", peak);
            } else {
                peak = 0;
            }
        }catch (NumberFormatException ex){

        }


        int warn;
        try {
            if (editPeakFlowWarning.getText().toString() != null && editPeakFlowWarning.getText().toString() != "") {
                warn = Integer.parseInt(editPeakFlowWarning.getText().toString());
                editor.putInt("Warning", warn);
            } else {
                warn = 0;
            }
        }catch(NumberFormatException ex){

        }


        int critical;
        try {
            if (editPeakFlowCritical.getText().toString() != null && editPeakFlowCritical.getText().toString() != "") {
                critical = Integer.parseInt(editPeakFlowCritical.getText().toString());
            } else {
                critical = 0;
            }
            editor.putInt("Critical", critical);
        }catch(NumberFormatException ex){

        }

        editor.apply();

        // This is the same as "back" so should break the back loop situation
        //finish();
    }
}
