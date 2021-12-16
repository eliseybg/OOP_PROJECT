package com.breaktime.myapplication.logic;

import com.breaktime.myapplication.data.Data;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class Controller extends DeviceObserver {
    private Place player;
    private boolean canCheckPulse = true;
    private boolean canPauseByPulse = true;
    private boolean canChangeSoundByPulse = true;
    private boolean canCheckDistance = true;
    private boolean canPlayByDistance = true;
    private boolean canPauseByDistance = true;
    private boolean canChangeSoundByDistance = true;
    private boolean canCheckTimeToSleep = true;
    private boolean canPauseByTimeToSleep = true;
    private boolean canCheckAlarm = true;
    private boolean canPauseByAlarm = true;

    public Controller(Place player) {
        this.player = player;
    }

    @Override
    public void onDataUpdate(Data data) {
        if (canCheckDistance)
            checkDistance(data);
        if (canCheckPulse)
            checkPulse(data);
        if (canCheckTimeToSleep)
            checkTimeToSleep(data);
        if (canCheckAlarm)
            checkAlarm(data);
    }

    private void checkPulse(Data data) {
        int temp = 5;
        int pulse = data.getPulse();
        if ((pulse > 95 || pulse < 55) && canPauseByPulse) {
            player.pause();
            alert("You have a big pules");
            return;
        }

        if (pulse <= 75 && canChangeSoundByPulse) {
            player.increaseSound(temp);
        } else {
            player.decreaseSound(temp);
        }
    }

    private void checkDistance(Data data) {
        if (data.getDistance() > 10 && canPauseByDistance) {
            player.pause();
        } else if (canChangeSoundByDistance) {
            double sound = 8 * data.getDistance() + 20;
            System.out.println(sound);
            player.setSound((int) sound);
        }
        else if (canPlayByDistance) {
            player.play();
        }
    }

    private void checkAlarm(Data data) {
        if (data.isAlarm()) {
            if (canPauseByAlarm)
                player.pause();
            alert("Check your phone");
        }
    }

    private void checkTimeToSleep(Data data) {
        Date currentTime = Calendar.getInstance().getTime();
        int hours = currentTime.getHours();
        int minutes = currentTime.getMinutes();
        int[] sleepTime = data.getTime();
        if ((hours > sleepTime[0]) || (hours == sleepTime[0] && minutes > sleepTime[1])) {
            alert("Time to sleep");
            if (canPauseByTimeToSleep)
                player.pause();
        }

    }

    private void alert(String message) {
        new Thread(() -> JOptionPane
                .showMessageDialog(null, message, null, JOptionPane.PLAIN_MESSAGE)).start();
    }

    public Place getPlayer() {
        return player;
    }

    public void setPlayer(Place player) {
        this.player = player;
    }

    public boolean isCanCheckPulse() {
        return canCheckPulse;
    }

    public void setCanCheckPulse(boolean canCheckPulse) {
        this.canCheckPulse = canCheckPulse;
    }

    public boolean isCanPauseByPulse() {
        return canPauseByPulse;
    }

    public void setCanPauseByPulse(boolean canPauseByPulse) {
        this.canPauseByPulse = canPauseByPulse;
    }

    public boolean isCanChangeSoundByPulse() {
        return canChangeSoundByPulse;
    }

    public void setCanChangeSoundByPulse(boolean canChangeSoundByPulse) {
        this.canChangeSoundByPulse = canChangeSoundByPulse;
    }

    public boolean isCanCheckDistance() {
        return canCheckDistance;
    }

    public void setCanCheckDistance(boolean canCheckDistance) {
        this.canCheckDistance = canCheckDistance;
    }

    public boolean isCanPauseByDistance() {
        return canPauseByDistance;
    }

    public void setCanPauseByDistance(boolean canPauseByDistance) {
        this.canPauseByDistance = canPauseByDistance;
    }

    public boolean isCanPlayByDistance() {
        return canPlayByDistance;
    }

    public void setCanPlayByDistance(boolean canPlayByDistance) {
        this.canPlayByDistance = canPlayByDistance;
    }

    public boolean isCanChangeSoundByDistance() {
        return canChangeSoundByDistance;
    }

    public void setCanChangeSoundByDistance(boolean canChangeSoundByDistance) {
        this.canChangeSoundByDistance = canChangeSoundByDistance;
    }

    public boolean isCanCheckTimeToSleep() {
        return canCheckTimeToSleep;
    }

    public void setCanCheckTimeToSleep(boolean canCheckTimeToSleep) {
        this.canCheckTimeToSleep = canCheckTimeToSleep;
    }

    public boolean isCanPauseByTimeToSleep() {
        return canPauseByTimeToSleep;
    }

    public void setCanPauseByTimeToSleep(boolean canPauseByTimeToSleep) {
        this.canPauseByTimeToSleep = canPauseByTimeToSleep;
    }

    public boolean isCanCheckAlarm() {
        return canCheckAlarm;
    }

    public void setCanCheckAlarm(boolean canCheckAlarm) {
        this.canCheckAlarm = canCheckAlarm;
    }

    public boolean isCanPauseByAlarm() {
        return canPauseByAlarm;
    }

    public void setCanPauseByAlarm(boolean canPauseByAlarm) {
        this.canPauseByAlarm = canPauseByAlarm;
    }
}
