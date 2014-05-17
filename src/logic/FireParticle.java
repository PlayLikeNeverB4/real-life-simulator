package logic;

import graphics.GraphicsManager;
import javafx.geometry.BoundingBox;
import logic.utils.GeometryUtils;

import java.awt.*;

/**
 * Particle that simulates fire
 */
public class FireParticle extends Particle {

    /**
     * The center of the fire
     */
    Position reference;

    /**
     * The angle to the center
     */
    double angle;

    /**
     * The maximum height this particle can have
     */
    double maximumHeight;

    public FireParticle(Position position, double maximumHeight, Color color, GraphicsManager graphicsManager) {
        super(position, 3, color, graphicsManager);
        reference = new Position(position);
        this.maximumHeight = maximumHeight;
        angle = 0.0;
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     *
     * @param time The amount of time passed since the last update
     */
    @Override
    public void update(double time) {
        speed.setX(Math.abs(speed.getX()));
        speed.setY(Math.abs(speed.getY()));
        double currentHeight = position.getZ() - reference.getZ();

        if(currentHeight <= maximumHeight / 2) { // move chaotically around the center
            position.setZ(position.getZ() + time * speed.getZ());
            double nextHeight = position.getZ() - reference.getZ();
            angle = GeometryUtils.normalizeAngle(angle + speed.getX());
            double radius = nextHeight / 3;
            Position center = GeometryUtils.computePointOnCircle(angle, radius);
            position = center.add(reference).add(new Position(0, 0, nextHeight));
        }
        else if(currentHeight <= maximumHeight) { // switch to only upward
            speed.setX(0);
            speed.setY(0);
            super.update(time);
        }
        else { // in the final phase, start dying
            lifeTime = -1;
        }
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
