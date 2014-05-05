package logic;

/**
 * Represents a 3D point in the game world
 */
public class Position {
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
