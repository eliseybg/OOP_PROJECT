package com.breaktime.myapplication.logic;

import com.breaktime.myapplication.data.Data;

import java.util.ArrayList;

public abstract class Device {
    protected ArrayList<DeviceObserver> controllers = new ArrayList<>();

    public void attach(Controller controller) {
        controllers.add(controller);
    }

    public void detach(Controller controller) {
        controllers.remove(controller);
    }

    protected abstract void notifyObserver(Data data);
}
