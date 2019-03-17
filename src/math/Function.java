package math; /**
 * Created by Arne Stenkrona on 03/15/19.
 */

/** Abstraction for function to be iterated when generating fractal **/
public interface Function {
    /** Applies the function to complex number c
     * @param c complex number to apply function on
     * @return the complex number that the function maps c to
     * **/
    public Complex apply(Complex c);

    /**Checks the rate that complex number c diverges when the fractal
     * function is applied iteratively
     * NOTE: This should be done analytically
     * and then implemented in code, as there is hard
     * to find a general condition for divergence given
     * an arbitrary function and a complex number
     * @param c complex number to check divergence on
     * @return rate of divergence, where 0.0 is none
     * **/
    public double rateOfDivergence(Complex c);
}
