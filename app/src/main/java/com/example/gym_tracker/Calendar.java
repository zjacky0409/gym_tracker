package com.example.gym_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        Button goToEvent = findViewById(R.id.go_to_event);

        Date c = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
        date = df.format(c);

        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            //important: month is from 0 to 11
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day){
                date = day + "-" + (month + 1) + "-" + year;
                Log.d("Data I want", date);
                show_date.setText(date);
            }

        });

        goToEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToEventList(v);
            }
        });

    }

    public void goToEventList(View view) {
        Intent intent = new Intent(this, ShowEventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date",date);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}