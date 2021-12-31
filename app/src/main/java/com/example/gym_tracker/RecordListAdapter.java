package com.example.gym_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> name = new ArrayList<String>();
    Context context;
    int itemViewResID;
    String mDate;

    RecordListAdapter(Context context, int itemViewResID, List<String> name, String mDate) {
        this.context = context;
        this.itemViewResID = itemViewResID;
        this.name = name;
        this.mDate = mDate;
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt;


        // Event handling registration, page navigation goes here
        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txt = itemView.findViewById(R.id.name_two);

            this.txt.setOnClickListener((view) -> {
                Log.d("I want it", this.txt.getText().toString());
            });

            Button del_btn = itemView.findViewById(R.id.del_btn);
            Button view_edit_btn = itemView.findViewById(R.id.view_edit_btn);

            del_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // do sth
                    delRecord(getAdapterPosition());
                }
            });

            view_edit_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    goToAddEvent(getAdapterPosition());
                }
            });

        }


        public TextView getTextView() {
            return txt;
        }
        // End of ViewHolder initialization
    }


    // for creating viewHolder here
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate()to find a layout in xml
        View view = (LayoutInflater.from(context)).inflate(itemViewResID, null);
        return new EventListViewHolder(view);
    }


    // for setting the content for each element
    // set the content of the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((EventListViewHolder) holder).getTextView().setText(name.get(position));
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public void goToAddEvent(int position) {
        Intent intent = new Intent(this.context, AddRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", mDate);
        bundle.putString("Name", name.get(position));
        intent.putExtras(bundle);
        ((Activity) this.context).startActivityForResult(intent, 1);
    }

    public void delRecord(int position){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:3000/record/deleteRecord/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        // calling a method to create a post and passing our modal class.
        Call<CheckSuccess> call = retrofitAPI.delRecord(name.get(position),mDate);

        // on below line we are executing our method.
        call.enqueue(new Callback<CheckSuccess>() {
            @Override
            public void onResponse(Call<CheckSuccess> call, Response<CheckSuccess> response) {
                if (response.body().getSuccess() == true) {
                    name.remove(position);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CheckSuccess> call, Throwable t) {


            }
        });

    }

}
