package graphics.shapes;

import graphics.GraphicsManager;
import graphics.TextureLoader;
import logic.*;
import logic.Dimension;
import logic.shapes.Alley;
import logic.shapes.Quad;
import logic.shapes.ShapeSurfaceType;

import javax.media.opengl.GL2;
import java.awt.*;

public class AlleyRenderer extends ColoredQuadRenderer {
    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public AlleyRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager, Color.GRAY);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Alley alley = (Alley) renderedObject;
        GL2 gl = graphicsManager.getGlObject();
        gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
        new Quad(alley.getVertices(), graphicsManager, new ShapeSurfaceType(TextureLoader.alley, new Dimension(50, 50))).getRenderer().render();
        gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
    }
}
