package com.example.gym_tracker;

public class Exercise {
    private String name;
    private double set;
    private double weight;
    private String rest_time;
    private double rpe;
    private double rir;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Exercise(String name, double set, double weight, String rest_time, double rpe, double rir,String remark) {
        this.name = name;
        this.set = set;
        this.weight = weight;
        this.rest_time = rest_time;
        this.rpe = rpe;
        this.rir = rir;
        this.remark = remark;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSet() {
        return set;
    }

    public void setSet(double set) {
        this.set = set;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }


    public double getRir() {
        return rir;
    }

    public void setRir(double rir) {
        this.rir = rir;
    }

    public double getRpe() {
        return rpe;
    }

    public void setRpe(double rpe) {
        this.rpe = rpe;
    }

}
