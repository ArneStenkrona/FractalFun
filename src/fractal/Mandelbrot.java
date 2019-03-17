package fractal;

import math.Log;

/**
 * Created by Arne Stenkrona on 03/16/19.
 */
public class Mandelbrot extends Fractal {
    @Override
    /**
     * In the case of the mandelbrot set we check
     * the normalized number of iterations that it takes
     * to reach the escape radious of 2.
     * More details can be found here:
     * http://linas.org/art-gallery/escape/escape.html
     * **/
    protected double rateOfDivergence(double a, double b) {
        int iterations = 0;

        double za = 0, zb = 0;

        /*Iterate the function f(z) = z^2 + c, where c = a + i*b, z = za +i*zb
        * until abs(c) >= 2 or we've exceeded max number of iterations*/
        while (iterations < maxIterations && za*za + zb*zb < 4) {
            double temp = za;
            za = za*za - zb*zb + a;
            zb = 2*temp*zb + b;
            iterations++;
        }

        if (iterations == maxIterations)
            return maxIterations;

        return iterations + 1 - Math.log(Log.log(2, Math.sqrt(za*za+zb*zb)));
    }
}
