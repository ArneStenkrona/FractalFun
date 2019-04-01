package math;
/*
/**
 * Created by Arne Stenkrona on 03/16/19.
 */
/**This class contains function to calculate log with different bases**/
public final class Log {
    private Log(){}

    /**
     * Calculates the log base 2 for given integer
     * Created by x4u: https://stackoverflow.com/a/3305710
     * @param bits integer input to log2
     * @return log base 2 of bits
     * **/
    public static int binlog( int bits ) // returns 0 for bits=0
    {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }

    /**
     * Calculates the logarithm of base b for input n
     * @param b base for the logarithm
     * @param n input to the logarithm
     * @return log_b of n
     * **/
    public static double log(double b, double n) {
        return Math.log(n) / Math.log(b);
    }
}
