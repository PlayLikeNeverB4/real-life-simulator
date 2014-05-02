package logic;

import graphics.GraphicsManager;

import java.util.List;

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
     * The timestamp of the last update loop
     */
    private long lastLoopTime;

    /**
     * @param graphicsManager        The {@link GraphicsManager} which manages the graphics
     */
    public GameEngine(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        gameWorld = new GameWorld(graphicsManager);
        inputManager = new InputManager(gameWorld, graphicsManager);
        this.graphicsManager.setInputManager(inputManager);
        lastLoopTime = System.currentTimeMillis();
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
     * This method describes the life-cycle of the game
     */
    public void loopOnce() {
        long currentLoopTime = System.currentTimeMillis();

        // Check input
        inputManager.checkMoveKeys(currentLoopTime - lastLoopTime);
        List<AbstractEvent> eventList = inputManager.popNewEvents();
        for(AbstractEvent event : eventList)
            event.handle();

        // update objects
        // ...

        lastLoopTime = currentLoopTime;
    }

}