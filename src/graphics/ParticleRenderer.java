package graphics;

import logic.AbstractObject;
import logic.Particle;
import logic.Position;
import logic.shapes.MovableSphere;
import logic.shapes.ShapeSurfaceType;

import javax.media.opengl.GL2;
import java.awt.*;

/**
 * Renders a single particle
 */
public class ParticleRenderer extends AbstractRenderer {

    int numberOfSlices;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public ParticleRenderer(AbstractObject renderedObject, int numberOfSlices, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
        this.numberOfSlices = numberOfSlices;
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Particle particle = (Particle) renderedObject;
        GL2 gl = graphicsManager.getGlObject();

        Position position = particle.getPosition();
        Color color = particle.getColor();
        new MovableSphere(new Position(position), 0, 0, 0, new ShapeSurfaceType(color), 4, numberOfSlices, graphicsManager).getRenderer().render();
    }
}
