package sounder;

import main.Manager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by ppooi on 2017-04-03.
 */
public class Sounder {
    Manager manager;


    URL fileName;
    Clip clip;
    AudioInputStream ais;

    public Sounder(Manager manager){
        this.manager = manager;
        init_Locals();

    }
    public void init_Locals() {

        fileName = getClass().getClassLoader().getResource("siren.wav");

        try {

             ais  = AudioSystem.getAudioInputStream(fileName);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void startSound(){
        try
        {
            clip.loop(4);
            clip.setMicrosecondPosition(0);
            clip.start();


        }
        catch(Exception e)
        {

        }

    }

    public void stopSound(){
        clip.stop();
    }


    public boolean isRunning(){
        return clip.isRunning();

    }

    public void mute(){
        FloatControl gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        double gain = 0;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB); //
    }
    public void demute(){
        FloatControl gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        double gain = 1;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB); //
    }
}
