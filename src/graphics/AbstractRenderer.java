package graphics;

import logic.AbstractObject;

/**
 * This class manages the rendering of all of the objects
 */
public abstract class AbstractRenderer {

    /**
     * The {@link AbstractObject} that will be rendered on the screen
     */
    protected AbstractObject renderedObject;

    /**
     * The {@link GraphicsManager} which manages the graphics for rendering the game world object
     */
    protected GraphicsManager graphicsManager;

    /**
     * This method renders the object
     */
    public abstract void render();

    /**
     *
     * @param renderedObject    The {@link AbstractObject} that will be rendered on the screen
     * @param graphicsManager   The {@link GraphicsManager} which manages the graphics for rendering
     */
    protected AbstractRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        this.renderedObject = renderedObject;
        this.graphicsManager = graphicsManager;
    }

}