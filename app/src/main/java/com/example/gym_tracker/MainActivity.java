package com.example.gym_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button normalAction = findViewById(R.id.modify_btn);

        normalAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToCalendar(v);
            }
        });


    }


    public void goToCalendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}