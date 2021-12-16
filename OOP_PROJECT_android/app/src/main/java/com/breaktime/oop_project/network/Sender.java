package com.breaktime.oop_project.network;


import com.breaktime.oop_project.data.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sender {
    private static Sender users;
    private String subnet;
    private volatile Map<String, ObjectOutputStream> connections = new HashMap<>();
    private ScheduledExecutorService exec;

    private Sender(String subnet) {
        this.subnet = subnet;
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> new SearchUser().start(), 0, 3, TimeUnit.SECONDS);
    }

    public void send(Data data) {
        System.out.println("sending");
        new Thread(() -> connections.forEach((k, v) -> {
            try {
                v.writeObject(data);
                System.out.println("sent");
            } catch (IOException e) {
                e.printStackTrace();
            }
        })).start();
    }

    public static Sender getInstance(String subnet) {
        if (users == null)
            users = new Sender(subnet);
        return users;
    }

    private class SearchUser extends Thread {
        @Override
        public void run() {
            System.out.println(connections);

            for (int i = 1; i < 255; i++) {
                String host = subnet + i;
                if (!connections.containsKey(host))
                    new SearchUsersInLan(host);
            }
            System.gc();
        }
    }

    private class SearchUsersInLan extends Thread {
        private final String ip;

        SearchUsersInLan(String ip) {
            this.ip = ip;
            start();
        }

        @Override
        public void run() {
            Socket socket = null;
            ObjectOutputStream out = null;
            ObjectInputStream in = null;
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(ip, 3030), 2000);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                connections.put(ip, out);
                in.readInt();
            } catch (IOException ignored) {
                connections.remove(ip);
            }
        }
    }
}
