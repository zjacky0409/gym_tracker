package com.example.gym_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;


public class YoutubeFragment extends Fragment {


    private String url;

    public YoutubeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Log.d("ERROR", String.valueOf(getArguments()));
//        try {
//            destX = getArguments().getFloat("destX");
//            destY = getArguments().getFloat("destY");
//            foodName = getArguments().getString("foodName");
//            locationName = getArguments().getString("locationName");
//        } catch (Exception e) {
//            destX = 0;
//            destY = 0;
//        }
        WebView myWebView = getActivity().findViewById(R.id.yt);
        myWebView.setWebViewClient(new WebViewController());
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.loadUrl("https://www.youtube.com/watch?v=qlvE_owkBwI&list=RDSX_ViT4Ra7k&index=4");

    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}