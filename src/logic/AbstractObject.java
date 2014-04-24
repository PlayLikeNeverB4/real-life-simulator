package logic;

import graphics.AbstractRenderer;

/**
 * Represents an object in the game world
 */
public abstract class AbstractObject {

    /**
     * The position of the object in the game world
     */
    Position position;

    /**
     * The renderer which will draw the object
     */
    AbstractRenderer renderer;

    public AbstractRenderer getRenderer() {
        return renderer;
    }

    protected void updatePosition(double dx, double dy, double dz) {
    }

}