package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setup5Activity extends AppCompatActivity {

    //private EditText editPeakFlow   = null;
    private Button buttonAnimal;
    private Button buttonDust;
    private Button buttonEmotion;
    private Button buttonExercise;
    private Button buttonFood;
    private Button buttonPollen;
    private Button buttonSmoke;
    private Button buttonStress;
    private Button buttonOdour;
    private Button buttonVirus;
    private Button buttonWeather;


    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Change to triggers
        //editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

        buttonAnimal = (Button)findViewById(R.id.buttonAnimal);
        buttonDust = (Button)findViewById(R.id.buttonDust);
        buttonEmotion = (Button)findViewById(R.id.buttonEmotion);
        buttonExercise = (Button)findViewById(R.id.buttonExercise);
        buttonFood = (Button)findViewById(R.id.buttonFoods);
        buttonPollen = (Button)findViewById(R.id.buttonPollen);
        buttonSmoke = (Button)findViewById(R.id.buttonSmoke);
        buttonStress = (Button)findViewById(R.id.buttonStress);
        buttonOdour = (Button)findViewById(R.id.buttonOdours);
        buttonVirus = (Button)findViewById(R.id.buttonVirus);
        buttonWeather = (Button)findViewById(R.id.buttonWeather);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // Show next + previous buttons only
            buttonUpdate.setVisibility(View.INVISIBLE);
        }
        if(sharedPrefs.getBoolean("TriggerAnimal", false)){
            buttonDust.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerDust", false)){
            buttonDust.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerEmotion", false)){
            buttonEmotion.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerExercise", false)){
            buttonExercise.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerFood", false)){
            buttonFood.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerPollen", false)){
            buttonPollen.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerSmoke", false)){
            buttonSmoke.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerStress", false)){
            buttonStress.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerOdour", false)){
            buttonOdour.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerVirus", false)){
            buttonVirus.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        if(sharedPrefs.getBoolean("TriggerWeather", false)){
            buttonWeather.setBackgroundColor(Color.parseColor("#8BC34A"));
        }

        //int peak = (sharedPrefs.getInt("Peak", 0));
        //editPeakFlow.setText("" + peak);
    }

    public void buttonToggle(View view) {

        switch (view.getId()) {
            case R.id.buttonAnimal:
                doStuff("Animal", buttonAnimal);
                break;
            case R.id.buttonDust:
                doStuff("Dust", buttonDust);
                break;
            case R.id.buttonEmotion:
                doStuff("Emotion", buttonEmotion);
                break;
            case R.id.buttonExercise:
                doStuff("Exercise", buttonExercise);
                break;
            case R.id.buttonFoods:
                doStuff("Food", buttonFood);
                break;
            case R.id.buttonPollen:
                doStuff("Pollen", buttonPollen);
                break;
            case R.id.buttonSmoke:
                doStuff("Smoke", buttonSmoke);
                break;
            case R.id.buttonStress:
                doStuff("Stress", buttonStress);
                break;
            case R.id.buttonOdours:
                doStuff("Odour", buttonOdour);
                break;
            case R.id.buttonVirus:
                doStuff("Virus", buttonVirus);
                break;
            case R.id.buttonWeather:
                doStuff("Weather", buttonWeather);
                break;
        }
    }

    public void doStuff(String inputName,  Button inputButton){

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        String trigger = "trigger" + inputName;


        if(sharedPrefs.getBoolean(trigger, false)){
            inputButton.setBackgroundColor(Color.parseColor("#8BC34A"));
            editor.putBoolean(trigger, false);
            editor.apply();
        }
        else{
            inputButton.setBackgroundColor(Color.parseColor("#dddddd"));
            editor.putBoolean(trigger, true);
            editor.apply();
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
        Intent intent = new Intent(this, Setup6Activity.class);
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

        // Save something

        editor.apply();


    }

}
