package com.breaktime.myapplication.logic;

import com.breaktime.myapplication.data.Data;
import com.breaktime.myapplication.run.Main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroidDevice extends Device {
    private ServerSocket server;
    private volatile Socket phone;

    public AndroidDevice() {
        try {
            server = new ServerSocket(Main.port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("waiting");
                    phone = server.accept();
                    System.out.println("connected");
                    ObjectInputStream in = new ObjectInputStream(phone.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(phone.getOutputStream());
                    while (true) {
                         System.out.println("waiting data");
                        Data data = (Data) in.readObject();
                        System.out.println("got data");
                        notifyObserver(data);
                    }
                } catch (IOException | ClassNotFoundException | NullPointerException e) {
                }
            }
        }).start();
    }

    @Override
    protected void notifyObserver(Data data) {
        for (DeviceObserver controller : controllers) {
            controller.onDataUpdate(data);
        }
    }
}
