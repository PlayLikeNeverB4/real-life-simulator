package logic;

public abstract class AbstractStaticObject extends AbstractObject {

    public AbstractStaticObject(Position position) {
        super(position);
    }

    /**
     * Tests if this object can move or not
     */
    public boolean isMovable() {
        return false;
    }

    /**
     * Returns the current speed of the object
     */
    @Override
    public double getCurrentSpeed() {
        return 0;
    }

    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     * @param abstractObject The other object that this object collided with
     */
    protected void collisionBounceHandler(AbstractObject abstractObject) {
        // do nothing
    }

    /**
     * Notifies this object that it collided with a movable object
     * It updates this object's state depending on the movable object's speed and direction
     * @param movableObject The other object that this object collided with
     */
    protected void collidedWithMovableObject(AbstractMovableObject movableObject) {
        // do nothing
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