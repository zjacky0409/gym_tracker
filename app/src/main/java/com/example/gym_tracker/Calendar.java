package com.example.gym_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView show_date = findViewById(R.id.show_date);

        Date c = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
        date = df.format(c);

//        Log.d("Date That I want", date);
        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            //important: month [0-11]
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day){
                date = day + "-" + (month + 1) + "-" + year;
                Log.d("Data I want", date);
                show_date.setText(date);
            }

        });

    }
}