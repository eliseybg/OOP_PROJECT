package com.breaktime.oop_project.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.breaktime.oop_project.R;
import com.breaktime.oop_project.data.Data;
import com.breaktime.oop_project.network.Sender;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private Sender users;
    private EditText et_pulse;
    private EditText et_distance;
    private TextView et_time;
    private Switch sw_alarm;
    private int hours;
    private int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_pulse = findViewById(R.id.pulse);
        et_distance = findViewById(R.id.distance);
        sw_alarm = findViewById(R.id.alarm);
        et_time = findViewById(R.id.time);
        Date currentTime = Calendar.getInstance().getTime();
        hours = currentTime.getHours();
        minutes = currentTime.getMinutes();
        et_time.setText(hours + ":" + minutes);
        String address = getIPAddress(true);
        int end = address.lastIndexOf('.');
        users = Sender.getInstance(address.substring(0, end + 1));
    }

    private static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%');
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    public void send(View view) {
        String pulseStr = et_pulse.getText().toString();
        int pulse = Integer.parseInt(pulseStr);
        String distanceStr = et_distance.getText().toString();
        double distance = Double.parseDouble(distanceStr);
        int[] time = {hours, minutes};
        boolean alarm = sw_alarm.isChecked();
        users.send(new Data(pulse, distance, time, alarm));
    }

    public void setTime(View view) {
        new TimePickerDialog(this, (TimePickerDialog.OnTimeSetListener) (view1, hour, minute) -> {
            hours = hour;
            minutes = minute;
            et_time.setText(hours + ":" + minutes);
        }, hours, minutes, true).show();
    }
}