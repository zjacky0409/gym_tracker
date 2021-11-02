package com.example.gym_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TipsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> name = new ArrayList<String>();
    Context context;
    int itemViewResID;

    public TipsListAdapter(Context context, int itemViewResID, List<String> name) {
        this.name = name;
        this.context = context;
        this.itemViewResID = itemViewResID;
    }

    public class TipsListViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt;


        // Event handling registration, page navigation goes here
        public TipsListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txt = itemView.findViewById(R.id.name);
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
        ((TipsListAdapter.TipsListViewHolder) holder).getTextView().setText(name.get(position));
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

}
