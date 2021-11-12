package com.example.gym_tracker;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TipsDetailFragment extends Fragment {


    String details;

    public TipsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Why we put get bundle here
    // because we want to make sure that bundle has been created
    // if we put it on onCreate, it may not been created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_tips_detail, container, false);

        try {
            details = getArguments().getString("details");
            Log.d("yt_link", details);

        } catch (Exception e) {
            details = "<br>details<br>";

        }

        TextView shownHints = rootView.findViewById(R.id.hints_preview);
        shownHints.setText(Html.fromHtml(details));
        return rootView;
    }
}