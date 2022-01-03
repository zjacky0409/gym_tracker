package com.example.gym_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class SelectOptionFragment extends Fragment {


    RadioGroup radioGroup;
    RadioButton show_tips;
    RadioButton show_yt_video;

    public SelectOptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_select_option, container, false);
        radioGroup = rootView.findViewById(R.id.radio_group);
        show_tips = rootView.findViewById(R.id.show_tips);
        show_yt_video = rootView.findViewById(R.id.show_yt_video);

        radioGroup.clearCheck();

        ((ShowTipsDetailActivity) getActivity()).afterClick(0);
        radioGroup.check(R.id.show_tips);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                // When the user want to switch the content(yt_webview or detail)
                switch (index) {
                    case 0:
                        ((ShowTipsDetailActivity) getActivity()).afterClick(0);
                        break;
                    case 1:
                        ((ShowTipsDetailActivity) getActivity()).afterClick(1);
                        break;
                    default: // No choice made. // Do nothing.
                        break;
                }

            }
        });
        return rootView;
    }
}