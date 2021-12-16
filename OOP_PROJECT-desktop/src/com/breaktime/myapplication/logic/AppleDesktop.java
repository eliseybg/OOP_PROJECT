package com.breaktime.myapplication.logic;

import java.io.IOException;

public class AppleDesktop implements Place {
    private Runtime runtime = Runtime.getRuntime();

    @Override
    public boolean play() {
        System.out.println("play");
        try {
            String[] cmd = AppleCommander.getCommand(AppleCommander.STATE.PLAY_MUSIC, "");
            runtime.exec(cmd);
            cmd = AppleCommander.getCommand(AppleCommander.STATE.PLAY_MUSIC, "");
            runtime.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean pause() {
        System.out.println("pause");
        try {
            String[] cmd = AppleCommander.getCommand(AppleCommander.STATE.PAUSE_MUSIC, "");
            runtime.exec(cmd);
            cmd = AppleCommander.getCommand(AppleCommander.STATE.PAUSE_PLAYER, "");
            runtime.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean setSound(int amount) {
        System.out.println("set sound");
        try {
            String[] cmd = AppleCommander.getCommand(AppleCommander.STATE.SET_SOUND, "" + amount);
            runtime.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean increaseSound(int amount) {
        System.out.println("increase sound");
        try {
            String[] cmd = AppleCommander.getCommand(AppleCommander.STATE.CHANGE_SOUND, " + " + amount);
            runtime.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean decreaseSound(int amount) {
        System.out.println("decrease sound");
        try {
            String[] cmd = AppleCommander.getCommand(AppleCommander.STATE.CHANGE_SOUND, " - " + amount);
            runtime.exec(cmd);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
