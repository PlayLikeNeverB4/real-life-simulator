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
            renderer = new TexturedQuadRenderer(this, graphicsManager, surfaceType.getTextureHandler());
        } else {
            renderer = new ColoredQuadRenderer(this, graphicsManager, surfaceType.getColor());
        }
    }

    public Position[] getVertices() {
        return vertices;
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        return null;
    }

}
