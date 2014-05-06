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
        this.lastValidPosition = new Position(position);
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

    /**
     * Moves the object located at (x, y, z) to (x+dx, y+dy, z+dz)
     * @param dx x distance
     * @param dy y distance
     * @param dz z distance
     * @param isValid true if the move is known to be valid; false otherwise
     */
    public void move(double dx, double dy, double dz, boolean isValid) {
//        if(this instanceof MainCharacter)
//            System.out.println("Moved " + dx + " " + dy + " " + dz + " " + isValid);
        if(!isValid)
            this.lastValidPosition = new Position(this.position);
        this.position = this.position.add(new Position(dx, dy, dz));
        if(isValid)
            this.lastValidPosition = new Position(this.position);
    }

    /**
     * Moves the object located at (x, y, z) to (x+dx, y+dy, z+dz)
     * @param dx x distance
     * @param dy y distance
     * @param dz z distance
     */
    public void move(double dx, double dy, double dz) {
        move(dx, dy, dz, false);
    }

    protected void revertToLastValidPosition() {
//        if(this instanceof MainCharacter)
//            System.out.println("Reverted from " + position + "\nto " + lastValidPosition);
        position = new Position(lastValidPosition);
    }

    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     * @param abstractObject The other object that this object collided with
     */
    protected void collisionBounceHandler(AbstractObject abstractObject) {
        // TODO: do something maybe?
    }

    /**
     * Notifies this object that it collided with a movable object
     * It updates this object's state depending on the movable object's speed and direction
     * @param movableObject The other object that this object collided with
     */
    protected void collidedWithMovableObject(AbstractMovableObject movableObject) {
        // default action: do nothing
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     * @return true if the current collision is resolved; false otherwise
     */
    protected void collisionSpecialEffects(AbstractObject abstractObject) {
        // default action: do nothing
    }

}