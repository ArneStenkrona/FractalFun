package fractal;//package fractal;

import math.Log;

public class Julia extends Fractal {

    private double ca, cb;

    public Julia(double real, double imag) {
        this.ca = real;
        this.cb = imag;
    }

    public void setConstant(double real, double imag) {
        this.ca = real;
        this.cb = imag;
    }

    @Override
    protected double rateOfDivergence(double a, double b) {

        int iterations = 0;
        double za = a, zb = b;

        /*Iterate the function f(z) = z^2 + c, where c = ca + i*cb, z = za +i*zb
         * until abs(c) >= 2 or we've exceeded max number of iterations*/
        while (iterations < maxIterations && za*za + zb*zb < 4) {

            double xtemp = za * za - zb * zb;
            zb = 2 * za * zb  + cb;
            za = xtemp + ca;

            iterations++;
        }

        if (iterations == maxIterations)
            return maxIterations;

        return iterations + 1 - Math.log(Log.log(2, Math.sqrt(za*za+zb*zb)));
    }
}
