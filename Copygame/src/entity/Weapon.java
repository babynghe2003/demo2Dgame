package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon extends Entity {
    GamePanel gp;
    Player player;
    KeyHandler keyH;

    BufferedImage sword = getPlayerImage("/sprites/ciuCuaMai.png");
    BufferedImage cut1 = getPlayerImage("/sprites/cut-1.png"),
            cut2 = getPlayerImage("/sprites/cut-2.png"),
            cut3 = getPlayerImage("/sprites/cut-3.png"),
            cut4 = getPlayerImage("/sprites/cut-4.png");

    double rota = 30;
    boolean flip = false;

    long timers;
    long time;

    boolean attack = false;
    int attackStep = 0;

    public Weapon(GamePanel gp, Player player, KeyHandler keyH, String direction) {
        this.gp = gp;
        this.player = player;
        this.keyH = keyH;
        this.direction = direction;
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
        this.timers = gp.timer;
        this.x = player.x;
        this.y = player.y;
        this.speed = player.speed;
        this.direction = player.direction;

    }

    public void update() {
        this.x = player.x;
        this.y = player.y;
        this.direction = player.direction;
        this.timers = gp.currentTime;
        if (this.direction.charAt(1) == 'R') {
            this.rota = 15;
        }
        if (this.direction.charAt(1) == 'L') {
            this.rota = 15;
        }
        if (this.direction.charAt(1) == 'U') {
            this.rota = 105;
        }
        if (this.direction.charAt(1) == 'D') {
            this.rota = 285;
        }

        if (this.direction.charAt(0) == 'R') {
            x += 25;
            y += 16;
            this.flip = false;
        }
        if (direction.charAt(0) == 'L') {
            x -= 25;
            y += 20;
            this.flip = true;
        }

        if (keyH.attackPressed && time == 0) {
            time = gp.currentTime;
            attack = true;
        }
        if (timers - time > 70000000 * 4) {
            attack = false;
            time = 0;
        } else if (timers - time > 70000000 * 3) {
            attackStep = 4;
        } else if (timers - time > 70000000 * 2) {
            attackStep = 2;
        } else if (timers - time > 70000000 * 1) {
            attackStep = 3;
        } else if (timers - time >= 0) {
            attackStep = 1;
        }

    }

    public void draw(Graphics2D g2) {

        if (!attack || attackStep == 4) {
            BufferedImage imge = sword;
            if (!flip) {
                g2.rotate(Math.toRadians(-this.rota), this.x + 10, this.y + 32);
                g2.drawImage(imge, this.x, this.y + gp.titleSize, gp.titleSize*3/2, -gp.titleSize, null);
                g2.rotate(Math.toRadians(this.rota), this.x + 10, this.y + 32);
            } else {
                g2.rotate(Math.toRadians(+this.rota), this.x + 60, this.y + 25);
                g2.drawImage(imge, this.x + gp.titleSize, this.y + gp.titleSize, -gp.titleSize*3/2, -gp.titleSize, null);
                g2.rotate(Math.toRadians(-this.rota), this.x + 60, this.y + 25);
            }

        } else {

            BufferedImage imge = getPlayerImage(String.format("/sprites/cut-%s.png", attackStep));

            if (!flip) {
                g2.rotate(Math.toRadians(-this.rota), this.x + 10, this.y + 32);
                g2.drawImage(imge, this.x, this.y - 16, gp.titleSize * 2, gp.titleSize * 2, null);
                g2.rotate(Math.toRadians(this.rota), this.x + 10, this.y + 32);
            } else {
                g2.rotate(Math.toRadians(+this.rota), this.x + 60, this.y + 25);
                g2.drawImage(imge, this.x + gp.titleSize, this.y - 16, -gp.titleSize * 2, gp.titleSize * 2, null);
                g2.rotate(Math.toRadians(-this.rota), this.x + 60, this.y + 25);
            }
        }

    }

}
