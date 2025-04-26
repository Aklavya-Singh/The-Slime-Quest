package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // The size of the pickable objects is 48x48 pixels.
    // can be used to specify the area of the object that will be used for collision detection.
    // just need to go into the specefic object class and change the values of the rectangle to the size of the object.
    // use solidAreaX = 5; and solidAreaY = 5; for the x and y coordinates of the rectangle.

    public int solidAreaDefaultX = 0; // default x coordinate of the rectangle
    public int solidAreaDefaultY = 0; // default y coordinate of the rectangle

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

    //To create a new object for the map, make a new class in "object" package then enter it's coordinates on the map in the class "AssetSetter" (follow the format used there).
 

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                } 
    }
        
}
