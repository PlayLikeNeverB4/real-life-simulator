package graphics;

import com.jogamp.opengl.util.Animator;
import graphics.shapes.FenceRenderer;
import graphics.shapes.RoadRenderer;
import graphics.shapes.StairsRenderer;
import logic.GameEngine;
import logic.InputManager;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

/**
 * This class manages all of the rendering
 */
public class GraphicsManager implements GLEventListener {

    private MainWindow mainWindow;
    private GameEngine gameEngine;

    /**
     * The {@link Camera} of the scenes that are rendered
     */
    private Camera camera;

    /**
     * Object needed for using the JOGL library
     */
    private GL2 glObject;

    /**
     * Object needed for using the JOGL library
     */
    private GLU gluObject;

    /**
     * The canvas on which everything is rendered
     */
    private GLCanvas canvas;

    private InputManager inputManager;

    /**
     * @param mainWindow        The {@link graphics.MainWindow} of the application
     */
    public GraphicsManager(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.camera = null;

        // Creating a new GL profile
        GLProfile glprofile = GLProfile.getDefault();

        // Creating an object to manipulate OpenGL parameters
        GLCapabilities capabilities = new GLCapabilities(glprofile);

        // Setting some OpenGL parameters
        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);

        // Try to enable 2x anti aliasing (it should be supported on most hardware)
        capabilities.setNumSamples(16);
        capabilities.setSampleBuffers(true);
        capabilities.setDepthBits(64);

        // Creating an OpenGL canvas
        this.canvas = new GLCanvas(capabilities);

        // Adding the canvas in the center of the JFrame
        this.mainWindow.getContentPane().add(this.canvas);

        // Adding an OpenGL event listener to the canvas
        this.canvas.addGLEventListener(this);

        // Creating an animator that will redraw the scene 40 times per second
        Animator animator = new Animator(this.canvas);

        // Starting the animator
        animator.start();
    }

    public GL2 getGlObject() {
        return glObject;
    }

    public GLU getGluObject() {
        return gluObject;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public GLCanvas getCanvas() {
        return canvas;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
        this.canvas.addKeyListener(inputManager);
        this.canvas.addMouseMotionListener(inputManager);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.camera = new Camera(gameEngine.getGameWorld().getMainCharacter(), this);
    }

    private void loadTextures(String pathToDir) {
        GameWorldRenderer.loadTextures(pathToDir, this);
        RoadRenderer.loadTextures(pathToDir, this);
        StairsRenderer.loadTextures(pathToDir, this);
        FenceRenderer.loadTextures(pathToDir, this);
        PlayGroundRenderer.loadTextures(pathToDir, this);
    }

    /*
        Initializes JOGL
     */
    @Override
    public void init(GLAutoDrawable canvas) {
        // Saving the GL object for later use
        this.glObject = this.canvas.getGL().getGL2();

        // Saving the GLU object for later use
        this.gluObject = GLU.createGLU();

        // Setting the clear color (the color which will be used to erase the canvas)
        this.glObject.glClearColor(0, 0, 0, 0);

        // Selecting the modelview matrix
        this.glObject.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);

        // Choose the shading model
        this.glObject.glShadeModel(GL2.GL_SMOOTH);
        this.glObject.glEnable(GL.GL_LINE_SMOOTH);

        // Activate the depth test and set the depth function
        this.glObject.glEnable(GL.GL_DEPTH_TEST);
        this.glObject.glDepthFunc(GL.GL_LESS);

        // Blending options
        this.glObject.glDisable(GL.GL_BLEND);
//        this.glObject.glEnable(GL.GL_BLEND);
//        this.glObject.glBlendFunc(GL.GL_ONE, GL.GL_ZERO);

        // Texture setting
        this.glObject.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        this.glObject.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);

        // For very close textures (to prevent z-fighting)
        glObject.glPolygonOffset(0.0f, -1);

        // Load all of the needed textures into the RAM
        loadTextures("res/textures/");

        gameEngine.getGameWorld().initializeGameWorld(this);
    }

    /*
        Renders the game world
     */
    @Override
    public void display(GLAutoDrawable canvas) {
        gameEngine.loopOnce();
        // Erasing the canvas -- filling it with the clear color.
        glObject.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        glObject.glLoadIdentity();
        camera.setItself();
        gameEngine.getGameWorld().getRenderer().render();
    }

    @Override
    public void reshape(GLAutoDrawable canvas, int left, int top, int width, int height) {
    }

    @Override
    public void dispose(GLAutoDrawable canvas) {
    }
}