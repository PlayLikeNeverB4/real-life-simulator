package graphics;

import logic.AbstractObject;
import logic.Particle;
import logic.ParticleSource;

/**
 * Renders a group of particles
 */
public class ParticleSourceRenderer extends AbstractRenderer {
    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public ParticleSourceRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        ParticleSource particleSource = (ParticleSource) renderedObject;
        for(Particle particle : particleSource.getParticles())
            particle.getRenderer().render();
    }
}
