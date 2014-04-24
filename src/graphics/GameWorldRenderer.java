package graphics;

import logic.AbstractObject;
import logic.GameWorld;

public class GameWorldRenderer extends AbstractRenderer {

    public GameWorldRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        GameWorld gameWorld = (GameWorld) renderedObject;
        for(AbstractObject abstractObject : gameWorld.getObjectList()) {
            abstractObject.getRenderer().render();
        }
    }

}