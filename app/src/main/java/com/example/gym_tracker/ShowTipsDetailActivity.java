package com.example.gym_tracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShowTipsDetailActivity extends AppCompatActivity {

    private Fragment TipsDetailFragment;
    private Fragment YoutubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips_detail);
    }


    public void afterClick(int choose) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        TipsDetailFragment = new TipsDetailFragment();
        // TipsDetailFragment.setArguments(bundle);

        YoutubeFragment = new YoutubeFragment();

        if (choose == 1) {
            transaction.replace(R.id.content_fragment, YoutubeFragment, "ytView");
        } else {
            transaction.replace(R.id.content_fragment, TipsDetailFragment, "detail_fragment");
        }
        transaction.commit();
    }
}