package com.breaktime.oop_project.data;

import java.io.Serializable;

public class Data implements Serializable {
    private int pulse;
    private double distance;
    private int[] time;
    private boolean alarm;

    public Data(int pulse, double distance, int[] time, boolean alarm) {
        this.pulse = pulse;
        this.distance = distance;
        this.time = time;
        this.alarm = alarm;
    }

    public int getPulse() {
        return pulse;
    }

    public double getDistance() {
        return distance;
    }

    public int[] getTime() {
        return time;
    }

    public boolean isAlarm() {
        return alarm;
    }
}
