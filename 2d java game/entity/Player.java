package entity;

import main.KeyHandler;
import main.UtilityTool;
import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

@SuppressWarnings("unused")
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasBlueOrb = 0; // this is the index of the orbs that the player has picked up.
    int hasBronzeKey = 0; // this is the index of the keys that the player has picked up.

    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24; 
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues() {
    // Set player's default postion.

        worldX = gp.tileSize * 23; //starting position
        worldY = gp.tileSize * 21;
        speed = 4;// this is basically the amount of pixels the player will move if a key is pressed. i.e. 4 pixels here.
        direction = "down";
    }
    public void getPlayerImage() {

        up = setup("player_up");
        up2 = setup("player_up2");
        down = setup("player_down");
        down2 = setup("player_down2");
        left = setup("player_left");
        left2 = setup("player_left2");
        right = setup("player_right");
        right2 = setup("player_right2");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update() {
        // X value increases as you go to the right and decreases as you go to the left.
        // Y value increases as you go down adn decreases as you go up.

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed ) {
            
            if(keyH.upPressed ) {
                direction = "up";
                //worldY -= speed;

                //worldY = worldY - speed;
                // this means that if 'W' is pressed on the keyboard, the player's worldY pos will subtract the value of the speed (which is set to 4)
                // example; the default position is set to Y = 100, X = 100 that means due to this specific if statement the position of the player will be Y(100) - speed(4) i.e 96!
            }
            else if(keyH.downPressed ) {
                direction = "down";
                //worldY += speed;
            }
            else if(keyH.leftPressed ) {
                direction = "left";
                //worldX -= speed;
            }
            else if(keyH.rightPressed ) {
                direction = "right";
                //worldX += speed;
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;

            if(standCounter == 20) {
                spriteNum = 1; // For idle sprite
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {

            String objectName = gp.obj[i].name;
            switch(objectName) {
                case "Blue Orb":
                    gp.playSE(1);
                    gp.obj[i] = null; // remove the object from the map.
                    break;
                case "Bronze Chest":
                    if(hasBronzeKey > 0) {
                        gp.obj[i] = null; // remove the object from the map.
                        hasBronzeKey--;
                        System.out.println("Opened a bronze chest! Total keys: " + hasBronzeKey);
                    }
                    break;
                case "Bronze Key":
                    hasBronzeKey++;
                    gp.obj[i] = null; // remove the object from the map.
                    System.out.println("Picked up a bronze key! Total keys: " + hasBronzeKey);
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
        case "up":
            if (spriteNum == 1) {
                image = up;
            }
            if (spriteNum == 2) {
                image = up2;
            }
            break;
        case "down":
            if (spriteNum == 1) {
                image = down;
            }
            if (spriteNum == 2) {
                image = down2;
            }
            break;
        case "left":
            if (spriteNum == 1) {
                image = left;
            }
            if (spriteNum == 2) {
                image = left2;
            }
            break;
        case "right":
            if (spriteNum == 1) {
                image = right;
            }
            if (spriteNum == 2) {
                image = right2;
            }
            break;

        }
        g2.drawImage(image, screenX, screenY, null); // draw image on the screen.
    }
}