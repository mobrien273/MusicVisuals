package c21502889;

//import processing.core.*;
import ie.tudublin.Visual;

public class MichaelsVisual extends Visual {
  // Class variables
  int width = 600;  //Width of triangle mesh
  int height = 600; //Height of triangle mesh
  int scale = 20; //scale for the mesh

  // Mesh rows and columns
  int cols = width / scale;
  int rows = height / scale;

  
  public void settings() {
    size(600, 600, P3D);

    // Use this to make fullscreen
    // fullScreen();

    // Use this to make fullscreen and use P3D for 3D graphics
    // fullScreen(P3D, SPAN);

  }// End settings

  public void setup() {
    startMinim();
    background(0);
    stroke(100, 149, 237); // Set the stroke colour to blue
    noFill();

    // Call loadAudio to load an audio file to process
    loadAudio("EverythingInItsRightPlace.mp3");

  }// End setup

  public void keyPressed() {
    if (key == ' ') {
      getAudioPlayer().cue(0);
      getAudioPlayer().play();
    }

  }// End keyPressed

  public void draw() {

    //Draw terrain relative to the centre of the window
    translate(width/2, height/2);

    rotateX(PI/3); // rotate view so that there is a bird's eye view of the terrain

    translate(- width/2, - height/2); // translate again to mirror grid on left side of screen

    // Draw mesh with triangle strips
    for (int y = 0; y < rows; y++)
    {
      beginShape(TRIANGLE_STRIP);

      for (int x = 0; x < cols; x++)
      {
        vertex (x * scale, y * scale);
        vertex(x * scale, (y + 1) * scale);
      }
      endShape();
    }
   
  }

}// End MichaelsVisual