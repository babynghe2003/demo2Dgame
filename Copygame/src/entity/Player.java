package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    BufferedImage image = null;
    Weapon weapon;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        weapon = new Weapon(gp, this, keyH, direction);
        setDefaultValues();
    }

    public BufferedImage getPlayerImage(String Path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(Path));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("loi file anh");
        }
        return null;
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 6;

    }

    public void update() {
        if (x >= 0 && y >= 0 && x <= gp.screenWidth - gp.titleSize && y <= gp.screenHeight - gp.titleSize) {
            if (keyH.upPressed && keyH.leftPressed && y > 0 && x > 0) {
                x -= speed * 3 / 4;
                y -= speed * 3 / 4;

                if (weapon.time == 0) {
                    direction = "LU";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveLeft-%s.png", gp.step));
                }
            } else if (keyH.upPressed && keyH.rightPressed && y > 0 && x < gp.screenWidth - gp.titleSize) {
                x += speed * 3 / 4;
                y -= speed * 3 / 4;

                if (weapon.time == 0) {
                    direction = "RU";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveRight-%s.png", gp.step));
                }
            } else if (keyH.downPressed && keyH.leftPressed && y < gp.screenHeight - gp.titleSize && x > 0) {
                x -= speed * 3 / 4;
                y += speed * 3 / 4;

                if (weapon.time == 0) {
                    direction = "LD";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveLeft-%s.png", gp.step));
                }
            } else if (keyH.downPressed && keyH.rightPressed && y < gp.screenHeight - gp.titleSize
                    && x < gp.screenWidth - gp.titleSize) {
                x += speed * 3 / 4;
                y += speed * 3 / 4;

                if (weapon.time == 0) {
                    direction = "RD";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveRight-%s.png", gp.step));
                }
            } else if (keyH.upPressed && y > 0) {
                y -= speed;

                if (weapon.time == 0) {
                    if (direction.charAt(0) == 'L') {
                        image = getPlayerImage(String.format("/sprites/AssasinMoveLeft-%s.png", gp.step));
                        direction = "LU";
                    } else {
                        image = getPlayerImage(String.format("/sprites/AssasinMoveRight-%s.png", gp.step));
                        direction = "RU";
                    }
                }
            } else if (keyH.downPressed && y < gp.screenHeight - gp.titleSize) {
                y += speed;

                if (weapon.time == 0) {
                    if (direction.charAt(0) == 'L') {
                        image = getPlayerImage(String.format("/sprites/AssasinMoveLeft-%s.png", gp.step));
                        direction = "LD";
                    } else {
                        image = getPlayerImage(String.format("/sprites/AssasinMoveRight-%s.png", gp.step));
                        direction = "RD";
                    }
                }
            } else if (keyH.leftPressed && x > 0) {
                x -= speed;

                if (weapon.time == 0) {
                    direction = "LL";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveLeft-%s.png", gp.step));
                }
            } else if (keyH.rightPressed && x < gp.screenWidth - gp.titleSize) {
                x += speed;

                if (weapon.time == 0) {
                    direction = "RR";
                    image = getPlayerImage(String.format("/sprites/AssasinMoveRight-%s.png", gp.step));
                }
            } else {
                if (direction.charAt(0) == 'L') {
                    image = getPlayerImage(String.format("/sprites/AssasinLeft-%s.png", gp.step));
                } else {
                    image = getPlayerImage(String.format("/sprites/AssasinRight-%s.png", gp.step));
                }
            }
        } else {
            if (x < 0)
                x = 0;
            else if (x > gp.screenWidth - gp.titleSize)
                x = gp.screenWidth - gp.titleSize;
            if (y < 0)
                y = 0;
            else if (y > gp.screenHeight - gp.titleSize)
                y = gp.screenHeight - gp.titleSize;
        }
        weapon.update();

    }

    public void draw(Graphics2D g2, int step) {
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
        weapon.draw(g2);
    }

}
