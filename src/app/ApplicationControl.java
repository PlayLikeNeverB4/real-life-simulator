package app;

import graphics.GraphicsManager;
import graphics.MainWindow;
import logic.GameEngine;

/**
 * This class initializes the application
 */
public class ApplicationControl {

    private GraphicsManager graphicsManager;
    private MainWindow mainWindow;
    private GameEngine gameEngine;

    /**
     * Initializes all the members of it. The GraphicsManager object, the MainWindow object and the GameEngine object.
     */
    public ApplicationControl() {
        mainWindow = new MainWindow(800, 600);
        graphicsManager = new GraphicsManager(mainWindow);
        gameEngine = new GameEngine(graphicsManager);
        graphicsManager.setGameEngine(gameEngine);

        mainWindow.setVisible(true);
        mainWindow.requestFocus();
        graphicsManager.getCanvas().requestFocusInWindow();
    }

    /**
     * Application entry point
     *
     * @param args a vector of String objects obtained from the input consoles
     */
    public static void main(String[] args) {
        new ApplicationControl();
    }

}