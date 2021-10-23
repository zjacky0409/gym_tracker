package com.example.gym_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        TextView name = findViewById(R.id.event_name);
        TextView set = findViewById(R.id.set);
        Button addEventBtn = findViewById(R.id.save_btn);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Name",name.getText().toString());
                Log.d("Set",set.getText().toString());
                postData(name.getText().toString(),Integer.parseInt(set.getText().toString()));
            }
        });
    }

    private void postData(String name, int set) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:8081/addEvent/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIServiceTesting retrofitAPI = retrofit.create(MyAPIServiceTesting.class);

        // passing data from our text fields to our modal class.
        DataTesting modal = new DataTesting(name, set);

        // calling a method to create a post and passing our modal class.
        Call<DataTesting> call = retrofitAPI.createData(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataTesting>() {
            @Override
            public void onResponse(Call<DataTesting> call, Response<DataTesting> response) {

                Log.d("response name", response.body().getName());
                Log.d("response set", response.body().getSet()+"");

            }

            @Override
            public void onFailure(Call<DataTesting> call, Throwable t) {


            }
        });
    }
}