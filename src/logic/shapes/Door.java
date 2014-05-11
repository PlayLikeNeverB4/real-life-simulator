package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.DoorRenderer;
import logic.AbstractObject;
import logic.Dimension;
import logic.Position;
import logic.utils.GeometryUtils;
import logic.utils.ParallelepipedUtils;

public class Door extends StaticParallelepiped {

    /**
     * The time that the door stays opened
     */
    private static final long DOOR_CLOSE_TIME = 3;

    /**
     * The speed of the door opening and closing
     */
    private static final double DOOR_TOGGLE_SPEED = 4;

    /**
     * The original position of the door
     */
    private Position originalPosition;

    /**
     * The original dimension of the door
     */
    private Dimension originalDimension;

    /**
     * true if the door is opened; false otherwise
     */
    private boolean opened;

    /**
     * The time at which the door opened the last time
     */
    private long openingTime;

    /**
     * The direction in which the door rotates
     */
    private int angleDirection;

    /**
     * The current relative angle of the door; it means how open the door is and in which direction
     */
    private double relativeAngle;

    /**
     * true if the door is opened towards the front; false if it is opened towards the back
     */
    private boolean front;

    /**
     * The {@link Position} of the anchor point of the door
     */
    private Position anchor;

    public Door(Position position, Dimension dimension, ShapeSurfaceType frontFaceType, ShapeSurfaceType backFaceType, ShapeSurfaceType sidesType, GraphicsManager graphicsManager, int numberOfRotations) {
        super(position, dimension, ParallelepipedUtils.createSurfaceTypeDoorArray(frontFaceType, backFaceType, sidesType), graphicsManager, numberOfRotations);
        renderer = new DoorRenderer(this, graphicsManager);
        originalPosition = new Position(this.position);
        originalDimension = new Dimension(this.dimension);
        anchor = new Position(position);
        opened = false;
    }

    public Door(Position position, Dimension dimension, ShapeSurfaceType surfaceType, GraphicsManager graphicsManager, int numberOfRotations) {
        this(position, dimension, surfaceType, surfaceType, surfaceType, graphicsManager, numberOfRotations);
    }

    public double getRelativeAngle() {
        return relativeAngle;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isFront() {
        return front;
    }

    public Position getAnchor() {
        return anchor;
    }

    /**
     * Opens the door
     */
    private void open() {
        if(front) {
            angleDirection = 1;
            rotate(1);
        }
        else {
            angleDirection = -1;
            rotate(-1);
        }

        recomputeQuads();
        opened = true;
        openingTime = System.currentTimeMillis();
    }

    /**
     * Closes the door
     */
    private void close() {
        // Set it back to the original position
        position = new Position(originalPosition);
        dimension = new Dimension(originalDimension);

        // Rotate it back
        if(front)
            rotation--;
        else
            rotation++;

        angleDirection = -angleDirection;
        opened = false;

        recomputeQuads();
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     *
     * @param abstractObject The other object that this object collided with
     */
    @Override
    protected void collisionSpecialEffects(AbstractObject abstractObject) {
        if(abstractObject.isMovable()) { // only movable objects can open it
            if(!opened) {
                // Check which way the door should open by compute the cross product
                front = GeometryUtils.crossProduct(position, position.add(new Position(dimension)), abstractObject.getPosition()) < 0;
                // Open it
                open();
            }
        }
    }

    /**
     * Checks if the door should close back and updates the door angle
     */
    @Override
    public void specialUpdate(double time) {
        if(opened) {
            long elapsedTime = System.currentTimeMillis() - openingTime;
            if(elapsedTime >= DOOR_CLOSE_TIME * 1000)
                close();
        }

        relativeAngle += angleDirection * DOOR_TOGGLE_SPEED;

        if(front) { // [0, 90]
            relativeAngle = Math.min(90, relativeAngle);
            relativeAngle = Math.max(0, relativeAngle);
        }
        else { // [-90, 0]
            relativeAngle = Math.min(0, relativeAngle);
            relativeAngle = Math.max(-90, relativeAngle);
        }
    }
}
