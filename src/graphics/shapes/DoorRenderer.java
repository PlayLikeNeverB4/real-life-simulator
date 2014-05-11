package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.Position;
import logic.shapes.Door;
import logic.shapes.Quad;

import javax.media.opengl.GL2;

public class DoorRenderer extends AbstractRenderer {
    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public DoorRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        Door door = (Door) renderedObject;

        Position point = door.getAnchor();
        double angle = door.getRelativeAngle();
        if(door.isOpened()) {
            if(door.isFront())
                angle -= 90;
            else
                angle += 90;
        }

        for(Quad quad : door.getSides()) {
            gl.glPushMatrix();
            gl.glTranslated(point.getX(), point.getY(), 0);
            gl.glRotated(angle, 0.0, 0.0, 1.0);
            gl.glTranslated(-point.getX(), -point.getY(), 0);

            quad.getRenderer().render();

            gl.glPopMatrix();
        }
    }
}
