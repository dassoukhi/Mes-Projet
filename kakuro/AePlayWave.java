package kakuro;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 

public class AePlayWave extends Thread {
 
    private String filename;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    enum Position {LEFT, RIGHT, NORMAL};
    SourceDataLine auline = null;
    
    public AePlayWave()
    {
    	
    }
 
    public AePlayWave(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
    
    public void setFichier(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
 
 
    public void run() {
        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }
 
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
 
        AudioFormat format = audioInputStream.getFormat();
        
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
 
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }
 
        auline.start();
        
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
    
    public void arreter()
    {
    	auline.close();
    	
    }
    
    public void playEnBoucle(Thread k, String m)
    {
    	  k = null;
          k=new AePlayWave(m);
          k.start();
          
          while(k.isAlive())
          {
        	  
          }
          playEnBoucle(k,m);
    	
    }
 
    /**
     * Exemple pour jouer le son du tambour
     */
    public static void main(String[] args) {
    	String m = "C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\instru.wav";
      Thread playWave=new AePlayWave(m);
       //playWave.start();
     
      ((AePlayWave) playWave).playEnBoucle(playWave,m);
      

       
      
    }
} 