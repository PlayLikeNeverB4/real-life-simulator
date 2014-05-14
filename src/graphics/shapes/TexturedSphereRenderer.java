package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.shapes.MovableSphere;
import logic.shapes.StaticSphere;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Represents a {@link StaticSphere} or {@link MovableSphere} rendered as it is covered with a texture
 */
public class TexturedSphereRenderer extends AbstractRenderer {

    /**
     * The texture used to render the {@link logic.shapes.StaticSphere} or the {@link logic.shapes.MovableSphere}
     */
    private TextureHandler textureHandler;

    /**
     * @param renderedObject     The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager    The {@link graphics.GraphicsManager} which manages the graphics for rendering
     * @param textureHandler     The texture used for rendering
     */
    public TexturedSphereRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager, TextureHandler textureHandler) {
        super(renderedObject, graphicsManager);
        this.textureHandler = textureHandler;
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

        gl.glPushMatrix(); //remember current matrix
            gl.glTranslated(renderedObject.getPosition().getX(), renderedObject.getPosition().getY(), renderedObject.getPosition().getZ());
            TextureHandler.enableTexturing(gl);
                textureHandler.bind();

                GLUquadric sphere = glu.gluNewQuadric();
                // Enabling texturing on the quadric.
                glu.gluQuadricTexture(sphere, true);

                // Draw sphere (possible styles: FILL, LINE, POINT).
                glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);
                glu.gluQuadricNormals(sphere, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(sphere, GLU.GLU_OUTSIDE);
                final int slices = 65;
                final int stacks = 65;
                glu.gluSphere(sphere, radius, slices, stacks);
                glu.gluDeleteQuadric(sphere);

            TextureHandler.disableTexturing(gl);
        gl.glPopMatrix(); //restore matrix
    }

}
