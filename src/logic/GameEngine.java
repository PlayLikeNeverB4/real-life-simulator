package logic;

import graphics.GraphicsManager;

/**
 * This class manages the life-cycle of the game
 */
public class GameEngine {

    /**
     * The {@link GameWorld} which stores the objects of the game
     */
    private GameWorld gameWorld;

    /**
     * The {@link InputManager} which will handle the input events from keyboard or mouse
     */
    private InputManager inputManager;

    /**
     * The {@link GraphicsManager} which manages the graphics
     */
    private GraphicsManager graphicsManager;

    /**
     * @param graphicsManager        The {@link GraphicsManager} which manages the graphics
     */
    public GameEngine(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        inputManager = new InputManager();
        gameWorld = new GameWorld(graphicsManager);
        initializeGameWorld();
    }
    
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    /**
     * This method creates objects and adds them to the game world
     */
    private void initializeGameWorld() {
        gameWorld.getObjectList().add(new TestObject(new Position(0, 0, 0), 20, graphicsManager));
//        gameWorld.getObjectList().add(new TestObject(new Position(5, 0, 0), 40, graphicsManager));
    }

    /**
     * This method describes the life-cycle of the game
     */
    public void loopOnce() {
        // see input - acum nu avem
        // update objects

    }

}