package logic;

import graphics.GraphicsManager;

public class GameEngine {

    private GameWorld gameWorld;
    private InputManager inputManager;
    private GraphicsManager graphicsManager;

    public GameEngine(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        inputManager = new InputManager();
        gameWorld = new GameWorld();
        setInitialGameWorldState();
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

    private void setInitialGameWorldState() {

    }

    public void run() {

    }

}