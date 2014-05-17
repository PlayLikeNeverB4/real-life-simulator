package graphics;

/**
 * This class loads and stores all of the textures needed in the application
 */
public class TextureLoader {

    public static TextureHandler[] trees;
    public static TextureHandler[] fences;
    public static TextureHandler road;
    public static TextureHandler step;
    public static TextureHandler ground;
    public static TextureHandler sky;
    public static TextureHandler sand;
    public static TextureHandler doorFront;
    public static TextureHandler doorBack;
    public static TextureHandler floor;
    public static TextureHandler wall;
    public static TextureHandler alley;

    /**
     * Loads all of the needed textures into memory
     * @param pathToDir       The path to the texture folder
     * @param graphicsManager The graphics manager that uses the textures
     */
    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        trees = new TextureHandler[] {
                new TextureHandler(pathToDir + "billboard_tree0.png", graphicsManager),
                new TextureHandler(pathToDir + "billboard_tree1.png", graphicsManager)
        };
        fences = new TextureHandler[] {
                new TextureHandler(pathToDir + "fenceWood.png", graphicsManager),
                new TextureHandler(pathToDir + "rock.png", graphicsManager)
        };
        road = new TextureHandler(pathToDir + "road.png", graphicsManager);
        step = new TextureHandler(pathToDir + "step.png", graphicsManager);
        ground = new TextureHandler(pathToDir + "grass.png", graphicsManager, false);
        sky = new TextureHandler(pathToDir + "sky.png", graphicsManager, false);
        sand = new TextureHandler(pathToDir + "sand.png", graphicsManager);
        doorFront = new TextureHandler(pathToDir + "door_front.png", graphicsManager);
        doorBack = new TextureHandler(pathToDir + "door_back.png", graphicsManager);
        floor = new TextureHandler(pathToDir + "floor.png", graphicsManager);
        wall = new TextureHandler(pathToDir + "wall.png", graphicsManager);
        alley = new TextureHandler(pathToDir + "alley.png", graphicsManager);
    }

}
