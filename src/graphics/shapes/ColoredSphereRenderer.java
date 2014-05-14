package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.shapes.MovableSphere;
import logic.shapes.StaticSphere;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.awt.*;

/**
 * Represents a {@link StaticSphere} or {@link MovableSphere} rendered as it is covered with a color
 */
public class ColoredSphereRenderer extends AbstractRenderer {

    /**
     * The color used to render the {@link logic.shapes.StaticSphere} or {@link logic.shapes.MovableSphere}
     */
    private Color color;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public ColoredSphereRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, Color color) {
        super(renderedObject, graphicsManager);
        this.color = color;
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        GLU glu = graphicsManager.getGluObject();
        double radius;
        if(renderedObject.isMovable()) {
            radius = ((MovableSphere) renderedObject).getRadius();
        } else {
            radius = ((StaticSphere) renderedObject).getRadius();
        }
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glPushMatrix(); //remember current matrix
            gl.glTranslated(renderedObject.getPosition().getX(), renderedObject.getPosition().getY(), renderedObject.getPosition().getZ());
            gl.glColor3d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);
            GLUquadric sphere = glu.gluNewQuadric();
            // Draw sphere (possible styles: FILL, LINE, POINT).
            glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);
            glu.gluQuadricNormals(sphere, GLU.GLU_FLAT);
            glu.gluQuadricOrientation(sphere, GLU.GLU_OUTSIDE);
            final int slices = 65;
            final int stacks = 65;
            glu.gluSphere(sphere, radius, slices, stacks);
            glu.gluDeleteQuadric(sphere);
        gl.glPopMatrix(); //restore matrix
    }

}
