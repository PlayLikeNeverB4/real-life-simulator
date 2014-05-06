package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;
import javafx.geometry.BoundingBox;
import logic.utils.GeometryUtils;

/**
 * The object which the user controls
 */
public class MainCharacter extends AbstractPerson {

    /**
     * The sensitivity of the user control keys when moving the main character
     */
    private double movingSpeed;

    public MainCharacter(Position position, GraphicsManager graphicsManager, double movingSpeed) {
        super(position, Math.PI / 2);
        this.movingSpeed = movingSpeed;
        this.renderer = new MaleRenderer(this, graphicsManager);
    }

    public double getMovingSpeed() {
        return movingSpeed;
    }

    /**
     * Moves the mainCharacter in the XOY plane which is at a distance to (0,0) and at a specified angle
     * @param angle     The angle to the mainCharacter's direction axis
     */
    public void move(double angle, double distance) {
        this.lastValidPosition = new Position(this.position);
        this.position = this.position.add(GeometryUtils.computePointOnCircle(direction + angle, distance));
    }

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(position.getX() - 5, position.getY() - 5, position.getZ(),
                               10, 10, 30);
    }
}