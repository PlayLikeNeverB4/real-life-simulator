package logic;

import graphics.GraphicsManager;
import graphics.RoadRenderer;

public class Road extends AbstractStaticObject {

    private Dimension dimension;

    public Road(Position position, Dimension dimension, double direction, GraphicsManager graphicsManager) {
        super(position);
        this.dimension = dimension;
        this.direction = direction;
        renderer = new RoadRenderer(this, graphicsManager);
    }

    public Dimension getDimension() {
        return dimension;
    }

}