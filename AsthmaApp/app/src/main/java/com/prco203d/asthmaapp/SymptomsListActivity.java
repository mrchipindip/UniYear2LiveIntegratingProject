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

        listDataHeader.add(getResources().getString(R.string.symptom_heading_breathing_difficulty));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_coughing));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_exercising_difficulty));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_talking_difficulty));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_tight_chest));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_waking));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_walking_difficulty));
        listDataHeader.add(getResources().getString(R.string.symptom_heading_wheezing));

        List<String> symptom1 = new ArrayList<String>();
        symptom1.add(getResources().getString(R.string.symptom_description_breathing_difficulty));

        List<String> symptom2 = new ArrayList<String>();
        symptom2.add(getResources().getString(R.string.symptom_description_coughing));

        List<String> symptom3 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_exercising_difficulty));

        List<String> symptom4 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_talking_difficulty));

        List<String> symptom5 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_tight_chest));

        List<String> symptom6 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_waking));

        List<String> symptom7 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_walking_difficulty));

        List<String> symptom8 = new ArrayList<String>();
        symptom3.add(getResources().getString(R.string.symptom_description_wheezing));

        listDataChild.put(listDataHeader.get(0), symptom1);
        listDataChild.put(listDataHeader.get(1), symptom2);
        listDataChild.put(listDataHeader.get(2), symptom3);
        listDataChild.put(listDataHeader.get(3), symptom4);
        listDataChild.put(listDataHeader.get(4), symptom5);
        listDataChild.put(listDataHeader.get(5), symptom6);
        listDataChild.put(listDataHeader.get(6), symptom7);
        listDataChild.put(listDataHeader.get(7), symptom8);


    }

}

