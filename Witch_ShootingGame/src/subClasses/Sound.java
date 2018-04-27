package subClasses;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound {
	
	
	URL fileName;
	int loop;
	public Clip clip = null;

	public Sound(URL fileName,int loop){
		this.fileName = fileName;
		this.loop = loop;
		init();
	}
	
	public void init(){
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void play(){
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(fileName);
			
			clip.open(ais);
			clip.loop(loop);
			clip.start();
		
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void activate(){
		play();
	}
	public void deactivate(){
		clip.close();
	}
	public void setFileName(URL fileName)
	{
		this.fileName = fileName;
	}
}
