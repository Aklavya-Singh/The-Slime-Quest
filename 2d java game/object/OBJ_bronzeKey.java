package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_bronzeKey extends SuperObject {

    public OBJ_bronzeKey(GamePanel gp) {
        name = "Bronze Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key_bronze.png"));

            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        collision = true; // this object will be collidable with the player.
    }
}
