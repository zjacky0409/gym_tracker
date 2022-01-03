package com.example.gym_tracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShowTipsDetailActivity extends AppCompatActivity {

    private Fragment TipsDetailFragment;
    private Fragment YoutubeFragment;
    private String name;
    private String details;
    private String yt_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips_detail);

        // Get the bundle data
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name = extras.getString("name");
        details = extras.getString("details");
        yt_link = extras.getString("yt_link");
    }

    // Switching the fragment when the user want to switch the content
    public void afterClick(int choose) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        TipsDetailFragment = new TipsDetailFragment();
        Bundle bundleToSelect = new Bundle();
        bundleToSelect.putString("name", name);
        bundleToSelect.putString("yt_link", yt_link);
        bundleToSelect.putString("details", details);
        TipsDetailFragment.setArguments(bundleToSelect);

        YoutubeFragment = new YoutubeFragment();
        YoutubeFragment.setArguments(bundleToSelect);


        if (choose == 1) {
            transaction.replace(R.id.content_fragment, YoutubeFragment, "ytView");
        } else {
            transaction.replace(R.id.content_fragment, TipsDetailFragment, "detail_fragment");
        }
        transaction.commit();
    }
}