package logic;

import graphics.GraphicsManager;
import graphics.ParticleRenderer;
import javafx.geometry.BoundingBox;

import java.awt.*;

/**
 * Represents a particle that simulates some physics
 */
public class Particle extends AbstractMovableObject {

    /**
     * The color of the particle
     */
    Color color;

    /**
     * The (x,y,z) speed of the particle
     */
    Position speed;

    /**
     * The lifetime of the particle
     */
    double lifeTime;

    /**
     * The starting position of the particle
     */
    Position originalPosition;

    public Particle(Position position, int numberOfSlices, Color color, GraphicsManager graphicsManager) {
        super(position);
        originalPosition = new Position(position);
        this.lifeTime = Math.random();
        this.color = color;
        speed = new Position((Math.random() - 0.5) / 4, (Math.random() - 0.5) / 4, Math.abs((Math.random() - 0.5) / 4));
        renderer = new ParticleRenderer(this, numberOfSlices, graphicsManager);
    }

    public Color getColor() {
        return color;
    }

    public double getLifeTime() {
        return lifeTime;
    }

    /**
     * Reset the life of the particle. Start from the original position
     */
    public void reset() {
        position = new Position(originalPosition);
        speed = new Position((Math.random() - 0.5) / 4, (Math.random() - 0.5) / 4, Math.abs((Math.random() - 0.5) / 4));
        lifeTime = Math.random();
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     *
     * @param time The amount of time passed since the last update
     */
    @Override
    public void update(double time) {
        position.set(position.getX() + time * speed.getX(),
                     position.getY() + time * speed.getY(),
                     position.getZ() + time * speed.getZ());
        lifeTime -= time / 1000.0;
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
