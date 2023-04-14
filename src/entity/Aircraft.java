package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Aircraft extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    public Aircraft(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getImage() {

        try {
            plane = ImageIO.read(new File("res/airplane/plane.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //Movement
        if(keyH.upPressed == true) {
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            y += speed;
        }
        if(keyH.leftPressed == true) {
            x -= speed;
        }
        else if(keyH.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = plane;
        g2.drawImage(image, x, y, 40, 40, null);
        
    }
}
