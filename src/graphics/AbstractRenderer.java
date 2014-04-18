package graphics;

public abstract class AbstractRenderer implements IRenderable {

    private IRenderable renderedObject;
    private GraphicsManager graphicsManager;
  
    public abstract void render();

}