package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ColoredQuadRenderer;
import graphics.shapes.TexturedQuadRenderer;
import javafx.geometry.BoundingBox;
import logic.AbstractStaticObject;
import logic.Position;

/**
 * Rectangle in 3D space
 */
public class Quad extends AbstractStaticObject {

    /**
     * An array of the rectangle's vertices
     */
    private Position[] vertices;

    public Quad(Position[] vertices, GraphicsManager graphicsManager, ShapeSurfaceType surfaceType) {
        super(vertices[0]);
        this.vertices = vertices;
        if(surfaceType.isTextured()) {
            renderer = new TexturedQuadRenderer(this, graphicsManager, surfaceType.getTextureHandler(), surfaceType.getTextureDimension());
        } else {
            renderer = new ColoredQuadRenderer(this, graphicsManager, surfaceType.getColor());
        }
    }

    public Position[] getVertices() {
        return vertices;
    }

    public Position computeMiddle() {
        double x = 0.0;
        double y = 0.0;
        for(Position pos : vertices) {
            x += pos.getX();
            y += pos.getY();
        }
        x /= 4;
        y /= 4;
        return new Position(x, y);
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return null;
    }

}
