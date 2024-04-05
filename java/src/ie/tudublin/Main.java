package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import c21502889.MichaelsVisual;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new MichaelsVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}