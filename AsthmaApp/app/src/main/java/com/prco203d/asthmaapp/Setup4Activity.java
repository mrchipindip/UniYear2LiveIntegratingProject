package com.prco203d.asthmaapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Setup4Activity extends AppCompatActivity {

    //private EditText editPeakFlow   = null;
    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    private Spinner spinnerP = null;
    private Spinner spinnerR = null;

    private EditText editPAM   = null;
    private EditText editPPM   = null;
    private EditText editR   = null;

    private TextView rescueYesNo;
    private TextView rescueDose;
    private TextView rescueDays;

    private Boolean rescueBool;
    private Integer tempRescueDose;
    private Integer tempRescueDays;

    private final int   spinnerPreventerNone = 0;
    private final int   spinnerPreventerBeclomethasone = 1;
    private final int   spinnerPreventerClenilModulate = 2;
    private final int   spinnerPreventerQvar = 3;
    private final int   spinnerPreventerFluticasone = 4;
    private final int   spinnerPreventerSymbicort = 5;
    private final int   spinnerPreventerSeretide = 6;
    private final int   spinnerPreventerFostair = 7;
    private final int   spinnerPreventerFlutiform = 8;

    private final int   spinnerRelieverNone = 0;
    private final int   spinnerRelieverSalbutamolV = 1;
    private final int   spinnerRelieverSalbutamolG = 2;
    private final int   spinnerRelieverTurbutalineB = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // change to inhalers
        // editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        spinnerP = (Spinner) findViewById(R.id.spinnerPreventers);
        // change!
        spinnerR = (Spinner) findViewById(R.id.spinnerAge);

        editPAM = (EditText) findViewById(R.id.editTextPAM);
        editPPM = (EditText) findViewById(R.id.editTextPPM);
        editR = (EditText) findViewById(R.id.editTextR);

        rescueYesNo = (TextView) findViewById(R.id.textViewRescueYesNo);
        rescueDose = (TextView) findViewById(R.id.textViewTabletNumber);
        rescueDays = (TextView) findViewById(R.id.textViewTabletDays);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){
            // Enable up button and use non-numbered activity title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup4_done));

            int pam = (sharedPrefs.getInt("PreventerAM", 0));
            int ppm = (sharedPrefs.getInt("PreventerPM", 0));
            int r = (sharedPrefs.getInt("RelieverWell", 0));

            editPAM.setText("" + pam);
            editPPM.setText("" + ppm);
            editR.setText("" + r);

            if(sharedPrefs.getBoolean("RescuePack", false)){
                int rescueDoseNum = (sharedPrefs.getInt("PrednisoloneMg", 0));
                int rescueDaysNum = (sharedPrefs.getInt("PrednisoloneDays", 0));

                rescueYesNo.setText("I have a prednisolone rescue pack.");
                rescueDose.setText("I should take " + rescueDoseNum + " x 5mg tablets when my symptoms are worse.");
                rescueDays.setText("I should continue taking this dose every morning for " + rescueDaysNum + " days");
            }
            else{
                rescueYesNo.setText("No rescue pack.");
                rescueDose.setText("-");
                rescueDays.setText("-");
            }

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // Show next + previous buttons only
            buttonUpdate.setVisibility(View.INVISIBLE);
        }

        //int peak = (sharedPrefs.getInt("Peak", 0));
        //editPeakFlow.setText("" + peak);

        // ADD THIS BACK IN LATER
        int spinnerPSavedInt = sharedPrefs.getInt("PreventerInt", 0);
        spinnerP.setSelection(spinnerPSavedInt);

        int spinnerRSavedInt = sharedPrefs.getInt("RelieverInt", 0);
        spinnerR.setSelection(spinnerRSavedInt);

    }

    // Overriding the back key, for first-time setup
//    @Override
//    public void onBackPressed() {
//
//        // if the app is set up already, go back like normal
////        if(isSetup()){
////            super.onBackPressed();
////        }
////        // otherwise exit
////        else{
////            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            startActivity(intent);
////        }
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

        Intent intent = new Intent(this, Setup5Activity.class);
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


    public void showRescueDialog(View view){

        // Display rescue popup
        final AlertDialog alertDialog = new AlertDialog.Builder(Setup4Activity.this).create();
        alertDialog.setTitle("Do you have a rescue pack?");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        rescueBool = false;
                        rescueYesNo.setText("No rescue pack.");
                        rescueDose.setText("-");
                        rescueDays.setText("-");
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();

                        // Display second rescue popup
                        final AlertDialog alertDialog2 = new AlertDialog.Builder(Setup4Activity.this).create();
                        alertDialog2.setTitle("Rescue Pack Details");

                        final TextView rescueDesc = new TextView(Setup4Activity.this);
                        rescueDesc.setText("  Number of 5mg prednisolone tablets to take\n  when feeling worse:");

                        final EditText editTabletNumber = new EditText(Setup4Activity.this);
                        editTabletNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editTabletNumber.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });

                        final TextView rescueDesc2 = new TextView(Setup4Activity.this);
                        rescueDesc2.setText("  Number of days to continue taking this dose\n  for every morning:");

                        final EditText editTabletDays = new EditText(Setup4Activity.this);
                        editTabletDays.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editTabletDays.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });

                        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
                        rescueLayout.setOrientation(LinearLayout.VERTICAL);
                        rescueLayout.addView(rescueDesc);
                        rescueLayout.addView(editTabletNumber);
                        rescueLayout.addView(rescueDesc2);
                        rescueLayout.addView(editTabletDays);

                        alertDialog2.setView(rescueLayout);

                        alertDialog2.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog2.setButton(AlertDialog.BUTTON_POSITIVE, "Confirm",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        rescueBool = true;

                                        rescueYesNo.setText("I have a prednisolone rescue pack.");

                                        try {
                                            if (editTabletNumber.getText().toString() != null && editTabletNumber.getText().toString() != "") {
                                                tempRescueDose = Integer.parseInt(editTabletNumber.getText().toString());
                                                rescueDose.setText("I should take " + tempRescueDose + " x5mg tablets when symptoms are worse.");
                                            } else {
                                            }
                                        }catch(NumberFormatException ex){

                                        }

                                        try {
                                            if (editTabletDays.getText().toString() != null && editTabletDays.getText().toString() != "") {
                                                tempRescueDays = Integer.parseInt(editTabletDays.getText().toString());
                                                rescueDays.setText("I should continue taking this dose every morning for " + tempRescueDays + " days");
                                            } else {
                                            }
                                        }catch(NumberFormatException ex){

                                        }

                                        dialog.dismiss();
                                    }
                                });

                        alertDialog2.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                        alertDialog2.show();


                    }
                });

        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialog.show();


    }


    public void saveData() {

        // write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Save something
        // Do the spinners
        String pString = "";
        int spinnerPosP = spinnerP.getSelectedItemPosition();
        editor.putInt("PreventerInt", spinnerPosP);
        switch (spinnerPosP)
        {
            case spinnerPreventerNone:
                pString += "None";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerBeclomethasone:
                pString += "Beclomethasone";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerClenilModulate:
                pString += "Clenil Modulate";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerQvar:
                pString += "Qvar";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerFluticasone:
                pString += "Fluticasone (Flixotide)";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerSymbicort:
                pString += "Symbicort";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerSeretide:
                pString += "Seretide";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerFostair:
                pString += "Fostair";
                editor.putString("PreventerName", pString);
                break;

            case spinnerPreventerFlutiform:
                pString += "Flutiform";
                editor.putString("PreventerName", pString);
                break;
        }

        String rString = "";
        int spinnerPosR = spinnerR.getSelectedItemPosition();
        editor.putInt("RelieverInt", spinnerPosR);
        switch (spinnerPosR)
        {
            case spinnerRelieverNone:
                rString += "None";
                editor.putString("RelieverName", rString);
                break;

            case spinnerRelieverSalbutamolV:
                rString += "Salbutamol (Ventolin)";
                editor.putString("RelieverName", rString);
                break;

            case spinnerRelieverSalbutamolG:
                rString += "Salbutamol (Generic)";
                editor.putString("RelieverName", rString);
                break;

            case spinnerRelieverTurbutalineB:
                rString += "Turbutaline (Bricanyl)";
                editor.putString("RelieverName", rString);
                break;
        }

        // AM Puffs
        int amPuffs;
        try {
            if (editPAM.getText().toString() != null && editPAM.getText().toString() != "") {
                amPuffs = Integer.parseInt(editPAM.getText().toString());
            } else {
                amPuffs = 0;
            }
            editor.putInt("PreventerAM", amPuffs);
        }catch(NumberFormatException ex){

        }

        // PM Puffs
        int pmPuffs;
        try {
            if (editPPM.getText().toString() != null && editPPM.getText().toString() != "") {
                pmPuffs = Integer.parseInt(editPPM.getText().toString());
            } else {
                pmPuffs = 0;
            }
            editor.putInt("PreventerPM", pmPuffs);
        }catch(NumberFormatException ex){

        }

        // Rel Puffs
        int relPuffs;
        try {
            if (editR.getText().toString() != null && editR.getText().toString() != "") {
                relPuffs = Integer.parseInt(editR.getText().toString());
            } else {
                relPuffs = 0;
            }
            editor.putInt("RelieverWell", relPuffs);
        }catch(NumberFormatException ex){

        }

        // if it's been defined, set it
        if(rescueBool != null){
            editor.putBoolean("RescuePack", rescueBool);
        }

        try {
            if (tempRescueDose != null) {
                editor.putInt("PrednisoloneMg", tempRescueDose);
            }
        }catch(Exception ex){

        }

        try {
            if (tempRescueDays != null) {
                editor.putInt("PrednisoloneDays", tempRescueDays);
            }
        }catch(Exception ex){

        }


        // Everything to save is:
//        sharedPrefs.getInt("PreventerAM", 0);
//        sharedPrefs.getInt("PreventerPM", 0);
//        sharedPrefs.getInt("RelieverWell", 0);
//        sharedPrefs.getInt("RelieverWarningLevel", 0);
//        sharedPrefs.getInt("PreventerWarningPuffs", 0);
//        sharedPrefs.getInt("PreventerWarningDoses", 0);
//        sharedPrefs.getInt("RelieverWarningMaxPuffsPer4Hours", 0);
//        sharedPrefs.getString("RelieverName", "Not Given");
//        sharedPrefs.getString("PreventerName", "Not Given");
//        sharedPrefs.getInt("PrednisoloneMg", 0);
//        sharedPrefs.getInt("PrednisoloneDays", 0);

        editor.apply();

    }

}
