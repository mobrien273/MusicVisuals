package c21502889;

import processing.core.*;
import ie.tudublin.Visual;

public class MichaelsVisual extends Visual 
{
  public void settings() 
  {
    size(1024, 500);

    // Use this to make fullscreen
    // fullScreen();

    // Use this to make fullscreen and use P3D for 3D graphics
    // fullScreen(P3D, SPAN);
  
  }//End settings

  public void setup() 
  {
    startMinim();

    // Call loadAudio to load an audio file to process
    // loadAudio("heroplanet.mp3");

  }//End setup

  public void keyPressed() 
  {
    if (key == ' ') 
    {
      getAudioPlayer().cue(0);
      getAudioPlayer().play();
    }
  
  }//End keyPressed

  public void draw ()
  {
    
  }

}// End MichaelsVisual