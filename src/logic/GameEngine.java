package logic;

import graphics.GraphicsManager;
import logic.events.AbstractEvent;

import java.util.List;

/**
 * This class manages the life-cycle of the game
 */
public class GameEngine {

    /**
     * The maximum time interval between two frames
     */
    private static final int MAX_FRAME_TIME = 35;

    /**
     * The {@link GameWorld} which stores the objects of the game
     */
    private GameWorld gameWorld;

    /**
     * The {@link logic.PhysicsEngine} that handles the physics for this game engine
     */
    private PhysicsEngine physicsEngine;

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
        physicsEngine = new PhysicsEngine(gameWorld);
        inputManager = new InputManager(gameWorld, graphicsManager);
        this.graphicsManager.setInputManager(inputManager);
        lastLoopTime = -1;
    }
    
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    /**
     * This method describes the life-cycle of the game
     */
    public void loopOnce() {
        long currentLoopTime = System.currentTimeMillis();
        long timeInterval = Math.min(MAX_FRAME_TIME, currentLoopTime - lastLoopTime);

//        System.out.println(gameWorld.getMainCharacter().getPosition());

        // Check input
        inputManager.checkMoveKeys(timeInterval);
        List<AbstractEvent> eventList = inputManager.popNewEvents();
        for(AbstractEvent event : eventList)
            event.handle();

        // Update the game world

        // update objects' movement
        for(AbstractMovableObject movableObject : gameWorld.getMovableObjectList())
            if(movableObject != gameWorld.getMainCharacter()) // the mainCharacter's moves are special
                movableObject.update(timeInterval);
        for(AbstractObject object : gameWorld.getUntouchableObjectList())
            if(object.isMovable())
                ((AbstractMovableObject)object).update(timeInterval);

        // special update for objects
        for(AbstractObject abstractObject : gameWorld.getObjectList())
            abstractObject.specialUpdate(timeInterval);
        for(AbstractObject object : gameWorld.getUntouchableObjectList())
            object.specialUpdate(timeInterval);

        // check for collisions
        physicsEngine.checkCollisions();

        // apply gravity
        physicsEngine.applyGravity(timeInterval);
        // check for collisions
        physicsEngine.checkCollisions();

        lastLoopTime = currentLoopTime;
    }

}