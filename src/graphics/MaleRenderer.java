package graphics;

import logic.AbstractObject;
import logic.AbstractPerson;
import logic.shapes.BodyMember;
import logic.utils.GeometryUtils;

import javax.media.opengl.GL2;

/**
 * Renders a male person
 */
public class MaleRenderer extends AbstractPersonRenderer {

    public MaleRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        AbstractPerson abstractPerson = (AbstractPerson)renderedObject;
        GL2 gl = graphicsManager.getGlObject();
        double direction = GeometryUtils.normalizeAngle(abstractPerson.getWatchingDirection() - Math.PI / 2);
        double angle = 360.0 * direction / (2.0 * Math.PI);

        gl.glPushMatrix();
        gl.glTranslated(abstractPerson.getPosition().getX() + abstractPerson.getDimension().getX() / 2,
                abstractPerson.getPosition().getY() + abstractPerson.getDimension().getY() / 2,
                abstractPerson.getPosition().getZ() + abstractPerson.getDimension().getZ() / 2);
        gl.glRotated(angle, 0, 0, 1);
        gl.glTranslated(-abstractPerson.getPosition().getX() - abstractPerson.getDimension().getX() / 2,
                -abstractPerson.getPosition().getY() - abstractPerson.getDimension().getY() / 2,
                -abstractPerson.getPosition().getZ() - abstractPerson.getDimension().getZ() / 2);

        // rendering the middle part of the person's body (abdomen)
        abstractPerson.getBody().getRenderer().render();

        // rendering the members of the person's body (leg and arms)
        BodyMember[] bodyMembers = abstractPerson.getBodyMembers();
        for(int i = 0; i < bodyMembers.length; i++) {
            bodyMembers[i].getRenderer().render();
        }

        // rendering the head of the person's body
        abstractPerson.getHead().getRenderer().render();

        gl.glPopMatrix();
    }

}