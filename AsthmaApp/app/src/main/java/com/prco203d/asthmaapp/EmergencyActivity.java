package com.prco203d.asthmaapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class EmergencyActivity extends AppCompatActivity implements View.OnClickListener {
    int i = 0;
    long sleepTime = 1;
    int numPuffs = 0;
    CountDownTimer cwCountDownTimer;
    Button btnStartTimer;
    TextView txtEmergencyCountDown;
    Toast symptom = null;
    ImageView imageView = null;
    ImageView imageView2 = null;
    ImageView imageView3 = null;
    ImageView imageView4 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        symptom = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);



        final ProgressBar cwProgressBar = (ProgressBar) findViewById(R.id.emergencyProgressBar);
        btnStartTimer = (Button)findViewById(R.id.emergencyStartTimer);
        imageView           = (ImageView) findViewById(R.id.imageView);
        imageView2    = (ImageView) findViewById(R.id.imageView2);
        imageView3        = (ImageView) findViewById(R.id.imageView3);
        imageView4          = (ImageView) findViewById(R.id.imageView4);
        btnStartTimer.setOnClickListener(this);
        txtEmergencyCountDown = (TextView)findViewById(R.id.emergencyCountdown);
        cwProgressBar.setProgress(i);



        cwCountDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //every tick add to I and set the progress bar to i
                i++;
                cwProgressBar.setProgress(i);
            }

            @Override
            public void onFinish() {
                //when timer finished change text and wait 2 seconds before begining again
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                symptom.setText("Placeholder");
                symptom.show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                symptom.setText("Placeholder");
                symptom.show();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                symptom.setText("Placeholder");
                symptom.show();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                symptom.setText("Placeholder");
                symptom.show();
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void EmergencyPuffTimer()
    {
        //disable the button to start timer again
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
