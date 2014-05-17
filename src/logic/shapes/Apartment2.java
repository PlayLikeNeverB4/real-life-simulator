package logic.shapes;

import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.Dimension;
import logic.Position;

import java.awt.*;
import java.util.LinkedList;

/**
 * Represents an apartment of type 2
 */
public class Apartment2 extends Apartment {

    public Apartment2(Position position, GraphicsManager graphicsManager) {
        super(position, graphicsManager);

        // Entrance door
        addObject(new DoorWall(position.add(new Position(-WALL_WIDTH, 0, FLOOR_HEIGHT)), new Dimension(7, HALL_WIDTH, WALL_HEIGHT),
                new Position(1, 10, 0), new Dimension(30, 5, 60), 1,
                new Position(1, 10, 0), new Dimension(5, 30, 60),
                graphicsManager));
        // Entrance hall
        addObject(new StaticParallelepiped(position.add(new Position(-HALL_LENGTH - WALL_WIDTH, 0, 0)), new Dimension(HALL_LENGTH, HALL_WIDTH, FLOOR_HEIGHT),
                0, new ShapeSurfaceType(Color.GRAY), graphicsManager));
//        // Kitchen
        addObject(new Room(position.add(new Position(-WALL_WIDTH - HALL_LENGTH, HALL_WIDTH, 0)), new Dimension(HALL_LENGTH, 150, WALL_HEIGHT),
                new Position(60, 0, 0), new Dimension(30, WALL_WIDTH, 60),
                0, new Position(60, 0, 0), new Dimension(30, WALL_WIDTH, 60), 0,
                new Position(60, 0, 20), new Dimension(40, WALL_WIDTH, 60),
                2, new Position(60, WALL_WIDTH, 20), new Dimension(40, 0, 60),
                graphicsManager));
        // Big room
        addObject(new Room(position.add(new Position(-WALL_WIDTH - HALL_LENGTH - 190, 0, 0)),
                new Dimension(190, 150 + HALL_WIDTH, WALL_HEIGHT),
                new Position(0, 5, 0), new Dimension(WALL_WIDTH, 30, 60),
                1, new Position(0, 5, 0), new Dimension(30, WALL_WIDTH, 60), 1,
                new Position(70, 0, 20), new Dimension(70, WALL_WIDTH, 50),
                2, new Position(70, 0, 20), new Dimension(70, 0, 50),
                graphicsManager));
        // Other hall
        addObject(new StaticParallelepiped(position.add(new Position(-MINI_HALL_B_LENGTH - MINI_HALL_LENGTH - HALL_LENGTH + 45, -HALL_WIDTH - WALL_WIDTH, 0)),
                new Dimension(WALL_WIDTH + HALL_LENGTH + MINI_HALL_LENGTH + MINI_HALL_B_LENGTH, HALL_WIDTH, FLOOR_HEIGHT),
                0, new ShapeSurfaceType(Color.GRAY), graphicsManager));
        // Wall separating the halls
        addObject(new HoleWall(position.add(new Position(-HALL_LENGTH - WALL_WIDTH - 190, -WALL_WIDTH, 0)),
                new Dimension(HALL_LENGTH + WALL_WIDTH + 190, WALL_WIDTH, WALL_HEIGHT),
                new Position(35 + 190, 0, FLOOR_HEIGHT),
                new Dimension(30, WALL_WIDTH, 60),
                graphicsManager));
        // Bath-room
        addObject(new Room(position.add(new Position(-WALL_WIDTH - HALL_LENGTH - MINI_HALL_LENGTH - 120, -HALL_WIDTH - WALL_WIDTH, 0)),
                new Dimension(120, HALL_WIDTH, WALL_HEIGHT),
                new Position(0, 5, 0), new Dimension(WALL_WIDTH, 30, 60),
                1, new Position(0, 5, 0), new Dimension(30, WALL_WIDTH, 60), 1,
                null, null,
                -1, null, null,
                graphicsManager));
        // Bedroom
        addObject(new Room(position.add(new Position(-WALL_WIDTH - HALL_LENGTH - 190, -HALL_WIDTH - WALL_WIDTH - 150)),
                new Dimension(190, 150, WALL_HEIGHT),
                new Position(190 - 50, 0, 0), new Dimension(30, WALL_WIDTH, 60),
                2, new Position(190 - 50, 0, 0), new Dimension(30, WALL_WIDTH, 60), 0,
                new Position(190 - 60 - 60, 0, 20), new Dimension(60, WALL_WIDTH, 50),
                0, new Position(190 - 60 - 60, 0, 20), new Dimension(60, 0, 50),
                graphicsManager));
        // Living room
        addObject(new Room(position.add(new Position(+MINI_HALL_B_LENGTH - 215, -HALL_WIDTH - WALL_WIDTH - 150)),
                new Dimension(215, 150, WALL_HEIGHT),
                new Position(80, 0, 0), new Dimension(30, WALL_WIDTH, 60),
                2, new Position(80, 0, 0), new Dimension(30, WALL_WIDTH, 60), 0,
                new Position(50, 0, 20), new Dimension(70, WALL_WIDTH, 50),
                0, new Position(50, 0, 20), new Dimension(70, 0, 50),
                graphicsManager));
    }

}
