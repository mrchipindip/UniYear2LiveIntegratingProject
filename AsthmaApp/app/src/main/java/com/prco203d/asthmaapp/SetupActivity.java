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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SetupActivity extends AppCompatActivity {

    private EditText editTextName = null;
    private Spinner spinnerAge = null;
    private Spinner  spinnerGender = null;
    //private EditText  editPeakFlow   = null;
    private TextView description = null;

    private final int   spinnerPosMALE    = 0;
    private final int   spinnerPosFEMALE  = 1;
    private final int   spinnerPosNOT_SAY = 2;

    private final int   spinnerPos0_10   = 0;
    private final int   spinnerPos11_20  = 1;
    private final int   spinnerPos21_30 = 2;
    private final int   spinnerPos31_40 = 3;
    private final int   spinnerPos41_50 = 4;
    private final int   spinnerPos51_60 = 5;
    private final int   spinnerPos61_70 = 6;
    private final int   spinnerPos70plus = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerAge  = (Spinner) findViewById(R.id.spinnerAge);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        description = (TextView) findViewById(R.id.textView);
        //editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        if((sharedPrefs.getBoolean("isSetup", false) == false)){
            description.setText(getResources().getString(R.string.setup1_desc_welcome));
        }
        else{
            description.setText(getResources().getString(R.string.setup1_desc));
        }

        String name = sharedPrefs.getString("Name", "User");
        editTextName.setText(name);

        //int peak = (sharedPrefs.getInt("Peak", 0));
        //editPeakFlow.setText("" + peak);

        int spinnerAgeSavedInt = sharedPrefs.getInt("AgeInt", 0);
        spinnerAge.setSelection(spinnerAgeSavedInt);

        int spinnerGenderSavedInt = sharedPrefs.getInt("GenderInt", 0);
        spinnerGender.setSelection(spinnerGenderSavedInt);
    }

    // Overriding the back key, for first-time setup
    @Override
    public void onBackPressed() {

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // if the app isn't set up, exit it
        if((sharedPrefs.getBoolean("isSetup", false) == false)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        // otherwise just go back like normal
        else{
            super.onBackPressed();
        }
    }

    public void submitData(View view) {

        // write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Do the textView ones
        String userName;
        if(editTextName.getText().toString() != null && editTextName.getText().toString() != "") {
            userName = editTextName.getText().toString();
        } else{
            userName = "User";
        }
        editor.putString("Name", userName);

        //int peak;
        //if(editPeakFlow.getText().toString() != null && editPeakFlow.getText().toString() != ""){
        //    peak = Integer.parseInt(editPeakFlow.getText().toString());
        //}
        //else {
        //    peak = 0;
        //}
        //editor.putInt("Peak", peak);

        // Do the spinners
        String genderString = "";
        int spinnerPosGender = spinnerGender.getSelectedItemPosition();
        editor.putInt("GenderInt", spinnerPosGender);
        switch (spinnerPosGender)
        {
            case spinnerPosMALE:
                genderString += ", Male";
                break;

            case spinnerPosFEMALE:
                genderString += ", Female";
                break;

            case spinnerPosNOT_SAY:
                genderString += "";
                break;
        }

        editor.putString("Gender", genderString) ;

        String ageString = "";
        int spinnerPosAge = spinnerAge.getSelectedItemPosition();
        editor.putInt("AgeInt", spinnerPosAge);
        switch (spinnerPosAge)
        {
            case spinnerPos0_10:
                ageString += "0-10";
                break;

            case spinnerPos11_20:
                ageString += "11-20";
                break;

            case spinnerPos21_30:
                ageString += "21-30";
                break;

            case spinnerPos31_40:
                ageString += "31-40";
                break;

            case spinnerPos41_50:
                ageString += "41-50";
                break;

            case spinnerPos51_60:
                ageString += "51-60";
                break;

            case spinnerPos61_70:
                ageString += "61-70";
                break;

            case spinnerPos70plus:
                ageString += "70+";
                break;
        }
        editor.putString("Age", ageString) ;

        // Set that the app is now setup for use
        editor.putBoolean("isSetup", true);

        editor.apply();

        // This is the same as "back" so should break the back loop situation
        finish();
    }

}
