package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.Position;
import logic.shapes.Quad;

import javax.media.opengl.GL2;
import java.awt.*;

/**
 * Represents a quad rendered as it is covered with a color
 */
public class ColoredQuadRenderer extends AbstractRenderer {

    /**
     * The color used to render the {@link Quad}
     */
    private Color color;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public ColoredQuadRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, Color color) {
        super(renderedObject, graphicsManager);
        this.color = color;
    }

    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        Quad quadObject = (Quad) renderedObject;
        Position[] vertices = quadObject.getVertices();

        gl.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
        gl.glBegin(GL2.GL_QUADS);
            for(int i = 0; i < 4; i++) {
                gl.glVertex3d(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ());
            }
        gl.glEnd();
    }

}
