package graphics;

import logic.MainCharacter;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Represents the game camera
 */
public class Camera {
    /**
     * The main character which the camera follows
     */
    private MainCharacter mainCharacter;

    /**
     * The graphics manager of the camera
     */
    private GraphicsManager graphicsManager;

    public Camera(MainCharacter mainCharacter, GraphicsManager graphicsManager) {
        this.mainCharacter = mainCharacter;
        this.graphicsManager = graphicsManager;
    }

    public void setItself() {
        GL2 gl = graphicsManager.getGlObject();
        GLU glu = graphicsManager.getGluObject();

        // Change to projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Set the camera
        float widthHeightRatio = (float) graphicsManager.getMainWindow().getWidth() / (float) graphicsManager.getMainWindow().getHeight();
        glu.gluPerspective(90, widthHeightRatio, 0.1, 2000);
        glu.gluLookAt(0, -10, 0, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}