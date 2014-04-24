package graphics;

public abstract class AbstractRenderer {

    private IRenderable renderedObject;
    private GraphicsManager graphicsManager;
  
    public abstract void render();

    protected AbstractRenderer(IRenderable renderedObject, GraphicsManager graphicsManager) {
        this.renderedObject = renderedObject;
        this.graphicsManager = graphicsManager;
    }
}