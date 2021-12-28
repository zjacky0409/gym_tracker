package com.example.gym_tracker;

public class Record {

    private String date;
    private String name;
    private String set;
    private String weight;
    private String rest_time;
    private String rpe;
    private String rir;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Record(String name, String set, String weight, String rest_time, String rpe, String rir, String remark, String Date) {
        this.name = name;
        this.set = set;
        this.weight = weight;
        this.rest_time = rest_time;
        this.rpe = rpe;
        this.rir = rir;
        this.remark = remark;
        this.date = Date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }


    public String getRir() {
        return rir;
    }

    public void setRir(String rir) {
        this.rir = rir;
    }

    public String getRpe() {
        return rpe;
    }

    public void setRpe(String rpe) {
        this.rpe = rpe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        this.date = mDate;
    }

}
