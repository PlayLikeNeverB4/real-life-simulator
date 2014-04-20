package graphics;

import logic.MainCharacter;

public class Camera {

    private MainCharacter mainCharacter;
    private GraphicsManager graphicsManager;

    public Camera(MainCharacter mainCharacter, GraphicsManager graphicsManager) {
        this.mainCharacter = mainCharacter;
        this.graphicsManager = graphicsManager;
    }

}