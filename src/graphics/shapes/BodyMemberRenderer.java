package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.Position;
import logic.shapes.BodyMember;
import logic.shapes.Quad;

import javax.media.opengl.GL2;

/**
 * Renders a body component (leg or arm) of a person
 */
public class BodyMemberRenderer extends AbstractRenderer {

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public BodyMemberRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        BodyMember bodyMember = (BodyMember) renderedObject;

        Position point = bodyMember.getAnchor();
        double angle = bodyMember.getRelativeAngle() * 360 / (2 * Math.PI);

        for(Quad quad : bodyMember.getSides()) {
            gl.glPushMatrix();

            gl.glTranslated(point.getX(), point.getY(), point.getZ());
            gl.glRotated(angle, 1.0, 0.0, 0.0);
            gl.glTranslated(-point.getX(), -point.getY(), -point.getZ());

            quad.getRenderer().render();

            gl.glPopMatrix();
        }
    }

}
