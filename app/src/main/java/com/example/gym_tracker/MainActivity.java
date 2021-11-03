package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button normalAction = findViewById(R.id.modify_btn);
        Button tipsAction = findViewById(R.id.view_tips);

        getSupportActionBar().setTitle("Main Page");

        normalAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToCalendar(v);
            }
        });

        tipsAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToViewReport(v);
            }
        });

// ref from http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
//        // addConverterFactory: what kinds of format that you want it.
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MyAPIServiceTesting fakeAPIService = retrofit.create(MyAPIServiceTesting.class);
//
//        Call<List<Post>> call = fakeAPIService.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                // by using foreach to select the data
//                for (Post item : response.body()) {
//                    Log.d("Testing", "id: " + item.getId());
//                    Log.d("Testing", "title: " + item.getTitle());
//                    Log.d("Testing", "body: " + item.getBody());
//                    Log.d("Testing", "userId: " + item.getUserId());
//                }
//
//                Log.d("Testing Again Ar","Testing for async await");
//
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.d("Testing", "response: " + t.toString());
//            }
//        });
//
//        Log.d("Testing","Testing for async await");

    }


    public void goToCalendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void goToViewReport(View view) {
        Intent intent = new Intent(this, ShowTipsActivity.class);
        startActivity(intent);
    }
}