package logic;

import graphics.GraphicsManager;

import java.awt.*;

/**
 * Source for fire particles
 */
public class FireParticleSource extends ParticleSource {

    /**
     * The graphics manager used for rendering the particles created by this source
     */
    GraphicsManager graphicsManager;

    public FireParticleSource(Position position, GraphicsManager graphicsManager) {
        super(position, graphicsManager);
        this.graphicsManager = graphicsManager;
    }

    /**
     * Starts the fire by creating the needed particles.
     */
    public void start() {
        // The lower (yellow) part of the fire
        for(int i = 0; i < 200; i++) {
            Position currentPosition = position.add(new Position(Math.random() * 20, Math.random() * 20, 0));
            particles.add(new FireParticle(currentPosition, 40, Color.YELLOW, graphicsManager));
        }

        // The middle (red) part of the fire
        for(int i = 0; i < 100; i++) {
            Position currentPosition = position.add(new Position(Math.random() * 20, Math.random() * 20, 15));
            particles.add(new FireParticle(currentPosition, 40, Color.RED, graphicsManager));
        }

        // The upper part of the fire (smoke)
        for(int i = 0; i < 200; i++) {
            Position currentPosition = position.add(new Position(Math.random() * 20, Math.random() * 20, 40 + Math.random() * 70));
            particles.add(new SmokeParticle(currentPosition, Color.GRAY, graphicsManager));
        }
    }

    /**
     * Stops the fire by destroying the particles.
     */
    public void stop() {
        particles.clear();
    }

    /**
     * Updates the particles.
     *
     * @param time The time passed since the last update
     */
    @Override
    public void specialUpdate(double time) {
        for(Particle particle : particles) {
            particle.update(time);
            if(particle.getLifeTime() < 0.0) { // if the particle is dead revive it
                particle.reset();
            }
        }
    }
}
