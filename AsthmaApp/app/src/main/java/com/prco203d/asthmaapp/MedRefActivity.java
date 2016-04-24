package com.prco203d.asthmaapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Calendar;

public class MedRefActivity extends AppCompatActivity implements View.OnClickListener {

    private PendingIntent pendingIntent;
    private CheckBox monthlyCheck;
    Button everydayCareButton = null;
    Button whenFeelingWorseButton = null;
    Button inAnAsthmaAttackButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_ref);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        monthlyCheck = (CheckBox) findViewById(R.id.monthlyCheck);
        everydayCareButton = (Button) findViewById(R.id.everydayCareButton);
        whenFeelingWorseButton = (Button) findViewById(R.id.whenFeelingWorseButton);
        inAnAsthmaAttackButton = (Button) findViewById(R.id.inAnAsthmaAttackButton);


        setSupportActionBar(toolbar);

        Intent alarmIntent = new Intent(MedRefActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MedRefActivity.this, 0, alarmIntent, 0);

        everydayCareButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WellActivity.class);
                startActivity(intent);

            }
        }));
        whenFeelingWorseButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UnwellActivity.class);
                startActivity(intent);

            }
        }));
        inAnAsthmaAttackButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EmergencyActivity.class);
                startActivity(intent);

            }
        }));
        monthlyCheck.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    start();
                    startAt10();
                } else
                    cancel();
            }
        }));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }


    @Override
    public void onClick(View v) {

    }
}
