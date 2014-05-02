package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;

/**
 * The object which the user controls
 */
public class MainCharacter extends AbstractPerson {

    public MainCharacter(Position position, GraphicsManager graphicsManager, double speed) {
        super(position);
        this.speed = speed;
        this.renderer = new MaleRenderer(this, graphicsManager);
        direction = Math.PI / 2;
    }

    /**
     * Moves the mainCharacter in the XOY plane which is at a distance to (0,0) and at a specified angle
     * @param angle     The angle to the mainCharacter's direction axis
     */
    public void move(double angle, double distance) {
        this.position = this.position.add(GeometryUtils.computePointOnCircle(direction + angle, distance));
    }
}