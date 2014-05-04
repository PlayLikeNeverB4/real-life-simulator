package logic;

import graphics.GameWorldRenderer;
import graphics.GraphicsManager;
import logic.shapes.Parallelepiped;
import logic.shapes.Road;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the game world
 */
public class GameWorld extends AbstractObject {

    /**
     * The size of the game world
     */
    private Dimension dimension;

    /**
     * A list containing all of the objects inside the game world
     */
    private List<AbstractObject> objectList;

    /**
     * A list containing all of the movable objects inside the game world
     */
    private List<AbstractMovableObject> movableObjectList;

    /**
     * The main character of the game world
     */
    private MainCharacter mainCharacter;

    /**
     * This constructor initializes all of the containing fields of GameWorld class
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    public GameWorld(GraphicsManager graphicsManager) {
        objectList = new LinkedList<AbstractObject>();
        movableObjectList = new LinkedList<AbstractMovableObject>();
        this.renderer = new GameWorldRenderer(this, graphicsManager);
        initializeGameWorld(graphicsManager);
    }

    /**
     * This method creates objects and adds them to the game world
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    private void initializeGameWorld(GraphicsManager graphicsManager) {
        dimension = new Dimension(1000, 1000, 500);
        mainCharacter = new MainCharacter(new Position(500, 500, 0), graphicsManager, 0.2);
        addObject(mainCharacter);
        addObject(new Parallelepiped(new Position(700, 600, 30), new Dimension(30, 30, 30), graphicsManager));
        addObject(new Parallelepiped(new Position(100, 800, 0), new Dimension(100, 20, 70), graphicsManager));
        addObject(new Parallelepiped(new Position(800, 200, 50), new Dimension(50, 100, 100), graphicsManager));
        addObject(new Road(new Position(500, 100, 0.2), new Dimension(100, 800, 0), Math.PI / 2, graphicsManager));
    }

    /**
     * Adds an ordinary object to the game world
     * @param abstractObject The object to be added
     */
    private void addObject(AbstractObject abstractObject) {
        objectList.add(abstractObject);
    }

    /**
     * Adds a movable object to the game world
     * @param movableObject The object to be added
     */
    private void addMovableObject(AbstractMovableObject movableObject) {
        addObject(movableObject);
        movableObjectList.add(movableObject);
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

}