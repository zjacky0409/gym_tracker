package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowTipsActivity extends AppCompatActivity {


    List<Tips> name = new ArrayList<Tips>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips);

        FloatingActionButton addTips = findViewById(R.id.addTips);

        addTips.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddTipsActivity(v);
            }
        });

        getSupportActionBar().setTitle("Tips Page");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:3000/tips/getTipsList/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        Call<List<Tips>> call = retrofitAPI.getTips();

        // on below line we are executing our method.
        call.enqueue(new Callback<List<Tips>>() {
            @Override
            public void onResponse(Call<List<Tips>> call, Response<List<Tips>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    name.add(response.body().get(i));
                }
                RecyclerView list = findViewById(R.id.tips_list);

                TipsListAdapter customAdaptor = new TipsListAdapter(ShowTipsActivity.this,
                        R.layout.exercise_summary, name);
                list.setAdapter(customAdaptor);
                list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

            }

            @Override
            public void onFailure(Call<List<Tips>> call, Throwable t) {


            }
        });
    }

    public void reloadThePage() {
        finish();
        startActivity(getIntent());
    }

    public void goToAddTipsActivity(View view) {
        Intent intent = new Intent(this, AddTipsActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reloadThePage();
    }

}