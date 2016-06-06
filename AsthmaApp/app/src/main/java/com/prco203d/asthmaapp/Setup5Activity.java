package com.prco203d.asthmaapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setup5Activity extends AppCompatActivity {

    //private EditText editPeakFlow   = null;
    private AppCompatButton buttonAnimal;
    private int animalInt = 0;

    private AppCompatButton buttonDust;
    private int dustInt = 0;

    private AppCompatButton buttonEmotion;
    private int emotionInt = 0;

    private AppCompatButton buttonExercise;
    private int exerciseInt = 0;

    private AppCompatButton buttonFood;
    private int foodInt = 0;

    private AppCompatButton buttonPollen;
    private int pollenInt = 0;

    private AppCompatButton buttonSmoke;
    private int smokeInt = 0;

    private AppCompatButton buttonStress;
    private int stressInt = 0;

    private AppCompatButton buttonOdour;
    private int odourInt = 0;

    private AppCompatButton buttonVirus;
    private int virusInt = 0;

    private AppCompatButton buttonWeather;
    private int weatherInt = 0;

    private AppCompatButton buttonC1;
    private int c1Int = 0;
    private String c1Name;

    private AppCompatButton buttonC2;
    private int c2Int = 0;
    private String c2Name;

    private AppCompatButton buttonC3;
    private int c3Int = 0;
    private String c3Name;

    private AppCompatButton buttonC4;
    private int c4Int = 0;
    private String c4Name;

    private AppCompatButton buttonC5;
    private int c5Int = 0;
    private String c5Name;

    private AppCompatButton buttonC6;
    private int c6Int = 0;
    private String c6Name;

    private Button buttonUpdate;
    private Button buttonNext;
    private Button buttonPrevious;

    ColorStateList red = new ColorStateList(new int[][]{new int[0]}, new int[]{0xfff44336});
    ColorStateList amber = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffffc107});
    ColorStateList green = new ColorStateList(new int[][]{new int[0]}, new int[]{0xff8BC34A});
    ColorStateList grey = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffd3d3d3});

    //v.setSupportBackgroundTintList(red);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Change to triggers
        //editPeakFlow = (EditText) findViewById(R.id.editTextPeak);

        buttonAnimal = (AppCompatButton)findViewById(R.id.buttonAnimal);
        buttonDust = (AppCompatButton)findViewById(R.id.buttonDust);
        buttonEmotion = (AppCompatButton)findViewById(R.id.buttonEmotion);
        buttonExercise = (AppCompatButton)findViewById(R.id.buttonExercise);
        buttonFood = (AppCompatButton)findViewById(R.id.buttonFoods);
        buttonPollen = (AppCompatButton)findViewById(R.id.buttonPollen);
        buttonSmoke = (AppCompatButton)findViewById(R.id.buttonSmoke);
        buttonStress = (AppCompatButton)findViewById(R.id.buttonStress);
        buttonOdour = (AppCompatButton)findViewById(R.id.buttonOdours);
        buttonVirus = (AppCompatButton)findViewById(R.id.buttonVirus);
        buttonWeather = (AppCompatButton)findViewById(R.id.buttonWeather);

        buttonC1 = (AppCompatButton)findViewById(R.id.buttonC1);
        buttonC2 = (AppCompatButton)findViewById(R.id.buttonC2);
        buttonC3 = (AppCompatButton)findViewById(R.id.buttonC3);
        buttonC4 = (AppCompatButton)findViewById(R.id.buttonC4);
        buttonC5 = (AppCompatButton)findViewById(R.id.buttonC5);
        buttonC6 = (AppCompatButton)findViewById(R.id.buttonC6);

        buttonUpdate = (Button)findViewById(R.id.buttonSubmit);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        // Editing setup mode
        if(isSetup()){
            // Enable up button and use non-numbered activity title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_setup5_done));

            // Show submit button only
            buttonNext.setVisibility(View.INVISIBLE);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        // First time setup mode
        else{
            // Show next + previous buttons only
            buttonUpdate.setVisibility(View.INVISIBLE);
        }

        animalInt = sharedPrefs.getInt("animal", 0);
        colourButton(buttonAnimal, sharedPrefs.getInt("animal", 0));

        dustInt = sharedPrefs.getInt("dust", 0);
        colourButton(buttonDust, sharedPrefs.getInt("dust", 0));

        emotionInt = sharedPrefs.getInt("emotion", 0);
        colourButton(buttonEmotion, sharedPrefs.getInt("emotion", 0));

        exerciseInt = sharedPrefs.getInt("exercise", 0);
        colourButton(buttonExercise, sharedPrefs.getInt("exercise", 0));

        foodInt = sharedPrefs.getInt("food", 0);
        colourButton(buttonFood, sharedPrefs.getInt("food", 0));

        pollenInt = sharedPrefs.getInt("pollen", 0);
        colourButton(buttonPollen, sharedPrefs.getInt("pollen", 0));

        smokeInt = sharedPrefs.getInt("smoke", 0);
        colourButton(buttonSmoke, sharedPrefs.getInt("smoke", 0));

        stressInt = sharedPrefs.getInt("stress", 0);
        colourButton(buttonStress, sharedPrefs.getInt("stress", 0));

        odourInt = sharedPrefs.getInt("odour", 0);
        colourButton(buttonOdour, sharedPrefs.getInt("odour", 0));

        virusInt = sharedPrefs.getInt("virus", 0);
        colourButton(buttonVirus, sharedPrefs.getInt("virus", 0));

        weatherInt = sharedPrefs.getInt("weather", 0);
        colourButton(buttonWeather, sharedPrefs.getInt("weather", 0));

        c1Int = sharedPrefs.getInt("c1", 0);
        colourButton(buttonC1, c1Int);
        c1Name = sharedPrefs.getString("c1Name", "");
        if(c1Name != null && !c1Name.isEmpty()){
            buttonC1.setText(c1Name);
        }

        c2Int = sharedPrefs.getInt("c2", 0);
        colourButton(buttonC2, c2Int);
        c2Name = sharedPrefs.getString("c2Name", "");
        if(c2Name != null && !c2Name.isEmpty()){
            buttonC2.setText(c2Name);
        }

        c3Int = sharedPrefs.getInt("c3", 0);
        colourButton(buttonC3, c3Int);
        c3Name = sharedPrefs.getString("c3Name", "");
        if(c3Name != null && !c3Name.isEmpty()){
            buttonC3.setText(c3Name);
        }

        c4Int = sharedPrefs.getInt("c4", 0);
        colourButton(buttonC4, c4Int);
        c4Name = sharedPrefs.getString("c4Name", "");
        if(c4Name != null && !c4Name.isEmpty()){
            buttonC4.setText(c4Name);
        }

        c5Int = sharedPrefs.getInt("c5", 0);
        colourButton(buttonC5, c5Int);
        c5Name = sharedPrefs.getString("c5Name", "");
        if(c5Name != null && !c5Name.isEmpty()){
            buttonC5.setText(c5Name);
        }

        c6Int = sharedPrefs.getInt("c6", 0);
        colourButton(buttonC6, c6Int);
        c6Name = sharedPrefs.getString("c6Name", "");
        if(c6Name != null && !c6Name.isEmpty()){
            buttonC6.setText(c6Name);
        }

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

    public void doStuff(String inputName,  AppCompatButton inputButton){

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        String trigger = "trigger" + inputName;

        if(sharedPrefs.getBoolean(trigger, false)){
            inputButton.setSupportBackgroundTintList(red);
            editor.putBoolean(trigger, false);
            editor.apply();
        }
        else{
            inputButton.setSupportBackgroundTintList(red);
            editor.putBoolean(trigger, true);
            editor.apply();
        }
    }

    public void clickAnimal(View view){
        animalInt = cycleInt(animalInt);
        Log.i("triggers",""+animalInt);
        colourButton(buttonAnimal, animalInt);
    }

    public void clickDust(View view){
        dustInt = cycleInt(dustInt);
        Log.i("triggers",""+dustInt);
        colourButton(buttonDust, dustInt);
    }

    public void clickEmotion(View view){
        emotionInt = cycleInt(emotionInt);
        Log.i("triggers",""+emotionInt);
        colourButton(buttonEmotion, emotionInt);
    }

    public void clickExercise(View view){
        exerciseInt = cycleInt(exerciseInt);
        Log.i("triggers",""+exerciseInt);
        colourButton(buttonExercise, exerciseInt);
    }

    public void clickFood(View view){
        foodInt = cycleInt(foodInt);
        Log.i("triggers",""+foodInt);
        colourButton(buttonFood, foodInt);
    }

    public void clickPollen(View view){
        pollenInt = cycleInt(pollenInt);
        Log.i("triggers",""+pollenInt);
        colourButton(buttonPollen, pollenInt);
    }

    public void clickSmoke(View view){
        smokeInt = cycleInt(smokeInt);
        Log.i("triggers",""+smokeInt);
        colourButton(buttonSmoke, smokeInt);
    }

    public void clickStress(View view){
        stressInt = cycleInt(stressInt);
        Log.i("triggers",""+stressInt);
        colourButton(buttonStress, stressInt);
    }

    public void clickOdour(View view){
        odourInt = cycleInt(odourInt);
        Log.i("triggers",""+odourInt);
        colourButton(buttonOdour, odourInt);
    }

    public void clickVirus(View view){
        virusInt = cycleInt(virusInt);
        Log.i("triggers",""+virusInt);
        colourButton(buttonVirus, virusInt);
    }

    public void clickWeather(View view){
        weatherInt = cycleInt(weatherInt);
        Log.i("triggers",""+weatherInt);
        colourButton(buttonWeather, weatherInt);
    }

    public void clickC1(View view){

        // Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC1, position);
                        c1Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC1.setText(tempText);
                                c1Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();

    }

    public void clickC2(View view){
// Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC2, position);
                        c2Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC2.setText(tempText);
                                c2Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();

    }

    public void clickC3(View view){
// Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC3, position);
                        c3Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC3.setText(tempText);
                                c3Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();

    }

    public void clickC4(View view){

        // Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC4, position);
                        c4Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC4.setText(tempText);
                                c4Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();
    }

    public void clickC5(View view){
// Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC5, position);
                        c5Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC5.setText(tempText);
                                c5Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();

    }

    public void clickC6(View view){
// Display trigger popup
        final AlertDialog alertDialogCustom = new AlertDialog.Builder(Setup5Activity.this).create();
        alertDialogCustom.setTitle("Custom Trigger");

        final TextView triggerDesc = new TextView(Setup5Activity.this);
        triggerDesc.setText("  Description of your trigger:");

        final EditText editTriggerName = new EditText(Setup5Activity.this);
        editTriggerName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTriggerName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });

        final TextView severityDesc = new TextView(Setup5Activity.this);
        severityDesc.setText("  Select the trigger strength:");

        final RadioButton rb0 = new RadioButton(Setup5Activity.this);
        final RadioButton rb1 = new RadioButton(Setup5Activity.this);
        final RadioButton rb2 = new RadioButton(Setup5Activity.this);
        final RadioButton rb3 = new RadioButton(Setup5Activity.this);

        final RadioGroup rg = new RadioGroup(Setup5Activity.this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.addView(rb0);
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);

        rb0.setText(R.string.trigger_unselected);
        rb0.setId(R.id.id0);
        rb1.setText(R.string.trigger_mild);
        rb1.setId(R.id.id1);
        rb1.setTextColor(green);
        rb2.setText(R.string.trigger_medium);
        rb2.setId(R.id.id2);
        rb2.setTextColor(amber);
        rb3.setText(R.string.trigger_severe);
        rb3.setId(R.id.id3);
        rb3.setTextColor(red);

        LinearLayout rescueLayout = new LinearLayout(getBaseContext());
        rescueLayout.setOrientation(LinearLayout.VERTICAL);
        rescueLayout.addView(triggerDesc);
        rescueLayout.addView(editTriggerName);
        rescueLayout.addView(severityDesc);
        rescueLayout.addView(rg);


        alertDialogCustom.setView(rescueLayout);

        alertDialogCustom.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialogCustom.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Can set the temporary values into permanent ones on confirmation
                        int checked = rg.getCheckedRadioButtonId();
                        View radioButton = rg.findViewById(checked);
                        int position = rg.indexOfChild(radioButton);

                        Log.i("trigger", ""+position);

                        colourButton(buttonC6, position);
                        c6Int = position;

                        // set button text
                        try {
                            if (editTriggerName.getText().toString() != null && editTriggerName.getText().toString() != "" && !editTriggerName.getText().toString().isEmpty()) {
                                String tempText = editTriggerName.getText().toString();
                                buttonC6.setText(tempText);
                                c6Name = tempText;
                            }
                        }catch(Exception ex){
                        }

                        dialog.dismiss();
                    }
                });

        alertDialogCustom.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        alertDialogCustom.show();

    }

    private void colourButton(AppCompatButton button, int stateInt) {

        if(stateInt <= 0)
            button.setSupportBackgroundTintList(grey);

        if(stateInt == 1)
            button.setSupportBackgroundTintList(green);

        if(stateInt == 2)
            button.setSupportBackgroundTintList(amber);

        if(stateInt == 3)
            button.setSupportBackgroundTintList(red);

    }

    public int cycleInt(int input){

        int retInt = input;

        retInt++;

        if(retInt >= 4){
            retInt = 0;
        }

        return retInt;
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

        Intent intent = new Intent(this, Setup6Activity.class);
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

        // Save something
        editor.putInt("animal", animalInt);
        editor.putInt("emotion", emotionInt);
        editor.putInt("dust", dustInt);

        editor.putInt("exercise", exerciseInt);
        editor.putInt("food", foodInt);
        editor.putInt("pollen", pollenInt);

        editor.putInt("smoke", smokeInt);
        editor.putInt("stress", stressInt);
        editor.putInt("odour", odourInt);

        editor.putInt("virus", virusInt);
        editor.putInt("weather", weatherInt);

        if(c1Name != null && !c1Name.isEmpty()){
            editor.putString("c1Name", c1Name);
            editor.putInt("c1", c1Int);
        }

        if(c2Name != null && !c2Name.isEmpty()){
            editor.putString("c2Name", c2Name);
            editor.putInt("c2", c2Int);
        }

        if(c3Name != null && !c3Name.isEmpty()){
            editor.putString("c3Name", c3Name);
            editor.putInt("c3", c3Int);
        }

        if(c4Name != null && !c4Name.isEmpty()){
            editor.putString("c4Name", c4Name);
            editor.putInt("c4", c4Int);
        }

        if(c5Name != null && !c5Name.isEmpty()){
            editor.putString("c5Name", c5Name);
            editor.putInt("c5", c5Int);
        }

        if(c6Name != null && !c6Name.isEmpty()){
            editor.putString("c6Name", c6Name);
            editor.putInt("c6", c6Int);
        }

        editor.apply();


    }

}
