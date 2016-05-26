package com.demosoft.testgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */
public class KeyListenerWrapper implements KeyListener {
    private KeyListener currentKeyListener;

    public KeyListenerWrapper(KeyListener currentKeyListener) {
        this.currentKeyListener = currentKeyListener;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentKeyListener.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentKeyListener.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentKeyListener.keyPressed(e);
    }

    public KeyListener getCurrentKeyListener() {
        return currentKeyListener;
    }

    public void setCurrentKeyListener(KeyListener currentKeyListener) {
        this.currentKeyListener = currentKeyListener;
    }
}
