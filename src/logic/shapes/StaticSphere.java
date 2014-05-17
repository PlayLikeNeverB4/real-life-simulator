package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ColoredSphereRenderer;
import graphics.shapes.TexturedSphereRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;
import logic.Position;

/**
 * Represents a sphere shape that can not be moved
 */
public class StaticSphere extends AbstractStaticObject {

    private double radius;

    public StaticSphere(Position position, ShapeSurfaceType surfaceType, double radius, GraphicsManager graphicsManager) {
        super(position);
        this.radius = radius;
        if(surfaceType.isTextured()) {
            renderer = new TexturedSphereRenderer(this, graphicsManager, surfaceType.getTextureHandler());
        } else {
            renderer = new ColoredSphereRenderer(this, graphicsManager, surfaceType.getColor(), 10);
        }
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] {
                new BoundingBox(position.getX() - radius, position.getY() - radius, position.getZ() - radius,
                        2 * radius, 2 * radius, 2 * radius)
        };
    }

}
