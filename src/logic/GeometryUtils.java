package logic;

/**
 * Handles most of the geometric calculations
 */
public class GeometryUtils {

    /**
     * Computes a point in the XOY plane which is at horizontalDistance distance to (0,0) and at a specified angle
     * The z-coordinate of the returned {@link Position} will always be 0
     * @param angle              The angle to the OX axis
     * @param horizontalDistance The distance to (0,0)
     * @return                   A {@link Position} at the requested angle and distance to (0,0)
     */
    public static Position computePointOnCircle(double angle, double horizontalDistance) {
        double nextX = Math.cos(angle) * horizontalDistance;
        double nextY = Math.sin(angle) * horizontalDistance;
        double nextZ = 0;
        return new Position(nextX, nextY, nextZ);
    }

    /**
     * Return an angle in the range [0..2PI] equivalent to the passed angle
     * @param angle The angle to be normalized
     * @return      The normalized angle
     */
    public static double normalizeAngle(double angle) {
        if(angle < 0.0)
            angle += 2 * Math.PI;
        else if(angle >= 2 * Math.PI)
            angle -= 2 * Math.PI;
        return angle;
    }
}
