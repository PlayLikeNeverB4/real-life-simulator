package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.ParallelepipedRenderer;
import logic.AbstractComponent;
import logic.Dimension;
import logic.Position;

/**
 * This class represents a component of an game world object that can be represented as a parallelepiped
 */
public class Parallelepiped extends AbstractComponent {

    /**
     * The {@link logic.Dimension} of the component (geometrical shape) of an object that is rendered
     */
    private Dimension dimension;

    /**
     *
     * @param position      The {@link logic.Position} where the parallelepiped stays
     * @param dimension     The {@link Dimension} of the parallelepiped
     * @param graphicsManager   The {@link GraphicsManager} which manages the renderer of parallelepiped
     */
    public Parallelepiped(Position position, Dimension dimension, GraphicsManager graphicsManager) {
        this.position = position;
        this.dimension = dimension;
        this.renderer = new ParallelepipedRenderer(this, graphicsManager);
    }

    public Dimension getDimension() {
        return dimension;
    }



}
