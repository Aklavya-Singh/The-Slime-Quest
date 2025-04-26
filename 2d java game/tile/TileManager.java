package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;

@SuppressWarnings("unused")
public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][] ; // this will store all the numbers from the map txt files.

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow]; // this will store all the numbers from the map txt files.

        getTileImage();
        loadMap("/res/maps/world02.txt");
    }

    public void getTileImage() {
        // Check if the images are being loaded properly.
        //System.out.println("Image loading started"); 

            // CAVE FLOOR
            setup(0, "tile_cavefloor", false);

            // CAVE WALL
            setup(1, "tile_walls", true);

            // GRASS
            setup(2, "tile_grass", false);

            // GRASS WITH FLOWER
            setup(3, "tile_flowergrass", false);

            // DIRT
            setup(4, "tile_dirt", false);

            // SAND
            setup(5, "tile_sand", false);

            // TREE
            setup(6, "tile_tree", true);

            // WATER
            setup(7, "tile_water", true);


        // To check if the images are being loaded properly.
        //System.out.println("Image loading finished");
    
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool =  new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

            
        } catch (Exception e) { 
            e.printStackTrace();

        }
    }
    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine(); // read a line of text from the txt file.

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); // splits the string at a space.

                    int num = Integer.parseInt(numbers[col]); // convertig the "string" into number values and using col as an index for numbers[] array.

                    mapTileNum[col] [row] = num; // then we store the extracted and converted numbers in the mapTileNum[][] array
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;

                } // This loop continues until every value in the numbers[] is stored in the mapTileNum[][]
            }
            br.close();
        } catch (Exception e) {
        }

    }


    public void draw(Graphics2D g2) {
    
        //g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                } 
            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            worldCol++;


            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
