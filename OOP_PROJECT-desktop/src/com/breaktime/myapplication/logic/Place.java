package com.breaktime.myapplication.logic;

public interface Place {
    boolean play();
    boolean pause();
    boolean setSound(int amount);
    boolean increaseSound(int amount);
    boolean decreaseSound(int amount);
}
