import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.io.File;
import java.sql.SQLOutput;

public class Music {
    Clip clip;
    String soundURL;

    public Music(String e){
        // Debugging: Check if the file path is correct
        soundURL = e;

        // Debugging: Verify sound URL is not null
        if (soundURL == null) {
            System.out.println("Error: Sound file 'BARNEY.wav' not found!");
        }
    }

    public void setFile() {
        try {
            File music = new File(soundURL);

            if(music.exists()) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL));
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            } else {
                System.out.println("Can't find file");
            }

        } catch (Exception e) {
            System.out.println("Error loading sound file: " + e.getMessage());
            e.printStackTrace();
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
