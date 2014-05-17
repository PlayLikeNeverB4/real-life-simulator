package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.Apartment;

public class ApartmentRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public ApartmentRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Apartment apartment = (Apartment) renderedObject;
        for(AbstractObject object : apartment.getObjectList())
            object.getRenderer().render();
    }
}
