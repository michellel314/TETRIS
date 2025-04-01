import javax.sound.sampled.*;
import java.net.URL;

public class Music {
    Clip clip;
    URL soundURL[] = new URL[10];

    public Music(){
        // Debugging: Check if the file path is correct
        soundURL[0] = getClass().getResource("/res/sounds/BARNEY.wav");

        // Debugging: Verify sound URL is not null
        if (soundURL[0] == null) {
            System.out.println("Error: Sound file 'BARNEY.wav' not found!");
        }
    }

    public void setFile(int i) {
        try {
            if (soundURL[i] == null) {
                System.out.println("Error: No sound file loaded at index " + i);
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            System.out.println("Error loading sound file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        } else {
            System.out.println("Error: Clip is not initialized.");
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Error: Clip is not initialized.");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        } else {
            System.out.println("Error: Clip is not initialized.");
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        } else {
            System.out.println("Error: Clip is not initialized.");
        }
    }
}
