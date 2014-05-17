package logic;

import graphics.FirePlaceRenderer;
import graphics.GraphicsManager;
import javafx.geometry.BoundingBox;
import logic.shapes.ShapeSurfaceType;
import logic.shapes.StaticParallelepiped;

import java.awt.*;

/**
 * A place where the main character has the option to start a fire
 */
public class FirePlace extends AbstractStaticObject {

    /**
     * The current state of the fire
     */
    boolean on;

    /**
     * The parallelepiped that marks the spot for the fireplace
     */
    StaticParallelepiped place;

    /**
     * The particles for simulating fire
     */
    FireParticleSource fireParticleSource;

    public FirePlace(Position position, GraphicsManager graphicsManager) {
        super(position);
        renderer = new FirePlaceRenderer(this, graphicsManager);
        place = new StaticParallelepiped(position, new Dimension(30, 30, 10), new ShapeSurfaceType(Color.BLACK), graphicsManager);
        on = false;
        fireParticleSource = new FireParticleSource(position.add(new Position(5, 5, 10)), graphicsManager);
    }

    public StaticParallelepiped getPlace() {
        return place;
    }

    public FireParticleSource getFireParticleSource() {
        return fireParticleSource;
    }

    public boolean isOn() {
        return on;
    }

    /**
     * Starts the fire
     */
    public void startFire() {
        fireParticleSource.start();
        on = true;
    }

    /**
     * Stops the fire
     */
    public void stopFire() {
        fireParticleSource.stop();
        on = false;
    }

    /**
     * Updates something unique.
     *
     * @param time The time passed since the last update
     */
    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        fireParticleSource.specialUpdate(time);
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
