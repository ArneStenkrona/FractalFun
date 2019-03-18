import fractal.Julia;
import fractal.Mandelbrot;
import view.GUI;
import view.Viewport;

/**
 * Created by Arne Stenkrona on 03/15/19.
 */
public class main {
    static int width = 1920 / 2;
    static int height = 1080 / 2;

    public static void main(String[] args) {
        GUI gui = new GUI(width, height);
    }
}
