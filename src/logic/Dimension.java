package logic;

/**
 * Represents a 3D dimension in the game world
 */
public class Dimension implements Comparable<Dimension> {
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

    public Dimension(double width, double height) {
        this.x = width;
        this.z = height;
    }

    public Dimension(Position position) {
        this(position.getX(), position.getY(), position.getZ());
    }

    public Dimension(double size) {
        this(size, size, size);
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

    public double getWidth() {
        return x;
    }

    public double getHeight() {
        return z;
    }

    @Override
    public int compareTo(Dimension o) {
        Position pos = new Position(o.x, o.y, o.z);
        Position thisDimension = new Position(x, y, z);
        return thisDimension.compareTo(pos);
    }

}
