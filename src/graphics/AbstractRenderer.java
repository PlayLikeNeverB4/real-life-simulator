package graphics;

import logic.AbstractObject;

public abstract class AbstractRenderer {

    protected AbstractObject renderedObject;
    protected GraphicsManager graphicsManager;
  
    public abstract void render();

    protected AbstractRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        this.renderedObject = renderedObject;
        this.graphicsManager = graphicsManager;
    }
}