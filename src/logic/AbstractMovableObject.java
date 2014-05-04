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

    protected AbstractMovableObject(Position position) {
        super(position);
    }

    public double getSpeed() {
        return speed;
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    public void update(double time) {
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
        double nextY = crtX + Math.sin(this.direction) * distance;
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
}