package com.example.gym_tracker;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String[] imgUrl, name;
    Context context;
    int itemViewResID;

    EventListAdapter(Context context, int itemViewResID, String[] imgUrl, String[] name ) {
        this.context = context;
        this.itemViewResID = itemViewResID;
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public static class EventListViewHolder extends RecyclerView.ViewHolder {
        private TextView txt;




        // Event handling registration, page navigation goes here
        public EventListViewHolder(@NonNull View itemView){
            super(itemView);
            this.txt = (TextView) itemView.findViewById(R.id.itemText);

            this.txt.setOnClickListener((view)->{
                Log.d("I want it", this.txt.getText().toString());
            });
        }





        public TextView getTextView() { return txt; }
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
        ((EventListViewHolder)holder).getTextView().setText(name[position]);
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return imgUrl.length;
    }

}
