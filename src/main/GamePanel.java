package main;

import java.awt.*;

import javax.swing.JPanel;

import entity.Aircraft;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int screenWidth = 1280;
    final int screenHeight = 720;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Aircraft aircraft = new Aircraft(this, this.keyH);

    //FPS
    private int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        } 
    } 
    

    public void update() {
        aircraft.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        aircraft.draw(g2);

        g2.dispose();
    }
}