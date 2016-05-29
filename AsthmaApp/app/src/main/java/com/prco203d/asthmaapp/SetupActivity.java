package com.prco203d.asthmaapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SetupActivity extends AppCompatActivity {

//    private EditText editTextName = null;
//    private Spinner spinnerAge = null;
//    private Spinner  spinnerGender = null;
    private TextView description = null;

    // Fillable TextViews
    private TextView nameDisplay = null;
    private TextView dobDisplay = null;
    private TextView genderDisplay = null;
    private TextView heightDisplay = null;

    Date DoB = null;

    // Nav
    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

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

        //editTextName = (EditText) findViewById(R.id.editTextName);
//        spinnerAge  = (Spinner) findViewById(R.id.spinnerAge);
//        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        description = (TextView) findViewById(R.id.textView);

        nameDisplay = (TextView) findViewById(R.id.textViewNameFilled);
        dobDisplay = (TextView) findViewById(R.id.textViewAgeFilled);
        genderDisplay = (TextView) findViewById(R.id.textViewGenderFilled);
        heightDisplay = (TextView) findViewById(R.id.textViewHeightFilled);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup
        if(isSetup()){
            // Brief info
            description.setText(getResources().getString(R.string.setup1_desc));

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);

            // Populate fields with user's info
            String name = sharedPrefs.getString("Name", "User");
            //editTextName.setText(name);

            int spinnerAgeSavedInt = sharedPrefs.getInt("AgeInt", 0);
//            spinnerAge.setSelection(spinnerAgeSavedInt);

            int spinnerGenderSavedInt = sharedPrefs.getInt("GenderInt", 0);
//            spinnerGender.setSelection(spinnerGenderSavedInt);
        }
        // First time setup
        else{
            // Welcome info
            description.setText(getResources().getString(R.string.setup1_desc_welcome));

            // Show next button
            buttonUpdate.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);

            // Display disclaimer popup
            AlertDialog alertDialog = new AlertDialog.Builder(SetupActivity.this).create();
            alertDialog.setTitle("Welcome to AsthmaAid");
            alertDialog.setMessage(getResources().getString(R.string.setup_disclaimer));

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // quit app here?
                            dialog.dismiss();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

            alertDialog.show();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Date DoB = new Date(year, month, day);

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

        saveData();

        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    // Go to previous page
    public void previousPage(View view){
        // No previous to go to!
    }

    public void submitData(View view) {

        saveData();

        // This is the same as "back" so should break the back loop situation
        finish();
    }

    public void showNameDialog(View view) {
        // Display name popup
        final EditText nameEdit = new EditText(SetupActivity.this);
        nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);

        final AlertDialog alertDialog = new AlertDialog.Builder(SetupActivity.this).create();
        alertDialog.setTitle("Enter your name");
        alertDialog.setView(nameEdit);

        nameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Button okButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    okButton.performClick();
                }
                return false;
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String editTextValue = nameEdit.getText().toString();
                        nameDisplay.setText(editTextValue);

                        dialog.dismiss();
                    }
                });

        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialog.show();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showGenderDialog(View view) {

    }

    public void showHeightDialog(View view) {

    }

    public void saveData(){

        // Write the data to a pref file
        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Validate and save name
        String userName;
        if(nameDisplay.getText().toString() != null && nameDisplay.getText().toString() != "") {
            userName = nameDisplay.getText().toString();
        } else{
            userName = "User";
        }
        editor.putString("Name", userName);

        // Validate and save age

        // Validate and save gender

        // Validate and save height

        // Calculate PF estimates
        int age = 12;               // Some
        int heightInCM = 142;       // temp
        float heightInMeters = heightInCM / 100f;       // temp
        Boolean isMale = false;      // values

        int estimatedPFBest;
        int estimatedPFWarning;
        int estimatedPFCritical;

        if(age >= 16){              // sources just say child and not an actual age?
            // Use adult formula
            if (isMale){
                // Use male formula
                estimatedPFBest = Math.round( ( ( (heightInMeters * 5.48f) + 1.58f) - (age * 0.041f) ) * 60f);

            } else{
                // Use female formula
                estimatedPFBest = Math.round((((heightInMeters * 3.72f) + 2.24f) - (age * 0.03f)) * 60f);
            }
        }
        else{
            // Use child formula
            estimatedPFBest = ((heightInCM - 100) * 5) + 100;
        }

        estimatedPFWarning = Math.round(estimatedPFBest * 0.75f);
        estimatedPFCritical = Math.round(estimatedPFBest * 0.5f);

        editor.putInt("EstimatedPF_Best", estimatedPFBest);
        editor.putInt("EstimatedPF_Warning", estimatedPFWarning);
        editor.putInt("EstimatedPF_Critical", estimatedPFCritical);

        Log.d("PF Debug", "" + estimatedPFBest);
        Log.d("PF Debug", "" + estimatedPFWarning);
        Log.d("PF Debug", "" + estimatedPFCritical);

        // Do the spinners
//        String genderString = "";
//        int spinnerPosGender = spinnerGender.getSelectedItemPosition();
//        editor.putInt("GenderInt", spinnerPosGender);
//        switch (spinnerPosGender)
//        {
//            case spinnerPosMALE:
//                genderString += ", Male";
//                break;
//
//            case spinnerPosFEMALE:
//                genderString += ", Female";
//                break;
//
//            case spinnerPosNOT_SAY:
//                genderString += "";
//                break;
//        }
//
//        editor.putString("Gender", genderString) ;
//
//        String ageString = "";
//        int spinnerPosAge = spinnerAge.getSelectedItemPosition();
//        editor.putInt("AgeInt", spinnerPosAge);
//        switch (spinnerPosAge)
//        {
//            case spinnerPos0_10:
//                ageString += "0-10";
//                break;
//
//            case spinnerPos11_20:
//                ageString += "11-20";
//                break;
//
//            case spinnerPos21_30:
//                ageString += "21-30";
//                break;
//
//            case spinnerPos31_40:
//                ageString += "31-40";
//                break;
//
//            case spinnerPos41_50:
//                ageString += "41-50";
//                break;
//
//            case spinnerPos51_60:
//                ageString += "51-60";
//                break;
//
//            case spinnerPos61_70:
//                ageString += "61-70";
//                break;
//
//            case spinnerPos70plus:
//                ageString += "70+";
//                break;
//        }
//        editor.putString("Age", ageString) ;

        editor.apply();

    }

}
