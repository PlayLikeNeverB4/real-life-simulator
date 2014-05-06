package logic;

import graphics.AbstractRenderer;
import javafx.geometry.BoundingBox;

/**
 * Represents an object in the game world
 */
public abstract class AbstractObject {

    /**
     * The position of the object in the game world
     */
    protected Position position;

    /**
     * The object's orientation represented as the angle to the OX axis in the XOY plane
     */
    protected double direction;

    /**
     * The renderer which will draw the object
     */
    protected AbstractRenderer renderer;

    protected AbstractObject() { }

    protected AbstractObject(Position position) {
        this.position = position;
    }

    public AbstractObject(Position position, double direction) {
        this(position);
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public AbstractRenderer getRenderer() {
        return renderer;
    }

    /**
     * Tests if this object can move or not
     */
    public abstract boolean isMovable();

    protected void updatePosition(double dx, double dy, double dz) {
        this.position.setX(this.position.getX() + dx);
        this.position.setY(this.position.getY() + dy);
        this.position.setZ(this.position.getZ() + dz);
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    public abstract BoundingBox[] getBoundingBoxes();

    /**
     * Tests if this object is in collision with another object
     * @param other The object to test collision with
     */
    public boolean isInCollisionWith(AbstractObject other) {
        BoundingBox[] boundingBoxes = getBoundingBoxes();
        BoundingBox[] otherBoundingBoxes = other.getBoundingBoxes();
        if(boundingBoxes == null || otherBoundingBoxes == null)
            return false;

        for(BoundingBox boundingBox : boundingBoxes)
            for(BoundingBox otherBoundingBox : otherBoundingBoxes)
                if(boundingBox.intersects(otherBoundingBox))
                    return true;

        return false;
    }

    /**
     * Notifies this object that it collided with an object and it updates its state
     * @param abstractObject The other object that this object collided with
     */
    public void collidedWith(AbstractObject abstractObject) {
        abstractObject.collisionSpecialEffects(this);
        if(this.isMovable())
            ((AbstractMovableObject) this).revertToLastValidPosition();
        collisionBounceHandler(abstractObject);
        if(abstractObject.isMovable())
            collidedWithMovableObject((AbstractMovableObject) abstractObject);
    }

    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     * @param abstractObject The other object that this object collided with
     */
    protected abstract void collisionBounceHandler(AbstractObject abstractObject);

    /**
     * Notifies this object that it collided with a movable object
     * It updates this object's state depending on the movable object's speed and direction
     * @param movableObject The other object that this object collided with
     */
    protected abstract void collidedWithMovableObject(AbstractMovableObject movableObject);

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     * @return true if the current collision is resolved; false otherwise
     */
    protected abstract void collisionSpecialEffects(AbstractObject abstractObject);

}