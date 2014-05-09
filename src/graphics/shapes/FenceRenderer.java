package graphics.shapes;

import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.shapes.*;

/**
 * Renders a {@link Fence}
 */
public class FenceRenderer extends ParallelepipedRenderer {

    public static TextureHandler[] textures;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public FenceRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        textures = new TextureHandler[2];
        textures[0] = new TextureHandler(pathToDir + "fenceWood.png", graphicsManager);
        textures[1] = new TextureHandler(pathToDir + "fenceRock.png", graphicsManager);

    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Quad[] sides = ((StaticParallelepiped) renderedObject).getSides();
        for(int i = 0; i < 6; i++) {
            sides[i].getRenderer().render();
        }
    }
}
