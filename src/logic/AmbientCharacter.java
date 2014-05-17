package logic;

import graphics.GraphicsManager;
import graphics.MaleRenderer;

/**
 * Represents a character from the game world that makes the world seem populated by active people
 */
public class AmbientCharacter extends AbstractPerson {

    /**
     * The path that the character follows
     */
    private final Position[] path;

    /**
     * The current index from the path
     */
    private int currentIndex;

    /**
     * The direction of positions that the characters walks in
     */
    private int pathDirection;

    public AmbientCharacter(Position position, Dimension dimension, int pathDirection, double speed, Position[] path, GraphicsManager graphicsManager) {
        super(position, dimension, graphicsManager);
        this.pathDirection = pathDirection;
        this.speed = speed;
        this.path = path;

        // Find the closest position
        this.currentIndex = 0;
        for(int i = 1; i < path.length; i++)
            if(position.distanceTo(path[i]) < position.distanceTo(path[currentIndex]))
                currentIndex = i;

        renderer = new MaleRenderer(this, graphicsManager);
    }

    public AmbientCharacter(Position position, Dimension dimension, Position[] path, GraphicsManager graphicsManager) {
        this(position, dimension, generatePathDirection(), Math.random() * 0.1, path, graphicsManager);
    }

    /**
     * Generates and returns a random path direction
     */
    private static int generatePathDirection() {
        if(Math.random() > 0.5)
            return  1;
        else
            return -1;
    }

    /**
     * Updates the objects' position and speed considering its current speed, acceleration and direction
     *
     * @param time The amount of time passed since the last update
     */
    @Override
    public void update(double time) {
        super.update(time);

        // Maybe go towards the next target
        if(position.distanceTo(path[currentIndex]) < 5)
            currentIndex = (currentIndex + pathDirection + path.length) % path.length;

        // Update the character's direction
        double nextX = path[currentIndex].getX();
        double nextY = path[currentIndex].getY();
        this.direction = Math.atan2(nextY - position.getY(), nextX - position.getX());
    }

}