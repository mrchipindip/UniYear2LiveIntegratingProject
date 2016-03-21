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

public class SetupActivity extends AppCompatActivity {

    private EditText editTextName = null;
    private Spinner  spinnerGender = null;
    private Spinner spinnerAge = null;
    private EditText  editPeakFlow   = null;

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
        editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

    }

    public void submitData(View view) {

        // write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Do the textView ones

        String userName = editTextName.getText().toString();
        editor.putString("Name", userName) ;

        int peak = Integer.parseInt(editPeakFlow.getText().toString());
        editor.putInt("Peak", peak);

        // Do the spinners

        String genderString = "";
        int spinnerPosGender = spinnerGender.getSelectedItemPosition();
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
        int spinnerPosAge = spinnerGender.getSelectedItemPosition();
        switch (spinnerPosAge)
        {
            case spinnerPos0_10:
                genderString += "0-10";
                break;

            case spinnerPos11_20:
                genderString += "11-20";
                break;

            case spinnerPos21_30:
                genderString += "21-30";
                break;

            case spinnerPos31_40:
                genderString += "31-40";
                break;

            case spinnerPos41_50:
                genderString += "41-50";
                break;

            case spinnerPos51_60:
                genderString += "51-60";
                break;

            case spinnerPos61_70:
                genderString += "61-70";
                break;

            case spinnerPos70plus:
                genderString += "70+";
                break;
        }

        editor.putString("Age", ageString) ;

        editor.apply();

        // Move user to a different page
        Intent intent = new Intent(this, MyDataActivity.class);
        startActivity(intent);
    }

}
