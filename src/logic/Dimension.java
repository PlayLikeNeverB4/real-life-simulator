package logic;

/**
 * Represents a 3D dimension in the game world
 */
public class Dimension {
    private double x;
    private double y;
    private double z;

    public Dimension(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Dimension(Dimension otherDimension) {
        this(otherDimension.x, otherDimension.y, otherDimension.z);
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
}
