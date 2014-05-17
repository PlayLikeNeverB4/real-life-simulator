package logic;

import graphics.GraphicsManager;
import logic.utils.ParallelepipedUtils;

/**
 * Source for fun particles (the ones used for joy boxes)
 */
public class FunParticleSource extends ParticleSource {

    public FunParticleSource(Position position, int numberOfSlices, GraphicsManager graphicsManager) {
        super(position, graphicsManager);
        for(int i = 0; i < 100; i++)
            particles.add(new Particle(new Position(position), numberOfSlices, ParallelepipedUtils.createRNGColor(), graphicsManager));
    }

}
