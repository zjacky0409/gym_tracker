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

public class ShowEventActivity extends AppCompatActivity {


    String mDate;

    List<String> name = new ArrayList<String>();

    String[] pokemonImgUrl = new String[]{
            "https://img.pokemondb.net/artwork/large/pikachu.jpg",
            "https://img.pokemondb.net/artwork/squirtle.jpg",
            "https://img.pokemondb.net/artwork/large/tauros.jpg",
            "https://img.pokemondb.net/artwork/large/bouffalant.jpg",
            "https://img.pokemondb.net/artwork/terrakion.jpg"
            , "https://img.pokemondb.net/artwork/rattata.jpg"
            , "https://img.pokemondb.net/artwork/raticate.jpg"
            , "https://img.pokemondb.net/artwork/patrat.jpg"
            , "https://img.pokemondb.net/artwork/pichu.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        Bundle bundle = getIntent().getExtras();
        mDate = bundle.getString("Date");

        Button addEvent = findViewById(R.id.add_event);
        Log.d("Test0", this.toString());
        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddEventActivity(v);
            }
        });


        // ref from http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
        // Instantiate the RequestQueue.
        // RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.179:8081/";

//        RequestFuture<String> future = RequestFuture.newFuture();
        // Request a string response from the provided URL.
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray errorArray = response.getJSONArray("exercises");
//                            for (int i = 0; i < errorArray.length(); i++) {
//                                Log.d("THE exercises = ", (String) errorArray.optString(i));
//                                pokemonName[i] = errorArray.optString(i);
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
//                                R.layout.event_summary, pokemonImgUrl, pokemonName,mDate);
//                        list.setAdapter(customAdaptor);
//                        list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                pokemonName[0] = "ERROR HERE";
//                Log.d("Send Error here", error.toString());
//            }
//        });
//
//        queue.add(stringRequest);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:8081/getExercises/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        // calling a method to create a post and passing our modal class.
        Call<List<ExerciseOnlyName>> call = retrofitAPI.getExercises(mDate);

        // on below line we are executing our method.
        call.enqueue(new Callback<List<ExerciseOnlyName>>() {
            @Override
            public void onResponse(Call<List<ExerciseOnlyName>> call, Response<List<ExerciseOnlyName>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    name.add(response.body().get(i).getExercises());
                }

                Log.d("Number that we want", name.toString());
                TextView showText = findViewById(R.id.textView);
                showText.setText(mDate);
                RecyclerView list = findViewById(R.id.event_list_recyclar_view);

                Log.d("Test1", ShowEventActivity.this.toString());
                EventListAdapter customAdaptor = new EventListAdapter(ShowEventActivity.this,
                        R.layout.event_summary, pokemonImgUrl, name, mDate);
                list.setAdapter(customAdaptor);
                list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

            }

            @Override
            public void onFailure(Call<List<ExerciseOnlyName>> call, Throwable t) {


            }
        });


//        TextView showText = findViewById(R.id.textView);
//        showText.setText(mDate);
//        RecyclerView list = (RecyclerView) findViewById(R.id.event_list_recyclar_view);
//
//        EventListAdapter customAdaptor = new EventListAdapter(this,
//                R.layout.event_summary, pokemonImgUrl, pokemonName);
//        list.setAdapter(customAdaptor);
//        list.setLayoutManager(new GridLayoutManager(this, 1));
    }

    public void goToAddEventActivity(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", mDate);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    public void reloadThePage() {
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getExtras().getString("Finish");
        Log.d("result", result);
        if (result.equals("true")) {
            reloadThePage();
        }
    }

}