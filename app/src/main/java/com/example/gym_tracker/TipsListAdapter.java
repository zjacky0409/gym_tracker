package com.example.gym_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TipsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Tips> name = new ArrayList<Tips>();
    Context context;
    int itemViewResID;

    public TipsListAdapter(Context context, int itemViewResID, List<Tips> name) {
        this.name = name;
        this.context = context;
        this.itemViewResID = itemViewResID;
    }

    public class TipsListViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        Button del;
        Button edit;

        // Event handling registration, page navigation goes here
        public TipsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txt = itemView.findViewById(R.id.name_two);
            this.edit = itemView.findViewById(R.id.edit_tip);
            this.del = itemView.findViewById(R.id.delete_tip);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToTipsDetailActivity(view, getPosition());
                }
            });
            this.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToAddTipsActivity(view, getPosition());
                }
            });

            this.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.0.179:3000/tips/delete/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

                    Call<CheckSuccess> call = retrofitAPI.delTips(name.get(getPosition()).getName());

                    call.enqueue(new Callback<CheckSuccess>() {
                        @Override
                        public void onResponse(Call<CheckSuccess> call, Response<CheckSuccess> response) {

                            if (response.body().getSuccess() == true) {
                                name.remove(getPosition());
                                notifyDataSetChanged();
                            }

                        }

                        @Override
                        public void onFailure(Call<CheckSuccess> call, Throwable t) {
                            Toast.makeText(context.getApplicationContext(), "Fail to del data to backend", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        }


        public TextView getTextView() {
            return txt;
        }
        // End of ViewHolder initialization
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate()to find a layout in xml
        View view = (LayoutInflater.from(context)).inflate(itemViewResID, null);
        return new TipsListAdapter.TipsListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TipsListAdapter.TipsListViewHolder) holder).getTextView().setText(name.get(position).getName());
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public void goToTipsDetailActivity(View view, int position) {
        Intent intent = new Intent(context, ShowTipsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name.get(position).getName());
        bundle.putString("yt_link", name.get(position).getYt_link());
        bundle.putString("details", name.get(position).getDetails());
        intent.putExtras(bundle);
        this.context.startActivity(intent);
    }

    public void goToAddTipsActivity(View view, int position) {
        Intent intent = new Intent(context, AddTipsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name.get(position).getName());
        bundle.putString("yt_link", name.get(position).getYt_link());
        bundle.putString("details", name.get(position).getDetails());
        intent.putExtras(bundle);
        ((Activity) this.context).startActivityForResult(intent, 1);
    }

//    public boolean delTip(View view, int position){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.179:3000/exercises/delete/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);
//
//        Call<CheckSuccess> call = retrofitAPI.delTips();
//
//        call.enqueue(new Callback<CheckSuccess>() {
//            @Override
//            public void onResponse(Call<CheckSuccess> call, Response<CheckSuccess> response) {
//
//                if(response.body().getSuccess() == true){
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<CheckSuccess> call, Throwable t) {
//                Toast.makeText(context.getApplicationContext(), "Fail to del data to backend", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}
