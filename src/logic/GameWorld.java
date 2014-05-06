package logic;

import graphics.GameWorldRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
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
        super(new Position(0, 0, 0));
        objectList = new LinkedList<AbstractObject>();
        movableObjectList = new LinkedList<AbstractMovableObject>();
        this.renderer = new GameWorldRenderer(this, graphicsManager);
        mainCharacter = new MainCharacter(new Position(500, 500, 0), graphicsManager, 0.2);
        dimension = new Dimension(1000, 1000, 500);     
    }

    /**
     * This method creates objects and adds them to the game world
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    public void initializeGameWorld(GraphicsManager graphicsManager) {
        dimension = new Dimension(1000, 1000, 500);
        addMovableObject(mainCharacter);
        ShapeSurfaceType[] shapeSurfaceTypes = new ShapeSurfaceType[6];
        shapeSurfaceTypes[0] = new ShapeSurfaceType(new TextureHandler("res/textures/grass.png", graphicsManager, false));
        shapeSurfaceTypes[1] = new ShapeSurfaceType(new Color(0, 255, 100));
        shapeSurfaceTypes[2] = new ShapeSurfaceType(new Color(233, 56, 0));
        shapeSurfaceTypes[3] = new ShapeSurfaceType(new Color(123, 34, 200));
        shapeSurfaceTypes[4] = new ShapeSurfaceType(new Color(255, 0, 0));
//        shapeSurfaceTypes[5] = new ShapeSurfaceType(new TextureHandler("res/textures/sky.png", graphicsManager, false));
//        shapeSurfaceTypes[4] = new ShapeSurfaceType(new Color(123, 134, 200));
        shapeSurfaceTypes[5] = new ShapeSurfaceType(new Color(3, 78, 10));
        addObject(new StaticParallelepiped(new Position(700, 600, 30), new Dimension(30, 30, 30), shapeSurfaceTypes, graphicsManager));
        addObject(new MovableParallelepiped(new Position(100, 800, 0), new Dimension(100, 20, 70), shapeSurfaceTypes, graphicsManager));
        addObject(new StaticParallelepiped(new Position(800, 200, 50), new Dimension(50, 100, 100), shapeSurfaceTypes, graphicsManager));
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

    /**
     * Computes the axis aligned bounding box of this object
     */
    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }
}