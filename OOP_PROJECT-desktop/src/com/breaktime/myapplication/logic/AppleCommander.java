package com.breaktime.myapplication.logic;

public class AppleCommander {
    private static String[] cmd = {"osascript", "-e", ""};
    private static final String CHANGE_SOUND = "set theOutput to output volume of (get volume settings)\n" +
            "set volume output volume (theOutput ${val})";
    private static final String SET_SOUND = "set volume output volume ${val}";
    private static final String PAUSE_MUSIC = "tell application \"Music\" to pause";
    private static final String PLAY_MUSIC = "tell application \"Music\" to play";
    private static final String PAUSE_PLAYER = "tell application \"QuickTime Player\"\n" +
            "\tpause document 1\n" +
            "end tell";
    private static final String PLAY_PLAYER = "tell application \"QuickTime Player\"\n" +
            "\tplay document 1\n" +
            "end tell";

    public enum STATE {CHANGE_SOUND, SET_SOUND, PAUSE_MUSIC, PLAY_MUSIC, PAUSE_PLAYER, PLAY_PLAYER}

    /**
     *  CHANGE_SOUND
     * args can be '- number' or '+ number'
     *
     *  SET_SOUND
     * args can be 'number'
     *  */
    public static String[] getCommand(STATE state, String args) {
        switch (state) {
            case CHANGE_SOUND -> {
                String val = CHANGE_SOUND.replaceAll("[$][{]val[}]", args);
                cmd[2] = val;
                return cmd;
            }
            case SET_SOUND -> {
                String val = SET_SOUND.replaceAll("[$][{]val[}]", args);
                cmd[2] = val;
                return cmd;
            }
            case PAUSE_MUSIC -> {
                cmd[2] = PAUSE_MUSIC;
                return cmd;
            }
            case PLAY_MUSIC -> {
                cmd[2] = PLAY_MUSIC;
                return cmd;
            }
            case PAUSE_PLAYER -> {
                cmd[2] = PAUSE_PLAYER;
                return cmd;
            }
            case PLAY_PLAYER -> {
                cmd[2] = PLAY_PLAYER;
                return cmd;
            }
            default -> throw new IllegalStateException("Wrong state");
        }
    }
}
