package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_blueOrb extends SuperObject {

    public OBJ_blueOrb(GamePanel gp) {
        name = "Blue Orb";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/drop_blueorb.png"));

            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true; // this object will be collidable with the player.
    }
}