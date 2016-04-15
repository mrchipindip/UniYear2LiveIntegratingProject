package com.prco203d.asthmaapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {


    private EditText peakFlowVariableEditText = null;
    private Button submitButton = null;
    private Button EditButton = null;

    public int peakFlowToday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar          = (Toolbar) findViewById(R.id.toolbar);
        submitButton             = (Button) findViewById(R.id.submitButton);
        peakFlowVariableEditText = (EditText) findViewById(R.id.peakFlowVariableEditText);
        EditButton               = (Button) findViewById(R.id.EditButton);

        submitButton.setOnClickListener(this);
        peakFlowVariableEditText.setOnKeyListener(this);
        EditButton.setOnClickListener(this);


        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openWell(View view) {
        Intent intent = new Intent(this, WellActivity.class);
        startActivity(intent);
    }


    public void openUnwell(View view) {
        Intent intent = new Intent(this, UnwellActivity.class);
        startActivity(intent);
    }

    public void openEmergency(View view) {
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivity(intent);
    }

    public void openMyData(View view) {
        Intent intent = new Intent(this, MyDataActivity.class);
        startActivity(intent);
    }

    public void openGraphAndCalendar(View view) {
        Intent intent = new Intent(this, GraphAndCalendarActivity.class);
        startActivity(intent);
    }

    public void openMedRef(View view) {
        Intent intent = new Intent(this, MedRefActivity.class);
        startActivity(intent);
    }

    public void openAppSettings(View view) {
        Intent intent = new Intent(this, AppSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:

            try {
                peakFlowToday = Integer.valueOf(peakFlowVariableEditText.getText().toString());
                submitButton.setEnabled(false);
                Toast submitToast = Toast.makeText(this, "Peak flow submitted: " + peakFlowToday , Toast.LENGTH_LONG);
                submitToast.setGravity(Gravity.TOP, -430, 430);
                submitToast.show();

            } catch (NumberFormatException ex) {
                //Toast.makeText(this, R.string.no_value, Toast.LENGTH_SHORT).show();
                Toast noValueToast = Toast.makeText(this, R.string.no_value, Toast.LENGTH_LONG);
                noValueToast.setGravity(Gravity.TOP, -430, 430);
                noValueToast.show();
            }
                break;

            case R.id.EditButton:
                try {
                    peakFlowToday = Integer.valueOf(peakFlowVariableEditText.getText().toString());
                    Toast editToast = Toast.makeText(this, "Todays peak flow has been changed to: " + peakFlowToday, Toast.LENGTH_LONG);
                    editToast.setGravity(Gravity.TOP, -430, 430);
                    editToast.show();
                } catch (NumberFormatException ex) {
                    Toast noValueToast = Toast.makeText(this, R.string.no_value, Toast.LENGTH_LONG);
                    noValueToast.setGravity(Gravity.TOP, -430, 430);
                    noValueToast.show();
                }
                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }



    }

