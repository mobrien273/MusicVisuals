# Music Visualiser Project

[![YouTube](images/thumbnail.png)](https://youtu.be/A6T_E21GGHo)

Youtube video demonstrating the visualizer.

Name: Michael O'Brien

Student Number: C21502889


## Description of the assignment
The visualizer features a procedurally generated terrain that responds to audio. The song chosen is "Everything in its Right Place" by Radiohead. The synths and electronic sound of the song reminded me of old wireframe 3D graphics so I wanted to emulate this with my visualizer.


## Instructions
To run the visualizer, run *Main.java* in the *ie/tudublin* package. Press the space bar to start the music.


## How it works

### Terrain Generation
The *terrainGen()* method handles terrain generation. The *terrain* array stores the heights for each point on the terrain. The *noise()* method is called in a nested loop for each point on the terrain to create natural looking hills on the terrain using Perlin noise. The amplitude from the *getAmplitude()* method is added to this to modulate the height of the terrain in response to the amplitude of the audio. The *scroll* variable is decremented when the  to continuously adjust the *y_offset*. This simlulates forward movement through an infinite landscape.

### Audio
The *minim* library is used to handle audio playback and analysis. The *getAmplitude()* method is used to get amplitude data for the song being played. As mentioned before the amplitude is used to modulate the terrain height in the terrainGen() method. The amplitude data is also used to shift the colour of the terrain. The program stores the amplitude data from the previous frame in the *previousAmplitude* variable and if it passes a predefined threshold the colour shifts between various hues of blue and purple.

### Rendering
The *draw()* method handles the rendering of the terrain. The terrain is drawn using a mesh of traingle strips. A nested loop is used and for each row of the terrain grid the *beginShape* method is called which creates the triangle strips that make up the terrain. Within the second loop vertices are defined for each point on the terrain grid. Each vertex is connected to the previous one in the strip. These vertices make up a row of terrain. *endShape()* is then called and the process begins for the next row in the terrain grid.

## What I am most proud of in the assignment
Despite being basic project I am happy with how the visuals turned out. Through creating this project I learned about Perlin noise and gained a better understanding of Java and the Processing library.