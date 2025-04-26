package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// This class will be common for every entity in game such as player, monsters, etc.

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up, up2, down, down2, left, left2, right, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea; // Using for collision detection and can store data such aas x, y, width, height.
    public int solidAreaDefaultX, solidAreaDefaultY;
    
    public boolean collisionOn = false;

}
