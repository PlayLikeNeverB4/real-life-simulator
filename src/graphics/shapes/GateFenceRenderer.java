package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.Fence;
import logic.shapes.GateFence;

/**
 * Renders a {@link GateFence}
 */
public class GateFenceRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public GateFenceRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Fence[] fences = ((GateFence)renderedObject).getFences();
        for(int i = 0; i < 2; i++) {
            fences[i].getRenderer().render();
        }
    }

}
