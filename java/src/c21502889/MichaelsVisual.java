package c21502889;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class MichaelsVisual extends Visual {
  int scale; // scale of terrain mesh
  float[][] terrain; // create 2d array for terrain

  // Mesh rows and columns
  int cols;
  int rows;

  float previousAmplitude = 0; // Store previous frame's amplitude
  float colourChangeThreshold = 0.01f; // Threshold to reduce frequency of colour changes

  float scroll = 0; // Variable for terrain scrolling
  boolean isScrolling = false; // Flag to control when to start scrolling


  public void settings() {
    size(800, 800, P3D);

    // Use this to make fullscreen
    // fullScreen();

    // Use this to make fullscreen and use P3D for 3D graphics
    fullScreen(P3D, SPAN);

  }// End settings

  public void setup() {
    startMinim();
    background(0);
    colorMode(HSB, 360, 100, 100); // Should give better range of colour
    noFill();
    
    scale = 15; // Assign scale
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
      isScrolling = true; // Start scrolling on space press
    }

  }// End keyPressed

  // Method for terrain generation using Perlin noise
  public void terrainGen() {

    // Check if space has been pressed
    if (isScrolling == true){
      scroll -= 0.01; // Decrement scroll on each terrain generation so terrain appears to be moving
    }
    
    terrain = new float[cols][rows]; // initialise terrain array

    float x_offset = 0; // offset values for Perlin noise
    float y_offset = scroll;

    // Get amplitude data
    float amplitude = getAmplitude();
    amplitude = amplitude + super.getSmoothedAmplitude() * 600; //amplify amplitude

    // Create randoms heights for terrain peaks
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        // Use the audio amplitude to modulate the height of the terrain
        terrain[x][y] = map(noise(x_offset, y_offset), 0, 1, -75, 75) + amplitude;
        x_offset += 0.1;
      }
      y_offset += 0.1;
      x_offset = 0; // Reset x_offset after each row
    }

  } // End terrainGen

  public void draw() {

    background(0); // Clear the background
    //lights(); // Set the default lighting

    // Perform FFT analysis
    try{
      calculateFFT();
      calculateFrequencyBands(); 
    } catch (VisualException e) {
      System.err.println("Error: Failed to calculate FFT");
      return;
    }

    // Update terrain based on the FFT analysis
    calculateAverageAmplitude();
    terrainGen(); // Modify terrain generation to respond to audio

    translate(width / 2, height / 2 + 100); // Adjust to center terrain and move down slightly
    rotateX(PI / 3); // Bird's eye view
    translate(-(cols - 1) * scale / 2, -(rows - 1) * scale / 2); // Center the terrain on the screen

    // Update the colour only after significant amplitude change
    float currentAmplitude = getSmoothedAmplitude() * 1000;
    if (Math.abs(currentAmplitude - previousAmplitude) > colourChangeThreshold) {
        previousAmplitude = currentAmplitude; 
    }

    // Define colour within blue to purple range
    float hue = map(previousAmplitude, 0, 100, 240, 270);
    int colour = color(hue, 100, 100);
    stroke(colour);

    // Draw terrain mesh with triangle strips
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