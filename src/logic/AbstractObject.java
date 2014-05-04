package logic;

import graphics.AbstractRenderer;

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

    protected void updatePosition(double dx, double dy, double dz) {
        this.position.setX(this.position.getX() + dx);
        this.position.setY(this.position.getY() + dy);
        this.position.setZ(this.position.getZ() + dz);
    }

    protected AbstractObject() { }

    protected AbstractObject(Position position) {
        this.position = position;
    }
}