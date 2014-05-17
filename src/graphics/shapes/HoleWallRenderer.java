package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.HoleWall;
import logic.shapes.StaticParallelepiped;

public class HoleWallRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public HoleWallRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        HoleWall holeWall = (HoleWall) renderedObject;
        for(StaticParallelepiped wall : holeWall.getWalls())
            wall.getRenderer().render();
    }
}
