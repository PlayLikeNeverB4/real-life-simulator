package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.MovableParallelepiped;
import logic.shapes.StaticParallelepiped;
import logic.shapes.Quad;

/**
* This class renders a component of a game world object that can be represented as a parallelepiped
*/
public class ParallelepipedRenderer extends AbstractRenderer {

    /**
     *
     * @param renderedObject    The {@link logic.shapes.StaticParallelepiped} that will be rendered on the screen
     * @param graphicsManager   The {@link graphics.GraphicsManager} which manages the renderer of parallelepiped
     */
    public ParallelepipedRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Quad[] sides = null;
        if(renderedObject.isMovable()) {
            sides = ((MovableParallelepiped) renderedObject).getSides();
        } else {
            sides = ((StaticParallelepiped) renderedObject).getSides();
        }
        for(int i = 0; i < 6; i++) {
            sides[i].getRenderer().render();
        }
    }

}
