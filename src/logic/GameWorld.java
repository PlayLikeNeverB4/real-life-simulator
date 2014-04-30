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
     */
    public GameWorld(GraphicsManager graphicsManager) {
        objectList = new LinkedList<AbstractObject>();
        movableObjectList = new LinkedList<AbstractMovableObject>();
        this.renderer = new GameWorldRenderer(this, graphicsManager);
        initializeGameWorld(graphicsManager);
    }

    /**
     * This method creates objects and adds them to the game world
     */
    private void initializeGameWorld(GraphicsManager graphicsManager) {
        dimension = new Dimension(1000, 1000, 1000);
        mainCharacter = new MainCharacter(new Position(500, 500, 500), graphicsManager);
        addMovableObject(mainCharacter);
//        objectList.add(new TestObject(new Position(0, 0, 0), 7, graphicsManager));
//        gameWorld.getObjectList().add(new TestObject(new Position(5, 0, 0), 40, graphicsManager));
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