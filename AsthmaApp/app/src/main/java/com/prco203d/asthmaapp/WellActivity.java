package com.prco203d.asthmaapp;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WellActivity extends AppCompatActivity implements View.OnClickListener {

    Button topLeftButton = null;
    Button bottomLeftButton = null;
    Button bottomRightButton = null;
    ImageButton wheezingmageButton = null;
    ImageButton chestTightnesIimageButton = null;
    ImageButton breathlessImageButton = null;
    ImageButton coughingImageButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well);

        Toolbar toolbar              = (Toolbar) findViewById(R.id.toolbar);
        topLeftButton                = (Button) findViewById(R.id.topLeftButton);
        bottomLeftButton             = (Button) findViewById(R.id.bottomLeftButton);
        bottomRightButton            = (Button) findViewById(R.id.bottomRightButton);
        wheezingmageButton           = (ImageButton) findViewById(R.id.wheezingImageButton);
        chestTightnesIimageButton    = (ImageButton) findViewById(R.id.chestTightnesImageButton);
        breathlessImageButton        = (ImageButton) findViewById(R.id.breathlessImageButton);
        coughingImageButton          = (ImageButton) findViewById(R.id.coughingImageButton);

        topLeftButton.setOnClickListener(this);
        bottomLeftButton.setOnClickListener(this);
        bottomRightButton.setOnClickListener(this);
        wheezingmageButton.setOnClickListener(this);
        chestTightnesIimageButton.setOnClickListener(this);
        breathlessImageButton.setOnClickListener(this);
        coughingImageButton.setOnClickListener(this);


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

    }
}
