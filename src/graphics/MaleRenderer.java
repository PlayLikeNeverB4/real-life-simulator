package graphics;

import logic.AbstractObject;
import logic.AbstractPerson;

import javax.media.opengl.GL2;

public class MaleRenderer extends AbstractPersonRenderer {

    public MaleRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        AbstractPerson abstractPerson = (AbstractPerson)renderedObject;
        GL2 gl = graphicsManager.getGlObject();

        double x = abstractPerson.getPosition().getX();
        double y = abstractPerson.getPosition().getY();
        double z = abstractPerson.getPosition().getZ();

        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3d(0, 0, 0);
            gl.glVertex3d(x - 5, y, z);
            gl.glVertex3d(x - 5, y, z + 30);
            gl.glVertex3d(x + 5, y, z + 30);
            gl.glVertex3d(x + 5, y, z);
        gl.glEnd();
    }

}