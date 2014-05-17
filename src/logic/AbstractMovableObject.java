package logic;

import logic.utils.GeometryUtils;

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
     * Returns the current speed of the object
     */
    @Override
    public double getCurrentSpeed() {
        return this.speed;
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    public void update(double time) {
        // save the last position
        lastValidPosition = new Position(position);

        updatePosition(time);
        updateSpeed(time);
    }

    /**
     * Updates the objects' position considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    private void updatePosition(double time) {
        double distance = this.speed * time;
        double addX = Math.cos(this.direction) * distance;
        double addY = Math.sin(this.direction) * distance;
        move(addX, addY, 0);
    }

    /**
     * Updates the objects' speed considering its current speed, acceleration and direction
     * @param time The amount of time passed since the last update
     */
    private void updateSpeed(double time) {
        double delta = this.acceleration * time;
        this.speed = Math.max(0, this.speed + delta);
    }

    /**
     * Updates something unique.
     * @param time The time passed since the last update
     */
    @Override
    public void specialUpdate(double time) {
        // default: do nothing
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

    /**
     * Moves this object from (x, y, z) to (x + delta.x, y + delta.y, z + delta.z)
     */
    public void move(Position delta) {
        move(delta.getX(), delta.getY(), delta.getZ());
    }

    /**
     * Moves the object located at (x, y, z) to (nextX, nextY, nextZ)
     * @param nextX x-coordinate
     * @param nextY y-coordinate
     * @param nextZ z-coordinate
     */
    public void moveTo(double nextX, double nextY, double nextZ) {
        move(nextX - position.getX(), nextY - position.getY(), nextZ - position.getZ());
    }

    /**
     * Moves this object to another position
     * @param isValid true if the move is known to be valid; false otherwise
     */
    public void moveTo(double nextX, double nextY, double nextZ, boolean isValid) {
        move(nextX - position.getX(), nextY - position.getY(), nextZ - position.getZ(), isValid);
    }

    /**
     * Same as moveTo(nextX, nextY, nextZ)
     */
    public void moveTo(Position next) {
        moveTo(next, false);
    }

    /**
     * Moves this object to another position
     * @param next    The target {@link logic.Position}
     * @param isValid true if the move is known to be valid; false otherwise
     */
    public void moveTo(Position next, boolean isValid) {
        moveTo(next.getX(), next.getY(), next.getZ(), isValid);
    }

    /**
     * Moves this object by a distance at an angle
     */
    public void move(double angle, double distance) {
        Position delta = GeometryUtils.computePointOnCircle(angle, distance);
        move(delta);
    }

    /**
     * Moves back this object to the last known valid position
     */
    protected void revertToLastValidPosition() {
        moveTo(lastValidPosition, true);
    }

    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     * @param abstractObject The other object that this object collided with
     */
    public void collisionBounceHandler(AbstractObject abstractObject) {
        if(this.position.getZ() == this.lastValidPosition.getZ() && this.position.compareTo(this.lastValidPosition) != 0)
            this.speed /= 2;
        // default action: don't bounce
    }

    /**
     * Notifies this object that it collided with a movable object.
     * It updates this object's state depending on the movable object's speed and direction
     * @param movableObject The other object that this object collided with
     */
    public void collidedWithMovableObject(AbstractMovableObject movableObject) {
        if(movableObject.getCurrentSpeed() > 0) { // the other object is moving, so it might affect this object
            this.direction = movableObject.direction;
            this.speed = movableObject.getCurrentSpeed();
        }
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     */
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        // default action: do nothing
    }

}