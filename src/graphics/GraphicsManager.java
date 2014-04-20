package graphics;

import logic.GameEngine;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

/**
 * This class manages all of the rendering
 */
public class GraphicsManager implements GLEventListener {

    private AbstractRenderer gameWorldRenderer;
    private MainWindow mainWindow;

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

    /**
     *
     * @param gameEngine        The {@link GameEngine} for which it manages the graphics
     * @param gameWorldRenderer The {@link AbstractRenderer} which renders the game world
     * @param mainWindow        The {@link MainWindow} of the application
     */
    public GraphicsManager(GameEngine gameEngine, AbstractRenderer gameWorldRenderer, MainWindow mainWindow) {
        this.gameWorldRenderer = gameWorldRenderer;
        this.mainWindow = mainWindow;
        this.camera = new Camera(gameEngine.getGameWorld().getMainCharacter(), this);
    }

    /**
     * Draws the game world
     */
    public void draw() {
//        display(...);
    }

    public GL2 getGlObject() {
        return glObject;
    }

    public GLU getGluObject() {
        return gluObject;
    }

    /*
        Initializes JOGL
     */
    @Override
    public void init(GLAutoDrawable canvas) {
        // Creating a new GL profile
        GLProfile glprofile = GLProfile.getDefault();

        // Creating an object to manipulate OpenGL parameters
        GLCapabilities capabilities = new GLCapabilities(glprofile);

        // Setting some OpenGL parameters
        capabilities.setHardwareAccelerated(true);
        capabilities.setDoubleBuffered(true);

        // Try to enable 2x anti aliasing (it should be supported on most hardware)
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);

        // Creating an OpenGL canvas
        this.canvas = new GLCanvas(capabilities);

        // Adding the canvas in the center of the JFrame
        this.mainWindow.getContentPane().add(this.canvas);

        // Adding an OpenGL event listener to the canvas
        this.canvas.addGLEventListener(this);

        // Adding the keyboard and mouse event listeners to the canvas
//        this.canvas.addKeyListener(this);

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

        // Activate the depth test and set the depth function
        this.glObject.glEnable(GL.GL_DEPTH_TEST);
        this.glObject.glDepthFunc(GL.GL_LESS);
    }

    /*
        Renders the game world
     */
    @Override
    public void display(GLAutoDrawable canvas) {

    }

    @Override
    public void reshape(GLAutoDrawable canvas, int left, int top, int width, int height) {
    }

    @Override
    public void dispose(GLAutoDrawable canvas) {
    }
}