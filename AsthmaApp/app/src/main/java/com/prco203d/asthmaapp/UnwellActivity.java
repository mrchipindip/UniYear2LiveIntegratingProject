package com.prco203d.asthmaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class UnwellActivity extends AppCompatActivity {

    ImageButton symptomsImageButton = null;
    ImageButton cantSleepImageButton = null;
    ImageButton cantTalkImageButton = null;
    ImageButton peakFlowDropImageButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unwell);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        symptomsImageButton = (ImageButton) findViewById(R.id.symptomsImageButton);
        cantSleepImageButton = (ImageButton) findViewById(R.id.cantSleepImageButton);
        cantTalkImageButton = (ImageButton) findViewById(R.id.cantTalkImageButton);
        peakFlowDropImageButton = (ImageButton) findViewById(R.id.peakFlowDropImageButton);


        symptomsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                Toast.makeText(getApplicationContext(), R.string.symptoms_popup, Toast.LENGTH_LONG).show();
            }
        });
        cantSleepImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                Toast.makeText(getApplicationContext(), R.string.cant_sleep_popup, Toast.LENGTH_LONG).show();
            }
        });
        cantTalkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                Toast.makeText(getApplicationContext(), R.string.cant_talk_popup, Toast.LENGTH_LONG).show();
            }
        });
        peakFlowDropImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                Toast.makeText(getApplicationContext(), R.string.peakflow_drop_popup, Toast.LENGTH_LONG).show();
            }
        });


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
