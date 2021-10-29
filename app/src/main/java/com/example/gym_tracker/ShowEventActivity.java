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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowEventActivity extends AppCompatActivity {


    String mDate;

    String[] pokemonName = new String[]{"Bench Press", "Squat"
            , "RDL", "Lat Pull Down", "Seated Row"
            , "Rattata", "Raticate", "Patrat", "Pichu"
    };

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

        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddEventActivity(v);
            }
        });


        // ref from http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.179:8081/";

//        RequestFuture<String> future = RequestFuture.newFuture();
        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
//                        try {
//                            Log.d("Success here", response.get("exercises").toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            pokemonName = (String[]) response.get("exercises");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        try {
                            JSONArray errorArray = response.getJSONArray("exercises");
                            for (int i = 0; i < errorArray.length(); i++) {
                                Log.d("THE exercises = ", (String) errorArray.optString(i));
                                pokemonName[i] = errorArray.optString(i);
                            }
                        } catch (JSONException e) {
                            Log.d("Error here", e.toString());
                            e.printStackTrace();
                        }
                        TextView showText = findViewById(R.id.textView);
                        showText.setText(mDate);
                        RecyclerView list = (RecyclerView) findViewById(R.id.event_list_recyclar_view);

                        EventListAdapter customAdaptor = new EventListAdapter(getApplicationContext(),
                                R.layout.event_summary, pokemonImgUrl, pokemonName);
                        list.setAdapter(customAdaptor);
                        list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pokemonName[0] = "ERROR HERE";
                Log.d("Send Error here", error.toString());
            }
        });

        queue.add(stringRequest);

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,future,future);
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
//        try {
//            String response = future.get(1, TimeUnit.SECONDS);
//            Log.d("Result", response);
//            // do something with response
//            pokemonName[0] = response;
//        } catch (InterruptedException e) {
//            // handle the error
//            Log.d("Error here", e.toString());
//        } catch (ExecutionException e) {
//            // handle the error
//            Log.d("Error here", e.toString());
//        } catch (TimeoutException e) {
//            Log.d("Error here",e.toString());
//            e.printStackTrace();
//        }


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
        startActivity(intent);
    }
}