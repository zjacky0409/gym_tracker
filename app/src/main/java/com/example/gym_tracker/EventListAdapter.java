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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String[] imgUrl;
    List<String> name = new ArrayList<String>();
    Context context;
    int itemViewResID;
    String mDate;

    EventListAdapter(Context context, int itemViewResID, String[] imgUrl, List<String> name, String mDate) {
        this.context = context;
        this.itemViewResID = itemViewResID;
        this.imgUrl = imgUrl;
        this.name = name;
        this.mDate = mDate;
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt;


        // Event handling registration, page navigation goes here
        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txt = itemView.findViewById(R.id.name);

            this.txt.setOnClickListener((view) -> {
                Log.d("I want it", this.txt.getText().toString());
            });

            Button del_btn = itemView.findViewById(R.id.del_btn);
            Button view_edit_btn = itemView.findViewById(R.id.view_edit_btn);

            del_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // do sth
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
        Intent intent = new Intent(this.context, AddEventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Date", mDate);
        bundle.putString("Name", name.get(position));
        intent.putExtras(bundle);
        ((Activity) this.context).startActivityForResult(intent, 1);
    }

}
