package main;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new object.OBJ_bronzeChest(gp);
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 16 * gp.tileSize;

        gp.obj[1] = new object.OBJ_blueOrb(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 18 * gp.tileSize;

        gp.obj[2] = new object.OBJ_bronzeKey(gp);
        gp.obj[2].worldX = 26 * gp.tileSize;
        gp.obj[2].worldY = 18 * gp.tileSize;

    }

}
