package com.breaktime.myapplication.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listenner implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed + " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed + " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released + " + e.getKeyCode());
    }
}
