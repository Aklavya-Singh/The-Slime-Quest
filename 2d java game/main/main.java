package main;

import javax.swing.JFrame;

public class main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this lets the window properly close on click "X" button.
        window.setResizable(false); //prevents the resizing of the window.
        window.setTitle("Slime Gayme"); //title of the window screen.

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //causes this window to be sized to fit the preferred size and layouts of its subcomponents (i.e. GamePanel).

        window.setLocationRelativeTo(null); //null = default so the window will be in the center.
        window.setVisible(true);

        gamePanel.setupGame(); //this is used to set the objects in the game.
        gamePanel.startGameThread();
    }
}
