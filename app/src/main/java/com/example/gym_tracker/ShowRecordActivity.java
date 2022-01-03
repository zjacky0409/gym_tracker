package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowRecordActivity extends AppCompatActivity {


    String mDate;

    List<String> name = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        Bundle bundle = getIntent().getExtras();
        mDate = bundle.getString("Date");

        getSupportActionBar().setTitle("Show Event Page");

        Button addEvent = findViewById(R.id.add_event);

        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddRecordActivity(v);
            }
        });


        // download the record list from the server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:3000/record/getRecordList/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        // calling a method to create a post and passing our modal class.
        Call<List<RecordOnlyName>> call = retrofitAPI.getRecords(mDate);

        // on below line we are executing our method.
        call.enqueue(new Callback<List<RecordOnlyName>>() {
            @Override
            public void onResponse(Call<List<RecordOnlyName>> call, Response<List<RecordOnlyName>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    name.add(response.body().get(i).getName());
                }

                TextView showText = findViewById(R.id.textView);
                showText.setText(mDate);

                // Build the RecyclerView For the record list
                RecyclerView list = findViewById(R.id.event_list_recyclar_view);

                RecordListAdapter customAdaptor = new RecordListAdapter(ShowRecordActivity.this,
                        R.layout.event_summary, name, mDate);
                list.setAdapter(customAdaptor);
                list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

            }

            @Override
            public void onFailure(Call<List<RecordOnlyName>> call, Throwable t) {


            }
        });

//        the problem of async and await, so we cannot put code here
//        TextView showText = findViewById(R.id.textView);
//        showText.setText(mDate);
//        RecyclerView list = (RecyclerView) findViewById(R.id.event_list_recyclar_view);
//
//        EventListAdapter customAdaptor = new EventListAdapter(this,
//                R.layout.event_summary, pokemonImgUrl, name);
//        list.setAdapter(customAdaptor);
//        list.setLayoutManager(new GridLayoutManager(this, 1));
    }


    // Go To AddRecord Activity
    public void AddRecordActivity(View view) {
        Intent intent = new Intent(this, AddRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", mDate);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1); // warning here
    }

    // reload the page and update the data
    public void reloadThePage() {
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            String result = data.getExtras().getString("Finish");
            if (result.equals("true")) {
                reloadThePage();
            }
        } catch (Exception e) {
            Log.d("No Finish", "Error Here");
        }

    }

// explain the usage of volley
//    public void downloadDataExamlple(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//         String url = "http://192.168.0.179:8081/";
//
//        RequestFuture<String> future = RequestFuture.newFuture();
//         // Request a string response from the provided URL.
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray errorArray = response.getJSONArray("exercises");
//                            for (int i = 0; i < errorArray.length(); i++) {
//                                Log.d("THE exercises = ", (String) errorArray.optString(i));
//                                name.add(errorArray.optString(i));
//                            }
//                        } catch (JSONException e) {
//                            Log.d("Error here", e.toString());
//                            e.printStackTrace();
//                        }
//                        TextView showText = findViewById(R.id.textView);
//                        showText.setText(mDate);
//                        RecyclerView list = (RecyclerView) findViewById(R.id.event_list_recyclar_view);
//
//                        EventListAdapter customAdaptor = new EventListAdapter(getApplicationContext(),
//                                R.layout.event_summary, pokemonImgUrl, name,mDate);
//                        list.setAdapter(customAdaptor);
//                        list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Send Error here", error.toString());
//            }
//        });
//
//        queue.add(stringRequest);
//
//    }

}