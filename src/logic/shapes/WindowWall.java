package logic.shapes;

import graphics.GraphicsManager;
import graphics.shapes.WindowWallRenderer;
import logic.Dimension;
import logic.Position;

/**
 * Represents a wall with a window in it
 */
public class WindowWall extends HoleWall {

    private Window window;

    public WindowWall(Position position, Dimension dimension,
                      Position holeRelativePosition, Dimension holeDimension,
                      Position windowRelativePosition, Dimension windowDimension,
                      GraphicsManager graphicsManager) {
        super(position, dimension, holeRelativePosition, holeDimension, graphicsManager);
        window = new Window(position.add(windowRelativePosition), windowDimension, graphicsManager);
        renderer = new WindowWallRenderer(this, graphicsManager);
    }

    public Window getWindow() {
        return window;
    }

}
