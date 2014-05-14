package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ColoredQuadRenderer;
import graphics.shapes.TexturedSphereRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractMovableObject;
import logic.Position;

/**
 * Represents a sphere shape that can move
 */
public class MovableSphere extends AbstractMovableObject {

    private double radius;

    public MovableSphere(Position position, double direction, double speed, double acceleration, ShapeSurfaceType surfaceType, double radius, GraphicsManager graphicsManager) {
        super(position, direction, speed, acceleration);
        this.radius = radius;
        if(surfaceType.isTextured()) {
            renderer = new TexturedSphereRenderer(this, graphicsManager, surfaceType.getTextureHandler());
        } else {
            renderer = new ColoredQuadRenderer(this, graphicsManager, surfaceType.getColor());
        }
    }

    public MovableSphere(Position position, ShapeSurfaceType surfaceType, double radius, GraphicsManager graphicsManager) {
        this(position, 0, 0, -2e-4, surfaceType, radius, graphicsManager);
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Notifies this object that it collided with a movable object.
     * It updates this object's state depending on the movable object's speed and direction
     *
     * @param movableObject The other object that this object collided with
     */
    @Override
    protected void collidedWithMovableObject(AbstractMovableObject movableObject) {
        super.collidedWithMovableObject(movableObject);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX() - radius, position.getY() - radius, position.getZ() - radius,
                        2 * radius, 2 * radius, 2 * radius)
        };
    }

}
