package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class WellActivity extends AppCompatActivity implements View.OnClickListener {

    public static Button topLeftButton = null;
    Button bottomLeftButton = null;
    Button bottomRightButton = null;
    ImageButton wheezingImageButton = null;
    ImageButton chestTightnesImageButton = null;
    ImageButton breathlessImageButton = null;
    ImageButton coughingImageButton = null;
    Toast symptom = null;
    int peak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well);

        symptom = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        Toolbar toolbar              = (Toolbar) findViewById(R.id.toolbar);
        topLeftButton                = (Button) findViewById(R.id.topLeftButton);
        bottomLeftButton             = (Button) findViewById(R.id.bottomLeftButton);
        bottomRightButton            = (Button) findViewById(R.id.bottomRightButton);
        wheezingImageButton          = (ImageButton) findViewById(R.id.wheezingImageButton);
        chestTightnesImageButton     = (ImageButton) findViewById(R.id.chestTightnesImageButton);
        breathlessImageButton        = (ImageButton) findViewById(R.id.breathlessImageButton);
        coughingImageButton          = (ImageButton) findViewById(R.id.coughingImageButton);

        topLeftButton.setOnClickListener(this);
        bottomLeftButton.setOnClickListener(this);
        bottomRightButton.setOnClickListener(this);
       // wheezingImageButton.setOnClickListener(this);



        //topLeftButton.setText(getIntent().getExtras().getString("MaxPeakFlow"));
        //topLeftButton.setText(MaxPeakFlow); // SORT THIS OUT

        peak = (sharedPrefs.getInt("Peak", 0));
        topLeftButton.setText("" + peak);


        wheezingImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                // symptom.cancel();
                symptom.setText(R.string.wheezing_popup);
                symptom.show();
            }
        });


        chestTightnesImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                //symptom.cancel();
                symptom.setText(R.string.cant_talk_popup);
                symptom.show();
            }
        });
        breathlessImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
               // symptom.cancel();
                symptom.setText(R.string.breathless_popup);
                symptom.show();
            }
        });
        coughingImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(WellActivity.this, Pop.class));
                //symptom.cancel();
                symptom.setText(R.string.coughing_popup);
                symptom.show();
            }
        });


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

    }


}
