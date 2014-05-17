package graphics.shapes;

import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.WindowWall;

public class WindowWallRenderer extends HoleWallRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public WindowWallRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        WindowWall windowWall = (WindowWall) renderedObject;
//        GL2 gl = graphicsManager.getGlObject();
//        gl.glDisable(GL2.GL_DEPTH_TEST);
//        windowWall.getWindow().getRenderer().render();
//        gl.glEnable(GL2.GL_DEPTH_TEST);

        super.render();
    }
}
