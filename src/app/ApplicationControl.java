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

    public ApplicationControl() {
    }

    /**
     * Application entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        new ApplicationControl();
    }

}