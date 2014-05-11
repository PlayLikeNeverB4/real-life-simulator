package logic;

import graphics.GameWorldRenderer;
import graphics.GraphicsManager;
import graphics.TextureLoader;
import javafx.geometry.BoundingBox;
import logic.shapes.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the game world
 */
public class GameWorld extends AbstractStaticObject {

    /**
     * The size of the game world
     */
    private Dimension dimension;

    /**
     * A list containing all of the touchable objects inside the game world except
     */
    private List<AbstractObject> objectList;

    /**
     * A list containing all of the movable objects inside the game world
     */
    private List<AbstractMovableObject> movableObjectList;

    /**
     * A list containing all of the untouchable objects inside the game world
     */
    private List<AbstractObject> untouchableObjectList;

    /**
     * The main character of the game world
     */
    private MainCharacter mainCharacter;

    /**
     * This constructor initializes all of the containing fields of GameWorld class
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    public GameWorld(GraphicsManager graphicsManager) {
        super(new Position(0, 0, 0));
        dimension = new Dimension(10000, 10000, 5000);
        objectList = new LinkedList<AbstractObject>();
        movableObjectList = new LinkedList<AbstractMovableObject>();
        untouchableObjectList = new LinkedList<AbstractObject>();
        this.renderer = new GameWorldRenderer(this, graphicsManager);
        mainCharacter = new MainCharacter(new Position(5000, 5000, 0.1), graphicsManager, 0.2);
    }

    /**
     * This method creates objects and adds them to the game world
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    public void initializeGameWorld(GraphicsManager graphicsManager) {
        addObject(new WorldBox(new Position(0, 0, 0), dimension));
        addMovableObject(mainCharacter);
        addObject(new StaticParallelepiped(new Position(5300, 5200, 0), new Dimension(100), new ShapeSurfaceType(Color.BLUE), graphicsManager));
        addObject(new Stairs(new Position(5100, 5300, 0), 3, 10, new Dimension(100, 20, 10), graphicsManager));
        addObject(new Stairs(new Position(5300, 5100, 0), 0, 5, new Dimension(100, 20, 20), graphicsManager));

        addObject(new PlayGround(new Position(5500, 4500, 0), 300, 2, graphicsManager));

        addObject(new Door(new Position(5200, 4700, 0), new Dimension(30, 5, 60),
                           new ShapeSurfaceType(TextureLoader.doorFront),
                           new ShapeSurfaceType(TextureLoader.doorBack),
                           new ShapeSurfaceType(new Color(241, 182, 107)),
                           graphicsManager, 1));
        addObject(new StaticParallelepiped(new Position(5200, 4600, 0), new Dimension(100, 5, 60),
                new ShapeSurfaceType(Color.GRAY), graphicsManager, 1));
        addObject(new StaticParallelepiped(new Position(5200, 4730, 0), new Dimension(100, 5, 60),
                new ShapeSurfaceType(Color.GRAY), graphicsManager, 1));

        addMovableObject(new MovableParallelepiped(new Position(4900, 4900, 0.1), new Dimension(50), new ShapeSurfaceType(Color.RED), graphicsManager));
        addMovableObject(new MovableParallelepiped(new Position(4900, 5200, 0.1), new Dimension(50), new ShapeSurfaceType(Color.RED), graphicsManager));

        generateForest(graphicsManager, 1000);

        addUntouchableObject(new Road(new Position(5000, 1000, 0.8), new Dimension(100, 8000, 0), Math.PI / 2, graphicsManager));
    }

    /**
     * Generates the forest surrounding the game world
     * @param margin The size of the forest
     */
    private void generateForest(GraphicsManager graphicsManager, double margin) {
        ForestGenerator forestGenerator = new ForestGenerator(this, graphicsManager);
        forestGenerator.generate(new Position(0, dimension.getY() - (margin + 1), 2), new Dimension(dimension.getX(), margin, 0), 0, 100);
        forestGenerator.generate(new Position(margin + 1, 0, 0), new Dimension(dimension.getX(), margin, 0), Math.PI / 2, 100);
        forestGenerator.generate(new Position(dimension.getX(), margin + 1, 0), new Dimension(dimension.getX(), margin, 0), Math.PI, 100);
        forestGenerator.generate(new Position(dimension.getX() - (margin + 1), dimension.getY(), 0), new Dimension(dimension.getX(), margin, 0), Math.PI * 3 / 2, 100);
    }

    /**
     * Adds an ordinary object to the game world
     * @param abstractObject The object to be added
     */
    public void addObject(AbstractObject abstractObject) {
        objectList.add(abstractObject);
    }

    /**
     * Adds a movable object to the game world
     * @param movableObject The object to be added
     */
    public void addMovableObject(AbstractMovableObject movableObject) {
        addObject(movableObject);
        movableObjectList.add(movableObject);
    }

    /**
     * Add an untouchable object to the game world
     * @param untouchableObject The object to be added
     */
    public void addUntouchableObject(AbstractObject untouchableObject) {
        untouchableObjectList.add(untouchableObject);
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public List<AbstractObject> getObjectList() {
        return objectList;
    }

    public List<AbstractMovableObject> getMovableObjectList() {
        return movableObjectList;
    }

    public List<AbstractObject> getUntouchableObjectList() {
        return untouchableObjectList;
    }

    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Updates the game world
     * @param time The amount of time passed since the last update
     */
    public void update(double time) {
        for(AbstractMovableObject movableObject : movableObjectList)
            movableObject.update(time);
    }

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox[] getBoundingBoxes() {
        return new BoundingBox[] { new BoundingBox(position.getX(), position.getY(), position.getZ(),
                                                   dimension.getX(), dimension.getY(), dimension.getZ())};
    }

}