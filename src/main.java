import fractal.Mandelbrot;
import view.Viewport;

/**
 * Created by Arne Stenkrona on 03/15/19.
 */
public class main {
    static int width = 1920 / 2;
    static int height = 1080 / 2;

    public static void main(String[] args) {
        Mandelbrot m = new Mandelbrot();
        Viewport vp = new Viewport(width, height, m);
        vp.drawFractal();
    }
}
