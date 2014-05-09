package graphics;

import logic.AbstractObject;
import logic.Billboard;
import logic.Position;
import logic.shapes.Quad;
import logic.shapes.ShapeSurfaceType;

import javax.media.opengl.GL2;

public class BillboardRenderer extends AbstractRenderer {

    /**
     * An array of tree textures that can be used to render billboards
     */
    private static TextureHandler[] treeTextures;

    /**
     * The selected texture for rendering this billboard
     */
    private TextureHandler textureHandler;

    public static TextureHandler getTreeTexture(int textureIdx) {
        return treeTextures[textureIdx];
    }

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public BillboardRenderer(AbstractObject renderedObject, TextureHandler textureHandler, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
        this.textureHandler = textureHandler;
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        treeTextures = new TextureHandler[2];
        treeTextures[0] = new TextureHandler(pathToDir + "billboard_tree0.png", graphicsManager);
        treeTextures[1] = new TextureHandler(pathToDir + "billboard_tree1.png", graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Billboard billboard = (Billboard) renderedObject;
        GL2 gl = graphicsManager.getGlObject();

        Position[] vertices = billboard.getVertices();
        Quad quad = new Quad(vertices, graphicsManager, new ShapeSurfaceType(textureHandler));

        gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
        quad.getRenderer().render();
        gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
    }
}
