package logic;

import graphics.IRenderable;

/**
 * Represents an object in the game world
 */
public abstract class AbstractObject implements IRenderable {

    /**
     * The position of the object in the game world
     */
    Position position;

    protected void updatePosition(double dx, double dy, double dz) {
    }

}