package graphics;

import logic.utils.GeometryUtils;
import logic.MainCharacter;
import logic.Position;

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

    /**
     * The distance from the camera to the center
     */
    private double centerDistance;

    /**
     * The vertical angle of the camera
     */
    public double verticalDirection;

    /**
     * The curent camera setting. (0 - far, 1 - near)
     */
    private int setting;

    /**
     * The relative horizontal distance to the main character
     */
    private static final int horizontalDistance[] = { 40, 1 };

    /**
     * The relative vertical distance to the main character
     */
    private static final int verticalDistance[] = { 50, 50 };

    public Camera(MainCharacter mainCharacter, GraphicsManager graphicsManager) {
        this.mainCharacter = mainCharacter;
        this.graphicsManager = graphicsManager;
        this.setting = 0;
    }

    public double getVerticalDirection() {
        return verticalDirection;
    }

    public void setVerticalDirection(double verticalDirection) {
        this.verticalDirection = verticalDirection;
    }

    /**
     * Toggles the camera setting
     */
    public void toggleSetting() {
        setting = 1 - setting;
    }

    /**
     * Sets up the camera for rendering
     */
    public void setItself() {
        GL2 gl = graphicsManager.getGlObject();
        GLU glu = graphicsManager.getGluObject();

        // Change to projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Compute the camera position
        centerDistance = 2 * horizontalDistance[setting];
        double horizontalDirection = mainCharacter.getWatchingDirection();
        Position cameraRelativePosition = GeometryUtils.computePointOnCircle(Math.PI + horizontalDirection, horizontalDistance[setting]);
        cameraRelativePosition.setZ(verticalDistance[setting]);
        Position cameraPosition = mainCharacter.getPosition().add(cameraRelativePosition);

        // Compute the center position
        Position centerVerticalPosition = GeometryUtils.computePointOnCircle(verticalDirection, centerDistance);
        double crtDistance = centerVerticalPosition.getX();
        double crtHeight = centerVerticalPosition.getY();
        Position centerPosition = GeometryUtils.computePointOnCircle(horizontalDirection, crtDistance);
        centerPosition.setZ(crtHeight);
        centerPosition = centerPosition.add(cameraPosition);

        // Set the camera
        float widthHeightRatio = (float) graphicsManager.getCanvas().getWidth() / (float) graphicsManager.getCanvas().getHeight();
        double maximumDistance = 1e6; // infinity
        glu.gluPerspective(100, widthHeightRatio, 0.01, maximumDistance);
        glu.gluLookAt(cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ(),
                      centerPosition.getX(), centerPosition.getY(), centerPosition.getZ(),
                      0, 0, 1);

        // Change back to model view matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}