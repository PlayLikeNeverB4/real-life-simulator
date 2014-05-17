package logic;

import graphics.GraphicsManager;
import graphics.ParticleSourceRenderer;
import javafx.geometry.BoundingBox;

import java.util.LinkedList;
import java.util.List;

/**
 * A source (generator) of particles.
 */
public class ParticleSource extends AbstractStaticObject {

    /**
     * The list of currently active particles
     */
    protected List<Particle> particles;

    public ParticleSource(Position position, GraphicsManager graphicsManager) {
        super(position);
        renderer = new ParticleSourceRenderer(this, graphicsManager);
        particles = new LinkedList<Particle>();
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public boolean isDead() {
        return particles.size() == 0;
    }

    /**
     * Updates something unique.
     *
     * @param time The time passed since the last update
     */
    @Override
    public void specialUpdate(double time) {
        super.specialUpdate(time);
        LinkedList<Particle> liveParticles = new LinkedList<Particle>();
        for(Particle particle : particles) {
            particle.update(time);
            if(particle.getLifeTime() > 0.0) {
                liveParticles.add(particle);
            }
        }
        particles = liveParticles;
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[0];
    }
}
