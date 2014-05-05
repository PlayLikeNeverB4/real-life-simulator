package logic;

/**
 * This class represents objects that can be moved
 */
public abstract class AbstractMovableObject extends AbstractObject {

    /**
     * The object's speed measured in units per second
     */
    protected double speed;

    /**
     * The object's acceleration measured in units per second^2
     * Negative acceleration means deceleration
     */
    protected double acceleration;

    /**
     * The last known position without collisions
     */
    protected Position lastValidPosition;

    public AbstractMovableObject(Position position, double direction, double speed, double acceleration) {
        super(position, direction);
        this.speed = speed;
        this.acceleration = acceleration;
        this.lastValidPosition = position;
    }

    public AbstractMovableObject(AbstractMovableObject movableObject) {
        this(movableObject.position, movableObject.direction, movableObject.speed, movableObject.acceleration);
    }

    protected AbstractMovableObject(Position position) {
        this(position, 0.0, 0.0, 0.0);
    }

    public AbstractMovableObject(Position position, double direction) {
        this(position, direction, 0.0, 0.0);
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * Tests if this object can move or not
     */
    public boolean isMovable() {
        return true;
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    public void update(double time) {
        // save the last position
        lastValidPosition = new Position(position);

        updatePosition(time);
    }

    /**
     * Updates the objects' position considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    private void updatePosition(double time) {
        double distance = this.speed * time;
        double crtX = this.position.getX();
        double crtY = this.position.getY();
        double nextX = crtX + Math.cos(this.direction) * distance;
        double nextY = crtY + Math.sin(this.direction) * distance;
        this.position.setX(nextX);
        this.position.setY(nextY);
    }

    /**
     * Updates the objects' speed considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    private void updateSpeed(double time) {
        double delta = this.acceleration * time;
        this.speed += delta;
    }

    protected void collisionBounceHandler(AbstractObject abstractObject) {
        // TODO: do something maybe?
    }

    protected void revertToLastValidPosition() {
        position = new Position(lastValidPosition);
    }

}