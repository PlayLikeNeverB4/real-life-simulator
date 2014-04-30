package logic;

import graphics.Camera;
import graphics.GraphicsManager;

/**
 * Refers to a movement of the mouse
 */
public class MouseMoveEvent extends AbstractEvent {

    /**
     * The number of pixels moved on the horizontal axis
     */
    private double deltaX;

    /**
     * The number of pixels moved on the vertical axis
     */
    private double deltaY;

    /**
     * The GraphicsManager which this event applies to
     */
    private GraphicsManager graphicsManager;

    /**
     * The main character which this event updates
     */
    protected MainCharacter mainCharacter;

    public MouseMoveEvent(double deltaX, double deltaY, MainCharacter mainCharacter, GraphicsManager graphicsManager) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.graphicsManager = graphicsManager;
        this.mainCharacter = mainCharacter;
    }

    /**
     * Handler for this event
     */
    @Override
    public void handle() {
        Camera camera = graphicsManager.getCamera();

        // x axis
        double deltaXAngle = convertHorizontalPixelDistanceToRadians(deltaX);
        double nextXAngle = mainCharacter.getDirection() + deltaXAngle;
        nextXAngle = GeometryUtils.normalizeAngle(nextXAngle);
        mainCharacter.setDirection(nextXAngle);

        // y axis
        double deltaYAngle = convertVerticalPixelDistanceToRadians(deltaY);
        double nextYAngle = camera.getVerticalDirection() + deltaYAngle;
        double lowerBound = -Math.PI / 2 + 0.01;
        double upperBound = Math.PI / 2 - 0.01;
        nextYAngle = Math.max(lowerBound, nextYAngle);
        nextYAngle = Math.min(upperBound, nextYAngle);
        camera.setVerticalDirection(nextYAngle);

    }

    /**
     * Converts the horizontal distance in pixels that the mouse moved to the corresponding angle difference
     * @param delta Signed horizontal distance in pixels that the mouse moved
     * @return The angle difference in radians
     */
    private static double convertHorizontalPixelDistanceToRadians(double delta) {
        return -Math.PI * delta / 1000;
    }

    /**
     * Converts the vertical distance in pixels that the mouse moved to the corresponding angle difference
     * @param delta Signed vertical distance in pixels that the mouse moved
     * @return The angle difference in radians
     */
    private static double convertVerticalPixelDistanceToRadians(double delta) {
        return -Math.PI * delta / 500;
    }
}
