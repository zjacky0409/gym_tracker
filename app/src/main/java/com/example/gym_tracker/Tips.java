package com.example.gym_tracker;

public class Tips {
    private String name;
    private String yt_link;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYt_link() {
        return yt_link;
    }

    public void setYt_link(String yt_link) {
        this.yt_link = yt_link;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Tips(String name, String yt_link, String details) {
        this.name = name;
        this.yt_link = yt_link;
        this.details = details;
    }
}
