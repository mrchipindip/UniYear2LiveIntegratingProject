package com.prco203d.asthmaapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MedRefActivity extends AppCompatActivity implements View.OnClickListener {

    private PendingIntent pendingIntent;
    private Button monthlyCheckEnabled;
    private Button monthlyCheckDisbaled;
    private Button fiveSecondAlarm;
    Button everydayCareButton = null;
    Button whenFeelingWorseButton = null;
    Button inAnAsthmaAttackButton = null;

    NotificationManager notificationManager;
    boolean isNotificActive = false;
    int notifID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_ref);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        everydayCareButton = (Button) findViewById(R.id.everydayCareButton);
        whenFeelingWorseButton = (Button) findViewById(R.id.whenFeelingWorseButton);
        inAnAsthmaAttackButton = (Button) findViewById(R.id.inAnAsthmaAttackButton);
        //remindMeMonthly = (CheckBox) findViewById(R.id.checkBoxMonthly);
        //monthlyCheckEnabled = (Button) findViewById(R.id.monthlyCheckEnabled);
        //monthlyCheckDisbaled = (Button) findViewById(R.id.monthlyCheckDisabled);
        fiveSecondAlarm = (Button) findViewById(R.id.fiveSecondAlarm);




        setSupportActionBar(toolbar);

        //Intent alarmIntent = new Intent(MedRefActivity.this, AlarmReceiver.class);
       // pendingIntent = PendingIntent.getBroadcast(MedRefActivity.this, 0, alarmIntent, 0);

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






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void enableAlarm(View view) {
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));

    }

    public void disableAlarm(View view)
    {
       //AlarmManager.cancel(this) ;
    }



    @Override
    public void onClick(View v) {

    }
}
