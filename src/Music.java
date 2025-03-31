import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {
     Clip clip;
    URL soundURL[] = new URL[10];
    public Music(){

        soundURL[0] = getClass().getResource("/sounds/BARNEY.wav");
    }


    public  void setFile (int i)  {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);


        } catch (Exception e){
        }

    }

    public void play() {
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }

    public void close() {
        clip.close();
    }
}
