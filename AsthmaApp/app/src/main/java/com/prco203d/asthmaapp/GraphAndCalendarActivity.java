package com.prco203d.asthmaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class GraphAndCalendarActivity extends AppCompatActivity {

    ArrayList<Integer> pfValues = new ArrayList<Integer>();
    ArrayList<Long> pfDates = new ArrayList<Long>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGraph();
        //buildCalendar();
    }


    void buildGraph() {

        setContentView(R.layout.activity_graph_and_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d4 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d5 = calendar.getTime();

        GraphView graph = (GraphView) findViewById(R.id.PFGraph);

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(d1, 300),
                    new DataPoint(d2, 325),
                    new DataPoint(d3, 300),
                    new DataPoint(d4, 375),
                    new DataPoint(d5, 400)
            });
            graph.addSeries(series);
        //currently adding value passing


        graph.setTitle("Peak Flow Graph");
        //adds Zooming functionality
        graph.getViewport().setScalable(true);
        //allows Scrolling
        graph.getViewport().setScrollable(true);
        // set date label formatter (shortens them to standard date format)
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));

        graph.getGridLabelRenderer().setNumHorizontalLabels(5); // defines number of imputs
        //sets the spacing
        //graph.getViewport().setMinX(d1.getTime());
        //graph.getViewport().setMaxX(d3.getTime());
       // graph.getViewport().setXAxisBoundsManual(true);

    }

    void openPFSharedPref()
    {
        pfValues.clear();
        pfDates.clear();

        SharedPreferences sharedPrefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

        String savedPFValues = sharedPrefs.getString("PFValues", "");
        StringTokenizer st = new StringTokenizer(savedPFValues, ",");
        String savedPFDate = sharedPrefs.getString("PFValues", "");
        StringTokenizer stDate = new StringTokenizer(savedPFDate, ",");

        int numValues = st.countTokens();

        for (int i = 0; i < numValues; i++) {
            pfValues.add(Integer.parseInt(st.nextToken()));
            pfDates.add(Long.parseLong(stDate.nextToken()));
        }

    }

    void loadGraphValues(ArrayList passedPFSet, ArrayList passedDateSet)
    {
        ArrayList<Integer> pfList = new ArrayList<Integer>(passedPFSet);
        ArrayList<Long> dateList = new ArrayList<Long>(passedDateSet);

        GraphView graph = (GraphView) findViewById(R.id.PFGraph);

        for (int i = 0; i<pfList.size(); i++) {

            Date tempDate = new Date(dateList.get(i));

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                    new DataPoint(tempDate, pfList.get(i))

            });
            graph.addSeries(series);
        }
    }

//    void buildCalendar()
//    {
//        Calendar nextYear = Calendar.getInstance();
//        nextYear.add(Calendar.YEAR, 1);
//        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
//        Date today = new Date();
//        calendar.init(today, nextYear.getTime())
//                .withSelectedDate(today);
//    }
//    // internal components
//    private LinearLayout header;
//    private ImageView btnPrev;
//    private ImageView btnNext;
//    private TextView txtDate;
//    private GridView grid;
//
//    public GraphAndCalendarActivity(Context context)
//    {
//        super(context);
//        initControl(context);
//    }
//
//    /**
//     * Load component XML layout
//     */
//    private void initControl(Context context)
//    {
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        inflater.inflate(R.layout.control_calendar, this);
//
//        // layout is inflated, assign local variables to components
//        header = (LinearLayout)findViewById(R.id.calendar_header);
//        btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
//        btnNext = (ImageView)findViewById(R.id.calendar_next_button);
//        txtDate = (TextView)findViewById(R.id.calendar_date_display);
//        grid = (GridView)findViewById(R.id.calendar_grid);
//    }
//
//    private void updateCalendar()
//    {
//        ArrayList<Date> cells = new ArrayList<>();
//        Calendar calendar = (Calendar)currentDate.clone();
//
//        // determine the cell for current month's beginning
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//
//        // move calendar backwards to the beginning of the week
//        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
//
//        // fill cells (42 days calendar as per our business logic)
//        while (cells.size() < DAYS_COUNT)
//        {
//            cells.add(calendar.getTime());
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//
//        // update grid
//        ((CalendarAdapter)grid.getAdapter()).updateData(cells);
//
//        // update title
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
//        txtDate.setText(sdf.format(currentDate.getTime()));
//    }
//
//    private class CalendarAdapter extends ArrayAdapter<Date>
//    {
//        // days with events
//        private HashSet<Date> eventDays;
//
//        // for view inflation
//        private LayoutInflater inflater;
//
//        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
//        {
//            super(context, R.layout.control_calendar_day, days);
//            this.eventDays = eventDays;
//            inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup parent)
//        {
//            // day in question
//            Date date = getItem(position);
//            int day = date.getDate();
//            int month = date.getMonth();
//            int year = date.getYear();
//
//            // today
//            Date today = new Date();
//
//            // inflate item if it does not exist yet
//            if (view == null)
//                view = inflater.inflate(R.layout.control_calendar_day, parent, false);
//
//            // if this day has an event, specify event image
//            view.setBackgroundResource(0);
//            if (eventDays != null)
//            {
//                for (Date eventDate : eventDays)
//                {
//                    if (eventDate.getDate() == day &&
//                            eventDate.getMonth() == month &&
//                            eventDate.getYear() == year)
//                    {
//                        // mark this day for event
//                        view.setBackgroundResource(R.drawable.reminder);
//                        break;
//                    }
//                }
//            }
//
//            // clear styling
//            ((TextView)view).setTypeface(null, Typeface.NORMAL);
//            ((TextView)view).setTextColor(Color.BLACK);
//
//            if (month != today.getMonth() || year != today.getYear())
//            {
//                // if this day is outside current month, grey it out
//                ((TextView)view).setTextColor(getResources().getColor(R.color.greyed_out));
//            }
//            else if (day == today.getDate())
//            {
//                // if it is today, set it to blue/bold
//                ((TextView)view).setTypeface(null, Typeface.BOLD);
//                ((TextView)view).setTextColor(getResources().getColor(R.color.today));
//            }
//
//            // set text
//            ((TextView)view).setText(String.valueOf(date.getDate()));
//
//            return view;
//        }
//    }
    //void buildCalendar()
    //{
    //    CalendarView calendar = (CalendarView) findViewById(R.id.PFCalendar);
    //    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
   //         @Override
   //         public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
    //            Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
    //        }
     //   });
   // }

}


