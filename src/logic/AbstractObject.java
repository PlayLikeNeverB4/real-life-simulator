package logic;

import graphics.AbstractRenderer;
import javafx.geometry.BoundingBox;

/**
 * Represents an object in the game world
 */
public abstract class AbstractObject implements Comparable<AbstractObject> {

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

    public void setPosition(Position position) {
        this.position = position;
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

    /**
     * Returns the current speed of the object
     */
    public abstract double getCurrentSpeed();

    /**
     * Updates something unique.
     * @param time The time passed since the last update
     */
    public abstract void specialUpdate(double time);

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
        collisionBounceHandler(abstractObject);
        if(this.isMovable())
            ((AbstractMovableObject) this).revertToLastValidPosition();
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
     */
    protected abstract void collisionSpecialEffects(AbstractObject abstractObject);

    @Override
    public int compareTo(AbstractObject o) {
        if(position.compareTo(o.getPosition()) == 0) {
            BoundingBox[] boundingBoxesThis = this.getBoundingBoxes();
            BoundingBox[] boundingBoxes = o.getBoundingBoxes();
            int conditionEvaluation = 0;
            if(boundingBoxesThis != null && boundingBoxes != null) {
                Dimension dimensionObj = new Dimension(boundingBoxes[0].getMaxX(),boundingBoxes[0].getMaxY(), boundingBoxes[0].getMaxZ());
                Dimension dimensionThis = new Dimension(boundingBoxesThis[0].getMaxX(),boundingBoxesThis[0].getMaxY(), boundingBoxesThis[0].getMaxZ());
                conditionEvaluation = dimensionThis.compareTo(dimensionObj);
            }
            if(conditionEvaluation == 0) {
                return 0;
            } else if(conditionEvaluation == 1) {
                return 1;
            } else {
                return -1;
            }
        }
        return position.compareTo(o.getPosition());
    }

}