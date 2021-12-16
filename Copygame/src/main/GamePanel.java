package main;

import javax.swing.*;

import entity.Player;
import entity.Weapon;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTitleSize = 32;
    public final int scale = 2;

    public final int titleSize = originalTitleSize * scale;
    public final int maxScreenWidth = 16;
    public final int maxScreenHeight = 12;
    public final int screenWidth = 768;
    public final int screenHeight = 576; // 768*576 px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 8;

    int mouth = 300;
    public int step;

    int FPS;
    public long timer = 0;
    public long currentTime;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(133, 58, 4));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        start();
    }

    public void start() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            System.out.println(currentTime);
            if (delta > 0) {
                Update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 900000000) {
                step = 5;
            } else if (timer >= 800000000) {
                step = 4;
            } else if (timer >= 700000000) {
                step = 3;
            } else if (timer >= 600000000) {
                step = 2;
            } else if (timer >= 500000000) {
                step = 1;
            } else if (timer >= 400000000) {
                step = 5;
            } else if (timer >= 300000000) {
                step = 4;
            } else if (timer >= 200000000) {
                step = 3;
            } else if (timer >= 100000000) {
                step = 2;
            } else
                step = 1;

            if (timer >= 1000000000) {
                FPS = drawCount;
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void Update() {
        player.update();
        

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2, step);
        
        g2.setFont(new Font("Courier", Font.PLAIN,13));
        g2.setColor(Color.blue);
        g2.drawString("FPS: "+FPS,10,10);
        g2.dispose();

    }

}
