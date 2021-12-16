package com.breaktime.myapplication.logic;

import com.breaktime.myapplication.data.Data;

public abstract class DeviceObserver {
    public abstract void onDataUpdate(Data data);
}
