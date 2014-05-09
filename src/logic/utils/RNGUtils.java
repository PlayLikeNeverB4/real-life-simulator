package logic.utils;

import logic.Position;

/**
 * Helper class for generating random numbers
 */
public class RNGUtils {

    /**
     * Generates 3 random numbers
     * The first 2 of them are from the interval [0, limxy)
     * The third is from the interval [0, limz]
     * @param limxy     The upper limit for the first 2 generated numbers
     * @param limz      The upper limit for the third generated number
     * @return          A {@link Position} used as a container for 3 random numbers that were generated
     */
    public static Position generate(double limxy, double limz) {
        double number1 = Math.random() * limxy;
        double number2 = Math.random() * limxy;
        double number3 = Math.random() * limz;
        return new Position(number1, number2, number3);
    }

    /**
     * Generates 3 random numbers
     * The first 2 of them are from the interval [minLimxy, maxLimxy]
     * The third is from the interval [minLimz, maxLimz]
     * @param minLimxy      The lower limit for the first 2 generated numbers
     * @param maxLimxy      The upper limit for the first 2 generated numbers
     * @param minLimz       The lower limit for the third generated number
     * @param maxLimz       The upper limit for the third generated number
     * @return              A {@link Position} used as a container for 3 random numbers that were generated
     */
    public static Position generate(double minLimxy, double maxLimxy, double minLimz, double maxLimz) {
        double number1 = minLimxy + (Math.random() * ((maxLimxy - minLimxy) + 1));
        double number2 = minLimxy + (Math.random() * ((maxLimxy - minLimxy) + 1));
        double number3 = minLimz + (Math.random() * ((maxLimz - minLimz) + 1));
        return new Position(number1, number2, number3);
    }

    /**
     * Generates a random number from the interval [0, lim)
     * @param lim       The upper limit for the generated number
     * @return          A random double number
     */
    public static double generateDouble(double lim) {
        double number1 = Math.random()* lim;
        return number1;
    }

}
