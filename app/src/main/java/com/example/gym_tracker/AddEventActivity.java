package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEventActivity extends AppCompatActivity {

    public String mDate;
    public String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Bundle bundle = getIntent().getExtras();
        mDate = bundle.getString("Date");
        mName = bundle.getString("Name");

        TextView name = findViewById(R.id.event_name);
        TextView set = findViewById(R.id.set);
        TextView rest_time = findViewById(R.id.rest_time);
        TextView remark = findViewById(R.id.remark);
        TextView rpe = findViewById(R.id.rpe);
        TextView rir = findViewById(R.id.rir);
        TextView weight = findViewById(R.id.weigth);

        Button addEventBtn = findViewById(R.id.save_btn);

        RadioButton kg_btn = findViewById(R.id.kg_btn);
        RadioButton lbs_btn = findViewById(R.id.lbs_btn);

        kg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbs_btn.setChecked(false);
            }
        });

        lbs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg_btn.setChecked(false);
            }
        });

        if (mName != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.179:8081/getSingleEvent/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

            Call<Exercise> call = retrofitAPI.getSingleEvent(mDate, mName);

            // on below line we are executing our method.
            call.enqueue(new Callback<Exercise>() {
                @Override
                public void onResponse(Call<Exercise> call, Response<Exercise> response) {

                    name.setText(response.body().getName());
                    set.setText(response.body().getSet() + "");
                    rest_time.setText(response.body().getRest_time());
                    remark.setText(response.body().getRemark());
                    rpe.setText(response.body().getRpe() + "");
                    rir.setText(response.body().getRir() + "");
                    weight.setText(response.body().getWeight() + "");

                }

                @Override
                public void onFailure(Call<Exercise> call, Throwable t) {


                }
            });

        }

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(set.getText()) || TextUtils.isEmpty(rest_time.getText()) ||
                        TextUtils.isEmpty(remark.getText()) || TextUtils.isEmpty(rir.getText()) || TextUtils.isEmpty(rpe.getText()) || TextUtils.isEmpty(weight.getText())) {
                    Toast.makeText(getApplicationContext(), "Please Fill in All The Field", Toast.LENGTH_SHORT).show();
                } else {
                    if (lbs_btn.isChecked()) {
                        postData(name.getText().toString(),
                                Double.parseDouble(set.getText().toString()),
                                Double.parseDouble(weight.getText().toString()) / 2.2,
                                rest_time.getText().toString(),
                                Double.parseDouble(rpe.getText().toString()),
                                Double.parseDouble(rir.getText().toString()),
                                remark.getText().toString());
                    } else {
                        postData(name.getText().toString(),
                                Double.parseDouble(set.getText().toString()),
                                Double.parseDouble(weight.getText().toString()),
                                rest_time.getText().toString(),
                                Double.parseDouble(rpe.getText().toString()),
                                Double.parseDouble(rir.getText().toString()),
                                remark.getText().toString());
                    }

                }


            }
        });
    }

//    private void postDataTesting(String name, int set) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.179:8081/addEvent/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        // below line is to create an instance for our retrofit api class.
//        MyAPIServiceTesting retrofitAPI = retrofit.create(MyAPIServiceTesting.class);
//
//        // passing data from our text fields to our modal class.
//        DataTesting modal = new DataTesting(name, set);
//
//        // calling a method to create a post and passing our modal class.
//        Call<DataTesting> call = retrofitAPI.createData(modal);
//
//        // on below line we are executing our method.
//        call.enqueue(new Callback<DataTesting>() {
//            @Override
//            public void onResponse(Call<DataTesting> call, Response<DataTesting> response) {
//
//                Log.d("response name", response.body().getName());
//                Log.d("response set", response.body().getSet() + "");
//
//            }
//
//            @Override
//            public void onFailure(Call<DataTesting> call, Throwable t) {
//
//
//            }
//        });
//    }

    private void postData(String name, double set, double weight, String rest_time, double rpe, double rir, String remark) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:8081/addEvent/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        // passing data from our text fields to our modal class.
        Exercise modal = new Exercise(name, set, weight, rest_time, rpe, rir, remark, mDate);

        // calling a method to create a post and passing our modal class.
        Call<List<Exercise>> call = retrofitAPI.createData(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {

                Log.d("The number that I want", response.body().size() + "");
                for (int i = 0; i < response.body().size() - 1; i++) {
                    Log.d("--------------------", "----------------------");
                    Log.d("response name", response.body().get(i).getName());
                    Log.d("response set", response.body().get(i).getSet() + "");
                    Log.d("response rir", response.body().get(i).getRir() + "");
                    Log.d("response rpe", response.body().get(i).getRpe() + "");
                    Log.d("response remark", response.body().get(i).getRemark());
                    Log.d("response weight", response.body().get(i).getWeight() + "");
                }
                // Log.d("Number that we want",response.body().length);
                Intent replyIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("Finish", "true");
                replyIntent.putExtras(bundle);

                setResult(RESULT_OK, replyIntent);
                finish();


            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {


            }
        });
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:8081/addEvent/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);


        // calling a method to create a post and passing our modal class.
        Call<List<Exercise>> call = retrofitAPI.getData("111");

        // on below line we are executing our method.
        call.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {

                Log.d("The number that I want", response.body().size() + "");
                for (int i = 0; i < response.body().size() - 1; i++) {
                    Log.d("--------------------", "----------------------");
                    Log.d("response name", response.body().get(i).getName());
                    Log.d("response set", response.body().get(i).getSet() + "");
                    Log.d("response rir", response.body().get(i).getRir() + "");
                    Log.d("response rpe", response.body().get(i).getRpe() + "");
                    Log.d("response remark", response.body().get(i).getRemark());
                    Log.d("response weight", response.body().get(i).getWeight() + "");
                }
                // Log.d("Number that we want",response.body().length);


            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {


            }
        });
    }
}