package logic;

import graphics.GraphicsManager;
import graphics.ParallelepipedRenderer;

/**
 * This class represents a component of an game world object that can be represented as a parallelepiped
 */
public class Parallelepiped extends AbstractComponent {

    /**
     * The {@link Dimension} of the component (geometrical shape) of an object that is rendered
     */
    private Dimension dimension;

    /**
     *
     * @param position      The {@link Position} where the parallelepiped stays
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
