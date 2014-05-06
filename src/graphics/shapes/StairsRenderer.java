package graphics.shapes;


import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.shapes.Stairs;
import logic.shapes.StaticParallelepiped;

/**
 * Renders a staircase
 */
public class StairsRenderer extends AbstractRenderer {

    /**
     * Texture for the steps
     */
    private static TextureHandler stepTexture;

    public static TextureHandler getStepTexture() {
        return stepTexture;
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        stepTexture = new TextureHandler(pathToDir + "step.png", graphicsManager);
    }

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public StairsRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Stairs stairs = (Stairs) renderedObject;
        for(StaticParallelepiped step : stairs.getSteps())
            step.getRenderer().render();
    }
}
