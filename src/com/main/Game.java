package com.main;

import java.io.IOException;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final int FPS_SET = 120;
    private Thread gameThread;

    public Game() throws IOException {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        long now = System.nanoTime();

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }

    }
}
