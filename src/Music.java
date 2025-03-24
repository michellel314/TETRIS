import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class Music {
    private Clip clip;
    public Music(String audioFilePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(audioFilePath)));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
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
