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
     * The width of an alley from the game world
     */
    public static double ALLEY_WIDTH = 40;
    /**
     * The z-coordinate of an alley from the game world
     */
    public static double ALLEY_HEIGHT = 0.4;

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
     * A list containing all of the fire places inside the game world
     */
    private List<FirePlace> firePlaces;

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
        firePlaces = new LinkedList<FirePlace>();
        this.renderer = new GameWorldRenderer(this, graphicsManager);
        mainCharacter = new MainCharacter(new Position(5000, 5000, 0.1), new Dimension(10, 5, 50), graphicsManager, 0.2);
    }

    /**
     * This method creates objects and adds them to the game world
     * @param graphicsManager The {@link GraphicsManager} which manages all of the rendering
     */
    public void initializeGameWorld(GraphicsManager graphicsManager) {
        // The world box
        addObject(new WorldBox(new Position(0, 0, 0), dimension));
        // The main character
        addMovableObject(mainCharacter);

        // The stairs that the kids play on
        addObject(new StaticParallelepiped(new Position(5400, 5200, 0), new Dimension(100), new ShapeSurfaceType(Color.BLUE), graphicsManager));
        addObject(new Stairs(new Position(5200, 5300, 0), 3, 10, new Dimension(100, 20, 10), graphicsManager));
        addObject(new Stairs(new Position(5400, 5100, 0), 0, 5, new Dimension(100, 20, 20), graphicsManager));

        // The playground
        addObject(new PlayGround(new Position(5500, 4500, 0), 300, 2, graphicsManager));

        // The block of apartments
        addObject(new Block(new Position(4000, 5000, 0), 2, graphicsManager));

        // The red boxes that can be moved
        addMovableObject(new MovableParallelepiped(new Position(4500, 4100, 0.1), new Dimension(50), new ShapeSurfaceType(Color.RED), graphicsManager));
        addMovableObject(new MovableParallelepiped(new Position(4500, 4400, 0.1), new Dimension(50), new ShapeSurfaceType(Color.RED), graphicsManager));

        // Generate the forest at the edge of the game world
        generateForest(graphicsManager, 1000);
        // Generate the fences at the edge of the game world
        generateGameWorldFences(graphicsManager, 3000, 35);

        // The big road
        addUntouchableObject(new Road(new Position(5000, 3000, 0.8), new Dimension(100, 4000, 0), Math.PI / 2, graphicsManager));

        // Alley surrounding the block
        addUntouchableObject(new Alley(new Position(3900, 4900, ALLEY_HEIGHT),
                                       new Dimension(1050, ALLEY_WIDTH, 0),
                                       graphicsManager));
        addUntouchableObject(new Alley(new Position(3900, 4900 + ALLEY_WIDTH, ALLEY_HEIGHT),
                                       new Dimension(ALLEY_WIDTH, 600, 0),
                                       graphicsManager));
        addUntouchableObject(new Alley(new Position(3900 + ALLEY_WIDTH, 4900 + 600, ALLEY_HEIGHT),
                                       new Dimension(1050, ALLEY_WIDTH, 0),
                                       graphicsManager));
        addUntouchableObject(new Alley(new Position(3900 + 1050, 4900, ALLEY_HEIGHT),
                                       new Dimension(ALLEY_WIDTH, 600, 0),
                                       graphicsManager));
        addUntouchableObject(new Alley(new Position(3900 + 545, 4900 + ALLEY_WIDTH, ALLEY_HEIGHT),
                                       new Dimension(100, 150, 0),
                                       graphicsManager));

        // Generate the walking pedestrians
        generateAmbientCharacters(graphicsManager);

        // Some fireworks
        addUntouchableObject(new FunParticleSource(new Position(5460, 5240, 200), 10, graphicsManager));
        addUntouchableObject(new FunParticleSource(new Position(5000, 5000, 40), 10, graphicsManager));

        // The fire places
        addFirePlace(new FirePlace(new Position(4700, 4600, 0), graphicsManager));
        addFirePlace(new FirePlace(new Position(4600, 4600, 0), graphicsManager));
        addFirePlace(new FirePlace(new Position(4500, 4600, 0), graphicsManager));

        // The sphere that can be moved
        double radius = 15;
        addMovableObject(new MovableSphere(new Position(5000, 4900, radius + 0.1), new ShapeSurfaceType(TextureLoader.sky), radius, graphicsManager));
    }

    /**
     * Generates people in the game world
     * @param graphicsManager The graphics manager used for rendering the people
     */
    private void generateAmbientCharacters(GraphicsManager graphicsManager) {
        // Walking pedestrians
        Position[] path = new Position[] {
                new Position(3924, 4920),
                new Position(3919, 5518),
                new Position(4970, 5516),
                new Position(4978, 4924)
        };
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4900, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4950, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4950, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4950, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4950, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3900, 4950, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3919, 5518, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3919, 5518, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(3919, 5518, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(4970, 5516, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(4970, 5516, 0.1), new Dimension(10, 5, 50), path, graphicsManager));
        addUntouchableObject(new AmbientCharacter(new Position(4970, 5516, 0.1), new Dimension(10, 5, 50), path, graphicsManager));

        // Children playing
        path = new Position[] {
                new Position(5164, 5236),
                new Position(5393, 5278),
                new Position(5466, 5279),
                new Position(5477, 5198),
                new Position(5383, 5201),
                new Position(5400, 5210),
                new Position(5393, 5278),
                new Position(5466, 5279),
                new Position(5477, 5198),
                new Position(5410, 5027),
        };
        addMovableObject(new AmbientCharacter(new Position(5164, 5236, 0.2), new Dimension(7, 3, 30), 1, 0.2, path, graphicsManager));
        addMovableObject(new AmbientCharacter(new Position(5100, 5236, 0.2), new Dimension(7, 3, 30), 1, 0.2, path, graphicsManager));
        addMovableObject(new AmbientCharacter(new Position(5000, 5236, 0.2), new Dimension(7, 3, 30), 1, 0.2, path, graphicsManager));
    }

    /**
     * Generates the forest surrounding the game world
     * @param margin The size of the forest
     */
    private void generateForest(GraphicsManager graphicsManager, double margin) {
        ForestGenerator forestGenerator = new ForestGenerator(this, graphicsManager);
        forestGenerator.generate(new Position(0, dimension.getY() - (margin + 1), 0), new Dimension(dimension.getX(), margin, 0), 0, 100);
        forestGenerator.generate(new Position(margin + 1, 0, 0), new Dimension(dimension.getX(), margin, 0), Math.PI / 2, 100);
        forestGenerator.generate(new Position(dimension.getX(), margin + 1, 0), new Dimension(dimension.getX(), margin, 0), Math.PI, 100);
        forestGenerator.generate(new Position(dimension.getX() - (margin + 1), dimension.getY(), 0), new Dimension(dimension.getX(), margin, 0), Math.PI * 3 / 2, 100);
    }

    /**
     * Generates the fences surrounding the game world
     * @param distanceFromWorldBoxMargin The distance from the margin of worldBox
     */
    private void generateGameWorldFences(GraphicsManager graphicsManager, double distanceFromWorldBoxMargin, double fenceWidth) {
        double scale = 20;
        Dimension dimensionFence = new Dimension(fenceWidth, (dimension.getY() - 2 * distanceFromWorldBoxMargin) - fenceWidth, fenceWidth);
        Dimension fenceTexture = new Dimension(dimensionFence.getY() / scale, dimensionFence.getX(), 0);
        addObject(new StaticParallelepiped(
                position.add(new Position(distanceFromWorldBoxMargin, distanceFromWorldBoxMargin + fenceWidth, 2)),
                dimensionFence,
                new ShapeSurfaceType(TextureLoader.fences[1], fenceTexture),
                graphicsManager));
        addObject(new StaticParallelepiped(
                position.add(new Position(dimension.getX() - distanceFromWorldBoxMargin - fenceWidth, distanceFromWorldBoxMargin + fenceWidth, 2)),
                dimensionFence,
                new ShapeSurfaceType(TextureLoader.fences[1], fenceTexture),
                graphicsManager));

        dimensionFence = new Dimension(dimension.getX() - 2 * distanceFromWorldBoxMargin, fenceWidth, fenceWidth);
        addObject(new StaticParallelepiped(
                position.add(new Position(distanceFromWorldBoxMargin, distanceFromWorldBoxMargin, 2)),
                dimensionFence,
                new ShapeSurfaceType(TextureLoader.fences[1], fenceTexture),
                graphicsManager));
        addObject(new StaticParallelepiped(
                position.add(new Position(distanceFromWorldBoxMargin, dimension.getY() - distanceFromWorldBoxMargin, 2)),
                dimensionFence,
                new ShapeSurfaceType(TextureLoader.fences[1], fenceTexture),
                graphicsManager));
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

    public void addFirePlace(FirePlace firePlace) {
        untouchableObjectList.add(firePlace);
        firePlaces.add(firePlace);
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

    public List<FirePlace> getFirePlaces() {
        return firePlaces;
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