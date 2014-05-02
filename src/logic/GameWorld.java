package logic;

import graphics.GameWorldRenderer;
import graphics.GraphicsManager;

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
        dimension = new Dimension(1000, 1000, 1000);
        mainCharacter = new MainCharacter(new Position(500, 500, 500), graphicsManager, 0.3);
        addObject(mainCharacter);
        addObject(new TestObject(new Position(10, 100, 100), 70, graphicsManager));
        addObject(new Parallelepiped(new Position(800, 200, 200), new Dimension(100, 130, 135), graphicsManager));
        addObject(new Parallelepiped(new Position(650, 750, 350), new Dimension(100, 100, 100), graphicsManager));
        addObject(new Parallelepiped(new Position(350, 450, 850), new Dimension(140, 111, 135), graphicsManager));
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