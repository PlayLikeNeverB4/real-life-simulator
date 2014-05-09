package logic;


import graphics.BillboardRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import javafx.geometry.BoundingBox;
import logic.utils.GeometryUtils;

/**
 * Represents a static billboard
 */
public class Billboard extends AbstractStaticObject {

    /**
     * The dimension of the billboard
     */
    Dimension dimension;

    public Billboard(Position position, double direction, Dimension dimension, TextureHandler textureHandler, GraphicsManager graphicsManager) {
        super(position);
        this.direction = direction;
        this.dimension = dimension;
        renderer = new BillboardRenderer(this, textureHandler, graphicsManager);
    }

    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Computes the position of the billboard's 4 vertices
     * @return An array of 4 vertices
     */
    public Position[] getVertices() {
        Position[] vertices = new Position[4];
        double aAngle = GeometryUtils.normalizeAngle(direction);
        vertices[0] = position.move(aAngle, dimension.getWidth() / 2.0);
        double bAngle = GeometryUtils.normalizeAngle(direction + Math.PI);
        vertices[1] = position.move(bAngle, dimension.getWidth() / 2.0);
        vertices[2] = vertices[1].add(new Position(0, 0, dimension.getHeight()));
        vertices[3] = vertices[0].add(new Position(0, 0, dimension.getHeight()));
        return vertices;
    }

    /**
     * Computes and returns an array containing the axis aligned bounding boxes that form of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return null;
    }
}
