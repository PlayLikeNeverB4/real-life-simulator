package graphics;

import logic.AbstractObject;
import logic.FirePlace;

public class FirePlaceRenderer extends AbstractRenderer {
    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public FirePlaceRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        FirePlace firePlace = (FirePlace) renderedObject;
        firePlace.getFireParticleSource().getRenderer().render();
        firePlace.getPlace().getRenderer().render();
    }
}
