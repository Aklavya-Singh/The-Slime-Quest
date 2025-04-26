package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_bronzeChest extends SuperObject {

    public OBJ_bronzeChest(GamePanel gp) {
        name = "Bronze Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest_bronze.png"));

            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        collision = true; // this object will be collidable with the player.
    }
}