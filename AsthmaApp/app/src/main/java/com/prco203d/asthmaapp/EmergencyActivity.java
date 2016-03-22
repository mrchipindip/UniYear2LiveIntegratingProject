package com.prco203d.asthmaapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class EmergencyActivity extends AppCompatActivity implements View.OnClickListener {
    int i = 0;
    long sleepTime = 1;
    int numPuffs = 0;
    CountDownTimer cwCountDownTimer;
    Button btnStartTimer;
    TextView txtEmergencyCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ProgressBar cwProgressBar = (ProgressBar) findViewById(R.id.emergencyProgressBar);
        btnStartTimer = (Button)findViewById(R.id.emergencyStartTimer);
        btnStartTimer.setOnClickListener(this);
        txtEmergencyCountDown = (TextView)findViewById(R.id.emergencyCountdown);
        cwProgressBar.setProgress(i);
        cwCountDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                cwProgressBar.setProgress(i);
            }

            @Override
            public void onFinish() {
                i++;
                cwProgressBar.setProgress(i);
                numPuffs++;
                txtEmergencyCountDown.setText("Puff!");
                new CountDownTimer(2000, 1000) {
                    public void onFinish() {
                        EmergencyPuffTimer();
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();


            }
        };


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void EmergencyPuffTimer()
    {
        btnStartTimer.setEnabled(false);
        if(numPuffs < 10)
        {
            txtEmergencyCountDown.setText("Get Ready..");
            i = 0;
            cwCountDownTimer.start();
        }
        else
        {
            btnStartTimer.setEnabled(true);
            numPuffs = 0;
            txtEmergencyCountDown.setText("Get Ready..");
        }

    }
    @Override
    public void onClick(View v) {

        EmergencyPuffTimer();

    }

}
