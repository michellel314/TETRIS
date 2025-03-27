import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class Music {
    private Clip clip;
    public static void playSound (String filePath)  {
        File audioFile = new File(filePath);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            // Keep the program running until the sound finishes
            while (clip.isRunning()) {
                Thread.sleep(10);
            }
            clip.close();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void close() {
        clip.close();
    }
}
