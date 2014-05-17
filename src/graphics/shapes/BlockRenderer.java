package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.Block;

public class BlockRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public BlockRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        Block block = (Block) renderedObject;

        for(AbstractObject object : block.getObjectList())
            object.getRenderer().render();
    }
}
