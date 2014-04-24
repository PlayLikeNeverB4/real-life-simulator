package graphics;

import logic.AbstractObject;

public abstract class AbstractPersonRenderer extends AbstractRenderer {

    protected AbstractPersonRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

}