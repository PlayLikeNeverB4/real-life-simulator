package graphics.shapes;

import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.DoorWall;

public class DoorWallRenderer extends HoleWallRenderer {
    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public DoorWallRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        super.render();

        DoorWall doorWall = (DoorWall) renderedObject;
        if(doorWall.getDoor() != null) // walls with holes in them (no door)
            doorWall.getDoor().getRenderer().render();
    }
}
