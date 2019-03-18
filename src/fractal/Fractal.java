package fractal;


import javax.print.attribute.standard.Finishings;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

/**
 * Created by Arne Stenkrona on 03/15/19.
 */
public abstract class Fractal {
    /**Number of iterations allowed when calculating rate of divergence**/
    int maxIterations;

    public static final int DEFAULT_ITERATIONS = 100;
    public static final int ABSOLUTE_MAX_ITERATIONS = 10000;

    public Fractal(){
        maxIterations = DEFAULT_ITERATIONS;
    }

    public void setMaxIterations(int iterations){
            maxIterations = iterations;
    }

    /**
     * Draws an image of the fractal,
     * in the domain ca + a + i*(cb + b)
     * where a in [0, domainWidth] and b in [0, domainHeight],
     * onto a buffered image with dimensions x and y
     * huge thanks to: https://www.codingame.com/playgrounds/2358/how-to-plot-the-mandelbrot-set/adding-some-colors
     * @param ca the real part of the lower-left part of the domain to be drawn
     * @param cb the imaginary part of the lower-left part of the domain to be drawn
     * @param domainWidth width of the domain
     * //@param domainHeight of the domain
     * @param x width of the image
     * @param y of the image
     * @return a buffered image containing a picture of the fractal
     */
    public BufferedImage draw(double ca, double cb, double domainWidth, /*double domainHeight,*/ int x, int y){
        BufferedImage img = new BufferedImage(x,y, BufferedImage.TYPE_INT_ARGB);

        double dx = domainWidth / x;
        double dy = dx;// domainHeight / y;

        int histogram[] = new int[maxIterations+1];
        double values[][] = new double[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                double a = ca + i*dx;
                double b = cb + j*dy;
                double div = rateOfDivergence(a, b);
                int iterations = (int) Math.floor(div);
                values[i][j] = div;
                if (div < maxIterations)
                    histogram[iterations]++;
            }
        }

        double total = IntStream.of(histogram).sum();
        double hues[] = new double[maxIterations+1];
        double h = 0;
        for (int i = 0; i < maxIterations; i++) {
               h += histogram[i] / total;
               hues[i] = h;
        }
        hues[maxIterations] = h;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                double div = values[i][j];
                float hue = 1.0f - (float) interpolateColor(hues[(int)Math.floor(div)], hues[(int)Math.ceil(div)], div % 1.0);
                float saturation = 1.0f;
                float brightness = div < maxIterations ? 1.0f : 0.0f;
                img.setRGB(i, img.getHeight() - 1 - j, Color.getHSBColor(hue, saturation, brightness).getRGB());
            }
        }

        return img;
    }

    private double interpolateColor(double c1, double c2, double t){
        return c1 * (1-t) + c2 * t;
    }

    /**Checks the rate that complex number c diverges when the fractal
     * function is applied iteratively
     * NOTE: This should be done analytically
     * and then implemented in code, as there is hard
     * to find a general condition for divergence given
     * an arbitrary function and a complex number
     * @param a real part of complex number to check divergence on
     * @param b imaginary part of complex number to check divergence on
     * @return rate of divergence, where 0.0 is none
     * **/
    protected abstract double rateOfDivergence(double a, double b);

}
