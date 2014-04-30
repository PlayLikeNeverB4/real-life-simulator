package graphics;

import logic.AbstractObject;
import logic.GameWorld;

import javax.media.opengl.GL2;

public class GameWorldRenderer extends AbstractRenderer {

    public GameWorldRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    @Override
    public void render() {
        GameWorld gameWorld = (GameWorld) renderedObject;
        renderWorldBox();
        for(AbstractObject abstractObject : gameWorld.getObjectList()) {
            abstractObject.getRenderer().render();
        }
    }

    private void renderWorldBox() {
        GL2 gl = graphicsManager.getGlObject();
        // TODO: take real dimensions
        double N = 1000;

        gl.glBegin(GL2.GL_QUADS);
            // front face
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(0, N, 0); // lower left
            gl.glVertex3d(N, N, 0); // lower right
            gl.glVertex3d(N, N, N); // upper right
            gl.glVertex3d(0, N, N); // upper left

            // right side face
            gl.glColor3d(1, 1, 0);
            gl.glVertex3d(N, N, 0); // lower left
            gl.glVertex3d(N, 0, 0); // lower right
            gl.glVertex3d(N, 0, N); // upper right
            gl.glVertex3d(N, N, N); // upper left

            // left side face
            gl.glColor3d(1, 1, 0);
            gl.glVertex3d(0, N, 0); // lower left
            gl.glVertex3d(0, 0, 0); // lower right
            gl.glVertex3d(0, 0, N); // upper right
            gl.glVertex3d(0, N, N); // upper left

            // back face
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(0, 0, 0); // lower left
            gl.glVertex3d(N, 0, 0); // lower right
            gl.glVertex3d(N, 0, N); // upper right
            gl.glVertex3d(0, 0, N); // upper left

            // top face
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(0, 0, N); // lower left
            gl.glVertex3d(N, 0, N); // lower right
            gl.glVertex3d(N, N, N); // upper right
            gl.glVertex3d(0, N, N); // upper left

            // bottom face
            gl.glColor3d(1, 1, 1);
            gl.glVertex3d(0, 0, 0); // lower left
            gl.glVertex3d(N, 0, 0); // lower right
            gl.glVertex3d(N, N, 0); // upper right
            gl.glVertex3d(0, N, 0); // upper left
        gl.glEnd();
    }

}