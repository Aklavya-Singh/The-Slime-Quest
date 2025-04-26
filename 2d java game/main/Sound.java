package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    // HOW TO ADD MUSIC AND SOUND EFFECTS:-

    // To add sound effect go to the class 'Player.pickUpObject', follow the format and add any SE to whichever object you want it in.
    
    // To change the default background music go to the class 'GamePanel.setupGame' and change the music code in "playMusic(0);". 
    // remember to add the desired music track to 'THIS.Sound' using the soundURL array.



    Clip clip;
    URL soundURL[] = new URL[30]; // This array is used to store the path of the sound files.
    
    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/Music_CreepyDungeon.wav");
        soundURL[1] = getClass().getResource("/res/sound/SE_OrbPickup.wav");

    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {

        }

    }
    public void play() {

        clip.start();

    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop() {

        clip.stop();

    }
}
