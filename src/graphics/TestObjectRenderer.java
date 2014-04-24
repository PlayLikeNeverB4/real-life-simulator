package graphics;

import logic.AbstractObject;
import logic.TestObject;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class TestObjectRenderer extends AbstractRenderer {

    public TestObjectRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        TestObject testObject = (TestObject) renderedObject;
        GL2 gl = graphicsManager.getGlObject();
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3d(testObject.posOrigin.getX(), testObject.posOrigin.getY(), testObject.posOrigin.getZ());
        gl.glColor3f(0, 1, 0);
        gl.glVertex3d(testObject.posUp.getX(), testObject.posUp.getY(), testObject.posUp.getZ());
        gl.glColor3f(0, 0, 1);
        gl.glVertex3d(testObject.posBack.getX(), testObject.posBack.getY(), testObject.posBack.getZ());
        gl.glEnd();
    }

}
