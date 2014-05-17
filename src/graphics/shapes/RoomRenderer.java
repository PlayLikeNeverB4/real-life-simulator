package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.AbstractStaticObject;
import logic.shapes.Room;

public class RoomRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public RoomRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        Room room = (Room) renderedObject;

        for(AbstractStaticObject wall : room.getWalls())
            wall.getRenderer().render();
        room.getFloor().getRenderer().render();
    }
}
