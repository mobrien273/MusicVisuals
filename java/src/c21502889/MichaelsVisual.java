package c21502889;

//import processing.core.*;
import ie.tudublin.Visual;

public class MichaelsVisual extends Visual {
  int scale; // scale of terrain mesh
  float[][] terrain; // create 2d array for terrain

  // Mesh rows and columns
  int cols; // = width / scale;
  int rows; // = height / scale;

  public void settings() {
    size(900, 900, P3D);

    // fullScreen(P3D, 1);

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

    // Dynamically calculate scale
    float aspectRatio = (float) width / height;
    scale = max(width, height) / max(ceil(aspectRatio * 40), 40);

    cols = width / scale + 50; // Number of columns based on the window width (added 50 to avoid gaps)
    rows = height / scale; // Number of rows based on the window height

    terrainGen(); // Generate terrain

    // Call loadAudio to load an audio file to process
    loadAudio("EverythingInItsRightPlace.mp3");

  }// End setup

  public void keyPressed() {
    if (key == ' ') {
      getAudioPlayer().cue(0);
      getAudioPlayer().play();
    }

  }// End keyPressed

  // Method for terrain generation using Perlin noise
  public void terrainGen() {
    terrain = new float[cols][rows]; // initialise terrain array

    float x_offset = 0; // offset values for Perlin noise
    float y_offset = 0;

    // Create randoms heights for terrain peaks
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        terrain[x][y] = map(noise(x_offset, y_offset), 0, 1, -75, 75);
        x_offset += 0.1;
      }
      y_offset += 0.1;
      x_offset = 0; // Reset x_offset after each row
    }

  } // End terrainGen

  public void draw() {

    background(0); // Clear the background
    translate(width / 2, height / 2 + 100); // Adjust to center terrain and move down slightly
    rotateX(PI / 3); // Bird's eye view
    translate(-(cols - 1) * scale / 2, -(rows - 1) * scale / 2); // Center the grid on the screen

    // Draw mesh with triangle strips
    for (int y = 0; y < rows - 1; y++) {
      beginShape(TRIANGLE_STRIP);
      for (int x = 0; x < cols; x++) {
        vertex(x * scale, y * scale, terrain[x][y]);
        vertex(x * scale, (y + 1) * scale, terrain[x][y + 1]);
      }
      endShape();
    }

  }

}// End MichaelsVisual