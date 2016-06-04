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
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SetupActivity extends AppCompatActivity {

    private TextView description = null;

    // Fillable TextViews
    private TextView nameDisplay = null;
    private TextView dobDisplay = null;
    private TextView genderDisplay = null;
    private TextView heightDisplay = null;

    // DoB Variables
    private int day;
    private int month;
    private int year;
    private int age;

    // Sex variables
    private Boolean isMale;
    private Boolean tempBool;
    private String tempText;

    private int heightInCM;

    // Navigation
    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Top of page description
        description = (TextView) findViewById(R.id.textView);

        // The input fields
        nameDisplay = (TextView) findViewById(R.id.textViewNameFilled);
        dobDisplay = (TextView) findViewById(R.id.textViewAgeFilled);
        genderDisplay = (TextView) findViewById(R.id.textViewGenderFilled);
        heightDisplay = (TextView) findViewById(R.id.textViewHeightFilled);

        // Navigation buttons
        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing mode
        if(isSetup()){
            // Enable up button and use non-numbered activity title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup_done));

            // Brief info
            description.setText(getResources().getString(R.string.setup1_desc));

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);

            // Populate fields with user's info
            String name = sharedPrefs.getString("Name", "--");
            nameDisplay.setText(name);

            day = sharedPrefs.getInt("dobD", 0);
            month = sharedPrefs.getInt("dobM", 0);
            year = sharedPrefs.getInt("dobY", 0);


            // Make a Calendar object
            Calendar displayCalendar = Calendar.getInstance();
            displayCalendar.set(sharedPrefs.getInt("dobY", 0),sharedPrefs.getInt("dobM", 0),sharedPrefs.getInt("dobD", 0));

            Calendar nowCalendar = Calendar.getInstance();
            age = nowCalendar.get(Calendar.YEAR) - displayCalendar.get(Calendar.YEAR);

            if (nowCalendar.get(Calendar.DAY_OF_YEAR) < displayCalendar.get(Calendar.DAY_OF_YEAR)){
                age--;
            }

            // Format for display
            java.text.DateFormat formatter = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG); // one of SHORT, MEDIUM, LONG, FULL, or DEFAULT
            formatter.setTimeZone(displayCalendar.getTimeZone());
            String formattedDoB = formatter.format(displayCalendar.getTime());
            dobDisplay.setText(formattedDoB);

            String gender = sharedPrefs.getString("Sex", "--");
            Log.d("PF Debug", gender);
            if(gender.equals("Male")) {
                isMale = true;
                Log.d("PF Debug", "gender set to male in oncreate");
            }
            else {
                isMale = false;
                Log.d("PF Debug", "gender set to female in oncreate");
            }
            genderDisplay.setText(gender);

            heightInCM = sharedPrefs.getInt("HeightCM", 0);
            String height = "" + sharedPrefs.getInt("HeightCM", 0) +"cm";
            heightDisplay.setText(height);
        }

        // First time setup mode
        else{
            // Welcome info
            description.setText(getResources().getString(R.string.setup1_desc_welcome));

            // Show next button only
            buttonUpdate.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);

            // Display disclaimer popup
            AlertDialog alertDialog = new AlertDialog.Builder(SetupActivity.this).create();
            alertDialog.setTitle("Welcome to Asthma Aid");
            alertDialog.setMessage(getResources().getString(R.string.setup_disclaimer));

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // quit app here?
                            dialog.dismiss();
                            onBackPressed();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(SetupActivity.this).create();
        alertDialog.setTitle("Enter your name");

        final EditText nameEdit = new EditText(SetupActivity.this);
        nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
        nameEdit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });

//        final EditText nameEdit2 = new EditText(SetupActivity.this);
//        nameEdit.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
//        nameEdit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(5) });
//
//        LinearLayout doubleEditText = new LinearLayout(this);
//        doubleEditText.setOrientation(LinearLayout.VERTICAL);
//        doubleEditText.addView(nameEdit2);
//        doubleEditText.addView(nameEdit);

        alertDialog.setView(nameEdit);

        // This code block allows the keyboard "done" button to dismiss the dialog
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
            ((SetupActivity)getActivity()).SetDob(year, month, day);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void SetDob(int y, int m, int d){
        // Set class variables
        day = d;
        month = m;
        year = y;

        // Make a Calendar object
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.set(year,month,day);

        // Format for display
        java.text.DateFormat formatter = java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG); // one of SHORT, MEDIUM, LONG, FULL, or DEFAULT
        formatter.setTimeZone(dobCalendar.getTimeZone());
        String formattedDoB = formatter.format(dobCalendar.getTime());
        dobDisplay.setText(formattedDoB);

        // Find out age
        Calendar nowCalendar = Calendar.getInstance();

        age = nowCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        if (nowCalendar.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        //heightDisplay.setText(""+age);
    }

    public void showGenderDialog(View view) {

        // The available options
        final CharSequence[] items = {getResources().getString(R.string.setup1_female),
                                      getResources().getString(R.string.setup1_male),
                                      getResources().getString(R.string.setup1_prefer)};

        // Display sex popup
        final AlertDialog alertGenderDialog = new AlertDialog.Builder(SetupActivity.this).setSingleChoiceItems(items, -1,

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        // Choose what to do
                        switch (item) {
                            case 0:
                                tempBool = false;
                                tempText = getResources().getString(R.string.setup1_female);
                                break;
                            case 1:
                                tempBool = true;
                                tempText = getResources().getString(R.string.setup1_male);
                                break;
                            case 2:
                                tempBool = false;
                                tempText = getResources().getString(R.string.setup1_prefer);
                                break;
                        }
                    }
                }).create();
        alertGenderDialog.setTitle("Select your sex");

        alertGenderDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertGenderDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        genderDisplay.setText(tempText);
                        isMale = tempBool;

                        dialog.dismiss();
                    }
                });

        alertGenderDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertGenderDialog.show();
    }

    public void showHeightDialog(View view) {

        // Display height popup
        final AlertDialog alertHeightDialog = new AlertDialog.Builder(SetupActivity.this).create();
        alertHeightDialog.setTitle("Enter your height in cm");

        final EditText heightEdit = new EditText(SetupActivity.this);
        heightEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        heightEdit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) }); // you can't be over 1000 cm, sorry!

        alertHeightDialog.setView(heightEdit);

        // This code block allows the keyboard "done" button to dismiss the dialog
        heightEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Button okButton = alertHeightDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    okButton.performClick();
                }
                return false;
            }
        });

        alertHeightDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertHeightDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String editTextValue = heightEdit.getText().toString();
                        heightDisplay.setText(editTextValue + "cm");
                        heightInCM = Integer.parseInt(editTextValue);
                        dialog.dismiss();
                    }
                });

        alertHeightDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertHeightDialog.show();
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
        editor.putInt("dobD", day);
        editor.putInt("dobM", month);
        editor.putInt("dobY", year);

        // Save sex
        String userSex;
        if(genderDisplay.getText().toString() != null && genderDisplay.getText().toString() != "") {
            userSex = genderDisplay.getText().toString();
        } else{
            userSex = "Unknown";
        }
        editor.putString("Sex", userSex);
        Log.d("PF Debug", genderDisplay.getText().toString());
        Log.d("PF Debug", userSex);

        // Validate and save height
        editor.putInt("HeightCM", heightInCM);

        float heightInMeters = heightInCM / 100f;       // temp

        int estimatedPFBest;
        int estimatedPFWarning;
        int estimatedPFCritical;

        if(age >= 16){              // sources just say child and not an actual age?

            // Use adult formula
            if (isMale){
                // Use male formula
                Log.d("PF Debug", "Used Male");
                estimatedPFBest = Math.round( ( ( (heightInMeters * 5.48f) + 1.58f) - (age * 0.041f) ) * 60f);

            } else{
                // Use female formula
                Log.d("PF Debug", "Used Female");
                estimatedPFBest = Math.round((((heightInMeters * 3.72f) + 2.24f) - (age * 0.03f)) * 60f);
            }
        }
        else{
            // Use child formula
            Log.d("PF Debug", "Used Child");
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

        editor.apply();
    }

}
