package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.Dimension;
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
     * The dimension of texture in the game world; If the object is larger it will be repeated
     */
    private Dimension textureDimension;


    /**
     * @param renderedObject   The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager  The {@link graphics.GraphicsManager} which manages the graphics for rendering
     * @param textureHandler   The texture used for rendering
     */
    public TexturedQuadRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, TextureHandler textureHandler) {
        this(renderedObject, graphicsManager, textureHandler, null);
    }

    /**
     * @param renderedObject     The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager    The {@link graphics.GraphicsManager} which manages the graphics for rendering
     * @param textureHandler     The texture used for rendering
     * @param textureDimension   The dimension of texture in the game world; If the object is larger it will be repeated
     */
    public TexturedQuadRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, TextureHandler textureHandler, Dimension textureDimension) {
        super(renderedObject, graphicsManager);
        this.textureHandler = textureHandler;
        this.textureDimension = textureDimension;
    }

    private Dimension computeQuadDimension() {
        Quad quad = (Quad) renderedObject;
        Position[] vertices = quad.getVertices();
        double x, y;
        if(vertices[0].getX() != vertices[1].getX())
            x = Math.abs(vertices[0].getX() - vertices[1].getX());
        else if(vertices[0].getY() != vertices[1].getY())
            x = Math.abs(vertices[0].getY() - vertices[1].getY());
        else
            x = Math.abs(vertices[0].getZ() - vertices[1].getZ());

        if(vertices[1].getX() != vertices[2].getX())
            y = Math.abs(vertices[1].getX() - vertices[2].getX());
        else if(vertices[1].getY() != vertices[2].getY())
            y = Math.abs(vertices[1].getY() - vertices[2].getY());
        else
            y = Math.abs(vertices[1].getZ() - vertices[2].getZ());

        return new Dimension(x, y, 0);
    }

    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        Quad quadObject = (Quad) renderedObject;
        Position[] vertices = quadObject.getVertices();

        if(this.textureDimension == null)
            this.textureDimension = computeQuadDimension();

        Dimension quadDimension = computeQuadDimension();
        double dx = quadDimension.getX();
        double dy = quadDimension.getY();
        double dimX = textureDimension.getX();
        double dimY = textureDimension.getY();

        // Needs auto-correction
        if(dimX / dimY < 0.2 || dimY / dimX < 0.2) {
            dimX = Math.max(dimX, 50);
            dimY = Math.max(dimY, 50);
        }

        double xScale = dx / dimX;
        double yScale = dy / dimY;

        TextureHandler.enableTexturing(gl);
        textureHandler.bind();
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());
            gl.glTexCoord2d(xScale, 0); // lower right
            gl.glVertex3d(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());
            gl.glTexCoord2d(xScale, yScale); // upper right
            gl.glVertex3d(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());
            gl.glTexCoord2d(0, yScale); // upper right
            gl.glVertex3d(vertices[3].getX(), vertices[3].getY(), vertices[3].getZ());
        gl.glEnd();
        TextureHandler.disableTexturing(gl);
    }

}
