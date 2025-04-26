package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ //extends keyword means that the user-created class 'GamePanel' is an added extension to the class 'JPanel'.

    //SCREEN RESOLUTION/ SCREEN SETTINGS

    final int orignalTileSize = 16; //16x16 tiles
    final int scale = 3; //we will be upscaling the orignal tile size which is too small times 3
    public final int tileSize = orignalTileSize * scale; //48x48 tiles

    public final int maxScreenCol = 16;
    public final int maxScreenrow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pizels
    public final int screenHeight = tileSize * maxScreenrow; //576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();


    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this); //this is used to set the objects in the game.

    UI ui = new UI(this);

    // ENTITY AND  OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //array of objects. 10 is the max number of objects we can have in the game.

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;



    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set then size of this class(JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //basically helps improve game's rendering performance.
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject(); //this is used to set the objects in the game.

        playMusic(0);        
        gameState = playState;
        
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run() {

        // DELTA/ ACCUMULATOR GAME LOOP.
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                // 1. UPDATE - update information such as character position
            update();

            // 2. DRAW - draw the screen with the updated information
            repaint();

            delta--;
            }

            
        }
    }

    public void update() {

        if (gameState == playState) {
            
            player.update();

        }
        if (gameState == pauseState) {

        }

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;


        // RENDER TIME DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        drawStart = System.nanoTime();


        //TILE
        tileM.draw(g2); // tileM.draw before player.draw because we always want to draw tiles first before the pplayer. 
        
        //OBJECT
        for(int i = 0; i < obj.length; i++) {

            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        // RENDER TIME DEBUG
        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            System.out.println(passed);

        }
        g2.dispose();
    }
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {

        music.stop();

    }
    public void playSE(int i) {

        soundEffect.setFile(i);
        soundEffect.play();
    }
}
