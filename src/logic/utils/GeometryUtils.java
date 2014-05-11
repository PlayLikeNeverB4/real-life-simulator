package logic.utils;

import logic.Position;

/**
 * Handles most of the geometric calculations
 */
public class GeometryUtils {

    /**
     * Computes a point in the XOY plane which is at horizontalDistance distance to (0,0) and at a specified angle
     * The z-coordinate of the returned {@link logic.Position} will always be 0
     * @param angle              The angle to the OX axis
     * @param horizontalDistance The distance to (0,0)
     * @return                   A {@link logic.Position} at the requested angle and distance to (0,0)
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

    /**
     * Computes and returns the angle difference between angle1 and angle2
     */
    public static double computeAngleDifference(double angle1, double angle2) {
        angle1 = normalizeAngle(angle1);
        angle2 = normalizeAngle(angle2);
        return Math.min(Math.abs(angle1 - angle2), 2 * Math.PI - Math.abs(angle1 - angle2));
    }

    /**
     * Rotates point2 around point1 by an angle
     * @return A {@link Position} representing the rotating point
     */
    public static Position rotateAroundPoint(Position point1, Position point2, double angle) {
        // Translate to origin
        Position P = new Position(point2.getX() - point1.getX(), point2.getY() - point1.getY());

        // Rotate
        double currentAngle = Math.atan2(P.getY(), P.getX());
        double nextAngle = GeometryUtils.normalizeAngle(currentAngle + angle);
        double distance = P.distanceTo(new Position(0, 0));
        P = GeometryUtils.computePointOnCircle(nextAngle, distance);

        // Translate back
        P = P.add(point1);

        return P;
    }

    /**
     * Computes and returns the cross product between A, B and C in 2D
     */
    public static double crossProduct(Position A, Position B, Position C) {
        return (B.getX() - A.getX()) * (C.getY() - A.getY()) - (B.getY() - A.getY()) * (C.getX() - A.getX());
    }

}
