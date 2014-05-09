package logic;

import logic.utils.GeometryUtils;

/**
 * Represents a 3D point in the game world
 */
public class Position implements Comparable<Position> {
    private double x;
    private double y;
    private double z;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(Position position) {
        this(position.x, position.y, position.z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPosition(Position position) {
        set(position.x, position.y, position.z);
    }

    /**
     * @param angle              The angle to the OX axis in the XOY plane
     * @param horizontalDistance The horizontal distance to the original position
     * @param verticalDistance   The vertical distance to the original position
     * @return                   A position at an angle and distance from this position
     */
    public Position computeRelativePosition(double angle, double horizontalDistance, double verticalDistance) {
        double nextX = x - Math.cos(angle) * horizontalDistance;
        double nextY = y - Math.sin(angle) * horizontalDistance;
        double nextZ = z + verticalDistance;
        return new Position(nextX, nextY, nextZ);
    }

    public Position add(Position position) {
        return new Position(x + position.x,
                            y + position.y,
                            z + position.z);
    }

    /**
     * Moves the position in the XOY plane which is at a distance to (0,0) and at a specified angle
     * @param angle     The angle to the OX axis in the XOY plane direction axis
     */
    public Position move(double angle, double distance) {
        Position delta = GeometryUtils.computePointOnCircle(angle, distance);
        return this.add(new Position(delta.getX(), delta.getY(), delta.getZ()));
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public int compareTo(Position o) {
        if(x == o.x) {
            if(y == o.y) {
                if(z == o.z) {
                    return 0;
                } else if(z > o.z) {
                    return 1;
                } else {
                    return -1;
                }
            } else if(y > o.y) {
                return 1;
            } else {
                return -1;
            }

        } else if(x > o.x){
            return 1;
        } else {
            return -1;
        }
    }

}
