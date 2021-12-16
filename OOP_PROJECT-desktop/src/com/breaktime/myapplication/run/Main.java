package com.breaktime.myapplication.run;

import com.breaktime.myapplication.logic.*;

public class Main {
    private static Place place;
    private static Device device;
    private static Controller controller;

    public static int port = 3030;

    public static void main(String[] args) {
        device = new AndroidDevice();
        place = new AppleDesktop();
        controller = new Controller(place);
        controller.setPlayer(place);
        device.attach(controller);
    }

}
