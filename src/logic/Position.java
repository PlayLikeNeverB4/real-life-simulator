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
     * Adds two positions
     * @param position The {@link Position} to add
     * @return A new {@link Position} which represents the sum of this {@link Position} and the other one
     */
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

    /**
     * Computes and returns the distance between this {@link Position} and the other one
     * @param position The {@link Position} to compute the distance to
     */
    public double distanceTo(Position position) {
        double dx = x - position.x;
        double dy = y - position.y;
        return Math.sqrt(dx * dx + dy * dy);
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
        if(Double.compare(x, o.x) != 0)
            return Double.compare(x, o.x);
        if(Double.compare(y, o.y) != 0)
            return Double.compare(y, o.y);
        return Double.compare(z, o.z);
    }
}
