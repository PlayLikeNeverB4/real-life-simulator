package graphics;

public class GraphicsManager {

    private Camera camera;
    private AbstractRenderer gameWorldRenderer;
    private MainWindow mainWindow;

    public GraphicsManager(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void draw() {
    }

}