package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;

/**
 * The object which the user controls
 */
public class MainCharacter extends AbstractPerson {

    /**
     * The direction where the main character is looking
     */
    private double watchingDirection;

    /**
     * The sensitivity of the user control keys when moving the main character
     */
    private final double movingSpeed;

    /**
     * True if the main character is moving; false otherwise
     */
    private boolean inMotion;

    public MainCharacter(Position position, Dimension dimension, GraphicsManager graphicsManager, double movingSpeed) {
        super(position, dimension, graphicsManager);
        this.watchingDirection = 0;
        this.movingSpeed = movingSpeed;
        this.graphicsManager = graphicsManager;
        this.renderer = new MaleRenderer(this, graphicsManager);
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }

    public void setWatchingDirection(double watchingDirection) {
        this.watchingDirection = watchingDirection;
    }

    public void setInMotion(boolean inMotion) {
        this.inMotion = inMotion;
    }


    @Override
    public double getWatchingDirection() {
        return watchingDirection;
    }

    /**
     * Returns the current speed of the object
     */
    @Override
    public double getCurrentSpeed() {
        if(inMotion)
            return movingSpeed;
        else
            return 0;
    }

    /**
     * Moves the mainCharacter in the XOY plane which is at a distance to (0,0) and at a specified angle
     * @param angle     The angle to the mainCharacter's watching direction axis
     */
    @Override
    public void move(double angle, double distance) {
        super.move(watchingDirection + angle, distance);
        computeHumanBodyComponents();
    }


    /**
     * Notifies this object that it collided with an object
     * It updates this object's state depending on whether it bounces or not
     * @param abstractObject The other object that this object collided with
     */
    @Override
    public void collisionBounceHandler(AbstractObject abstractObject) {
        // do nothing (for now)
    }

    @Override
    public void moveTo(double nextX, double nextY, double nextZ, boolean isValid) {
        super.moveTo(nextX, nextY, nextZ, isValid);
        computeHumanBodyComponents();
    }

}