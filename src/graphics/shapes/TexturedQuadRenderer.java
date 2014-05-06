package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.Position;
import logic.shapes.Quad;

import javax.media.opengl.GL2;

/**
 * Represents a quad rendered as it is covered with a texture
 */
public class TexturedQuadRenderer extends AbstractRenderer {

    /**
     * The texture used to render the {@link Quad}
     */
    private TextureHandler textureHandler;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public TexturedQuadRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, TextureHandler textureHandler) {
        super(renderedObject, graphicsManager);
        this.textureHandler = textureHandler;
    }

    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        Quad quadObject = (Quad) renderedObject;
        Position[] vertices = quadObject.getVertices();

        TextureHandler.enableTexturing(gl);
        textureHandler.bind();
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());
            gl.glTexCoord2d(1, 0); // lower right
            gl.glVertex3d(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());
            gl.glTexCoord2d(1, 1); // upper right
            gl.glVertex3d(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());
            gl.glTexCoord2d(0, 1); // upper right
            gl.glVertex3d(vertices[3].getX(), vertices[3].getY(), vertices[3].getZ());
        gl.glEnd();
        TextureHandler.disableTexturing(gl);
    }

}
