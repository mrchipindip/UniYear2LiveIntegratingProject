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

public class Setup3Activity extends AppCompatActivity {


    //private EditText editPeakFlow   = null;
    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    private EditText editDrName;
    private EditText editDrNo;
    private EditText editOutOfHoursName;
    private EditText editOutOfHoursNo;

    private String drName;
    private String drNo; // James Bond lol
    private String outOfHoursName;
    private String outOfHoursNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Change to surgery details
        //editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        editDrName = (EditText) findViewById(R.id.editTextGPName);
        editDrNo = (EditText) findViewById(R.id.editTextGPPhone);
        editOutOfHoursName = (EditText) findViewById(R.id.editTextOoHName);
        editOutOfHoursNo = (EditText) findViewById(R.id.editTextOoHNumber);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){
            // Enable up button and use non-numbered activity title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup3_done));

            editDrName.setText(sharedPrefs.getString("DrName",""));
            editDrNo.setText(sharedPrefs.getString("DrNo",""));
            editOutOfHoursName.setText(sharedPrefs.getString("OutOfHoursName",""));
            editOutOfHoursNo.setText(sharedPrefs.getString("OutOfHoursNo",""));

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
        saveData();

        Intent intent = new Intent(this, Setup4Activity.class);
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

        // Validate and save Dr name
        String tempDrName;
        if(editDrName.getText().toString() != null && editDrName.getText().toString() != "") {
            tempDrName = editDrName.getText().toString();
        } else{
            tempDrName = "";
        }
        editor.putString("DrName", tempDrName);

        // Validate and save Dr no
        String tempDrNo;
        if(editDrNo.getText().toString() != null && editDrNo.getText().toString() != "") {
            tempDrNo = editDrNo.getText().toString();
        } else{
            tempDrNo = "";
        }
        editor.putString("DrNo", tempDrNo);


        // Validate and save OoH name
        String tempOName;
        if(editOutOfHoursName.getText().toString() != null && editOutOfHoursName.getText().toString() != "") {
            tempOName = editOutOfHoursName.getText().toString();
        } else{
            tempOName = "";
        }
        editor.putString("OutOfHoursName", tempOName);

        // Validate and save OoH no
        String tempONo;
        if(editOutOfHoursNo.getText().toString() != null && editOutOfHoursNo.getText().toString() != "") {
            tempONo = editOutOfHoursNo.getText().toString();
        } else{
            tempONo = "";
        }
        editor.putString("OutOfHoursNo", tempONo);


        editor.apply();
    }

}
