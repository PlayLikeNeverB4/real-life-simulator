package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class creates the window (frame) of the game. Creates the interface with the user.
 */
public class MainWindow extends JFrame {

    /**
     * Default constructor has implicit width = 900 and height = 700.
     */
    public MainWindow() {
        this(800, 600);
    }

    /**
     * Explicit constructor
     * @param width specify the width of the frame
     * @param height specify the height of the frame
     */
    public MainWindow(int width, int height) {
        super("Real Life Simulator");

//        // go full-screen
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height);

        makeCursorInvisible();
    }

    private void makeCursorInvisible() {
        // Transparent 16 x 16 pixel cursor image
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame
        getContentPane().setCursor(blankCursor);
    }

}