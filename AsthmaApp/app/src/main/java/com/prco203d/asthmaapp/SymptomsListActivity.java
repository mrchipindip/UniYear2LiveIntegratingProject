package com.prco203d.asthmaapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

public class SymptomsListActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expListView = (ExpandableListView) findViewById(R.id.exp_Symptoms_List);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Symptom example 1");
        listDataHeader.add("Symptom example 2");
        listDataHeader.add("Symptom example 3");

        List<String> symptom1 = new ArrayList<String>();
        symptom1.add("Symptom 1 info");

        List<String> symptom2 = new ArrayList<String>();
        symptom2.add("Symptom 2 info");

        List<String> symptom3 = new ArrayList<String>();
        symptom3.add("Symptom 3 info");

        listDataChild.put(listDataHeader.get(0), symptom1);
        listDataChild.put(listDataHeader.get(1), symptom2);
        listDataChild.put(listDataHeader.get(2), symptom3);
    }

}

