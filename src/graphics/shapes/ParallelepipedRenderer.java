package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.Parallelepiped;

import javax.media.opengl.GL2;

/**
 * This class renders a component of a game world object that can be represented as a parallelepiped
 */
public class ParallelepipedRenderer extends AbstractRenderer {

    /**
     *
     * @param renderedObject    The {@link Parallelepiped} that will be rendered on the screen
     * @param graphicsManager   The {@link graphics.GraphicsManager} which manages the renderer of parallelepiped
     */
    public ParallelepipedRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        Parallelepiped parallelepiped = (Parallelepiped) renderedObject;
        GL2 gl = graphicsManager.getGlObject();
        double x, y, z, xLength, yWidth, zHeight;
        x =  parallelepiped.getPosition().getX();
        y = parallelepiped.getPosition().getY();
        z = parallelepiped.getPosition().getZ();
        xLength = x + parallelepiped.getDimension().getX();
        yWidth = y + parallelepiped.getDimension().getY();
        zHeight = z + parallelepiped.getDimension().getZ();

        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3f(1, 0, 0);
            // front face
            gl.glVertex3d(x, y, z);
            gl.glVertex3d(x, y, zHeight);
            gl.glVertex3d(xLength, y, zHeight);
            gl.glVertex3d(xLength, y, z);

            gl.glColor3f(0, 1, 0);
            // right face
            gl.glVertex3d(xLength, y, z);
            gl.glVertex3d(xLength, y, zHeight);
            gl.glVertex3d(xLength, yWidth, zHeight);
            gl.glVertex3d(xLength, yWidth, z);

            gl.glColor3f(1, 0, 1);
            // back face
            gl.glVertex3d(xLength, yWidth, z);
            gl.glVertex3d(xLength, yWidth ,zHeight);
            gl.glVertex3d(x, yWidth, zHeight);
            gl.glVertex3d(x, yWidth, z);

            gl.glColor3f(0, 0, 1);
            // left face
            gl.glVertex3d(x, yWidth, z);
            gl.glVertex3d(x, yWidth, zHeight);
            gl.glVertex3d(x, y, zHeight);
            gl.glVertex3d(x, y ,z);

            gl.glColor3f(0, 1, 1);
            // down face
            gl.glVertex3d(x, y, z);
            gl.glVertex3d(x, yWidth, z);
            gl.glVertex3d(xLength, yWidth, z);
            gl.glVertex3d(xLength, y ,z);

            gl.glColor3f(1, 1, 0);
            // up face
            gl.glVertex3d(x, y, zHeight);
            gl.glVertex3d(x, yWidth, zHeight);
            gl.glVertex3d(xLength, yWidth, zHeight);
            gl.glVertex3d(xLength, y, zHeight);
        gl.glEnd();
    }

}
