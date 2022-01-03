package com.example.gym_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;


public class YoutubeFragment extends Fragment {


    private String url; // the yt_link

    public YoutubeFragment() {
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
        return inflater.inflate(R.layout.fragment_youtube, container, false);
    }


    // Set the yt_link for the webView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            // get the bundle data(yt_link) here
            url = getArguments().getString("yt_link");
        } catch (Exception e) {
            url = "https://www.youtube.com/watch?v=qlvE_owkBwI&list=RDSX_ViT4Ra7k&index=4";
        }
        WebView myWebView = getActivity().findViewById(R.id.yt);
        myWebView.setWebViewClient(new WebViewController());
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.loadUrl(url);

    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}