package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Calendar extends AppCompatActivity {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView show_date = findViewById(R.id.show_date);
        Button goToEvent = findViewById(R.id.go_to_event);

        getSupportActionBar().setTitle("Calendar Page");

        Date c = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
        date = df.format(c);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            //important: month is from 0 to 11
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                date = day + "-" + (month + 1) + "-" + year;
                show_date.setText(date);
                Toast.makeText(getApplicationContext(), "You have selected " + date, Toast.LENGTH_SHORT).show();
            }

        });

        goToEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToEventList(v);
            }
        });


    }

    public void goToEventList(View view) {
        Intent intent = new Intent(this, ShowRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", date);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // a class to show the usage of retrofit
    // response is a object
    public void downloadData() {
        // An example to show the usage of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:8081/testingAgain/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPIService fakeAPIService = retrofit.create(MyAPIService.class);

        Call<RecordOnlyName> call = fakeAPIService.getMyDataByPost();

        call.enqueue(new Callback<RecordOnlyName>() {
            @Override
            public void onResponse(Call<RecordOnlyName> call, Response<RecordOnlyName> response) {
                Log.d("TESTING AR", "exercise: " + response.body().getName());

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("ERROR", "response: " + t.toString());
            }
        });
    }

}