    package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    // RENDER TIME DEBUG KEY
    boolean checkDrawTime = false;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // Associated KeyCodes
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }         
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }         
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ENTER) {
            if (gp.gameState == gp.playState) {

                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                
                gp.gameState = gp.playState;
                
            }
        }


        // RENDER TIME DEBUG KEY
        if(code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } 
            else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        // Associated KeyCodes
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }         
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }         
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        } 
    }

}
