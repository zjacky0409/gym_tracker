package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // link to the res/
        setContentView(R.layout.activity_main);

        // register different view
        Button normalAction = findViewById(R.id.modify_btn);
        Button tipsAction = findViewById(R.id.view_tips);
        Button view_report_btn = findViewById(R.id.view_report_btn);

        getSupportActionBar().setTitle("Main Page");

        // Event Handling For different buttons
        normalAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToCalendar(v);
            }
        });

        view_report_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToReport(v);
            }
        });

        tipsAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToViewReport(v);
            }
        });


    }


    public void goToCalendar(View view) {
        // Go to Calendar Activity
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void goToReport(View view) {
        // Go to ViewReport Activity
        Intent intent = new Intent(this, ViewReportActivity.class);
        startActivity(intent);
    }

    public void goToViewReport(View view) {
        // Go to ShowTips Activity
        Intent intent = new Intent(this, ShowTipsActivity.class);
        startActivity(intent);
    }


    // a class to show the usage of retrofit
    // response is a list
    public void downloadData() {
        // ref from http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
        // addConverterFactory: what kinds of format that you want it.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPIService fakeAPIService = retrofit.create(MyAPIService.class);

        Call<List<Post>> call = fakeAPIService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // by using foreach to select the data
                for (Post item : response.body()) {
                    Log.d("Testing", "id: " + item.getId());
                    Log.d("Testing", "title: " + item.getTitle());
                    Log.d("Testing", "body: " + item.getBody());
                    Log.d("Testing", "userId: " + item.getUserId());
                }

                Log.d("Testing Again", "Testing for async await");

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Testing", "response: " + t.toString());
            }
        });

        Log.d("Testing", "Testing for async await");
    }
}