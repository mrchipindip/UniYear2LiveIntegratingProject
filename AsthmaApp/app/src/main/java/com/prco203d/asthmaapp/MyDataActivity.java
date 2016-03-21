package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MyDataActivity extends AppCompatActivity {

    private TextView textTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textTitle = (TextView) findViewById(R.id.textViewTitle);

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String name = sharedPrefs.getString("Name", "");

        textTitle.setText(name + "'s Data");


    }

  //  @Override
//    protected void onStart() {
//        super.onStart();

//    }


    public void openSetup(View view) {
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
    }

}
