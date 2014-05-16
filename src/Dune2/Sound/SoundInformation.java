package Dune2.Sound;
import Dune2.Model.Constants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;

import java.net.URL;

public class SoundInformation
{  private Sound music;
   private Sound cannonShell;
   private Sound machineGun;
   private Sound machineGunHit;
   private Sound lightRocket;
   private Sound generalExplosion;
   private Sound smallExplosion;
   private Sound buildingPlaced;
   private Sound cannotPlace;
   private Sound unitUnderAttack;
   private Sound buildingUnderAttack;


   public SoundInformation()
   {  this.loadSounds();
   }

   public void loadSounds()
   {
      try {
		  this.music = new Sound("file:" + Constants.SOUND_PATH + "dune2.mid");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      
      try {
		  this.cannonShell = new Sound("file:" + Constants.SOUND_PATH + "CannonShell.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.generalExplosion = new Sound("file:" + Constants.SOUND_PATH + "GeneralExplosion.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.smallExplosion = new Sound("file:" + Constants.SOUND_PATH + "SmallExplosion.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.machineGun = new Sound("file:" + Constants.SOUND_PATH + "MachineGun.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.machineGunHit = new Sound("file:" + Constants.SOUND_PATH + "MachineGunHit.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.lightRocket = new Sound("file:" + Constants.SOUND_PATH + "LightRocket.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.buildingPlaced = new Sound("file:" + Constants.SOUND_PATH + "BuildingPlaced.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.cannotPlace = new Sound("file:" + Constants.SOUND_PATH + "CannotPlace.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
		  this.unitUnderAttack = new Sound("file:" + Constants.SOUND_PATH + "UnitUnderAttack.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
      try {
    	  this.buildingUnderAttack = new Sound("file:" + Constants.SOUND_PATH + "BaseUnderAttack.wav");
      } catch (Exception e) {
		  e.printStackTrace();
      }
   }

   public void playSound(String sound)
   {  
	   if (sound.equals(Constants.CANNON_SHELL))
      {  this.cannonShell.play();
      } else if (sound.equals(Constants.GENERAL_EXPLOSION))
      {  this.generalExplosion.play();
      } else if (sound.equals(Constants.SMALL_EXPLOSION))
      {  this.smallExplosion.play();
      } else if (sound.equals(Constants.MACHINE_GUN))
      {  this.machineGun.play();
      } else if (sound.equals(Constants.MACHINE_GUN_HIT))
      {  //this.machineGunHit.play();
         //this sound is muted cause its freakin annoying...I need a better sound
      }  else if (sound.equals(Constants.LIGHT_ROCKET))
      {  this.lightRocket.play();
      }else if (sound.equals(Constants.BUILDING_PLACED))
      {  this.buildingPlaced.play();
      } else if (sound.equals(Constants.CANNOT_PLACE))
      {  this.cannotPlace.play();
      } else if (sound.equals(Constants.UNIT_UNDER_ATTACK))
      {  this.unitUnderAttack.play();
      } else if (sound.equals(Constants.BUILDING_UNDER_ATTACK))
      {  this.buildingUnderAttack.play();
      }
   }

   public void playBackgroundMusic()
   {  this.music.loop();
      /* I must compile all of the "dune#.midi" sound files into one LONG midi.
         Once this is done, I will play the long midi as 'background music'.
         Whenever combat occurs, I play stop the background music and play whatever
         battle music I have.  After combat, stop battle music and begin the
         background music once again.  Always loop the music.
      */
   }

   public void stopBackgroundMusic()
   {  this.music.stop();
   }
   
   class Sound
   {
	   private Clip clip;
	   
	   Sound(String clipName) throws Exception
	   {
	        URL url = new URL(clipName);
	        AudioInputStream ais = AudioSystem.getAudioInputStream(url);
	        AudioFormat format = ais.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(ais);
	    }

	    public void play()
	    {
			try {
		        clip.setFramePosition(0);
		        clip.loop(0);
		        clip.start();
		    } catch (Exception e) {
				System.err.println("Exception occured.");
				e.printStackTrace();
			}
	    }
	    
	    public void loop()
	    {
	        try {
		        clip.setFramePosition(0);
		        clip.loop(1);
		        clip.start();
		    } catch (Exception e) {
				System.err.println("Exception occured.");
				e.printStackTrace();
			}
	    }
	    
	    public void stop()
	    {
			try {
				clip.stop();
			} catch (Exception e) {
				System.err.println("Exception occured.");
				e.printStackTrace();
			}
		}
   }
}
