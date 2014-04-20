package graphics;

import javax.swing.JFrame;

/**
 * This class creates the window (frame) of the game. Creates the interface with the user.
 */
public class MainWindow extends JFrame {

    private int height, width;

    /**
     * Default constructor has implicit width = 900 and height = 700.
     */
    public MainWindow() {
        new MainWindow(900, 700);
    }

    /**
     * Explicit constructor
     * @param width specify the width of the frame
     * @param height specify the height of the frame
     */
    public MainWindow(int width, int height) {
        super("Real Life Simulator");

        this.width = width;
        this.height = height;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height);

        this.setVisible(true);
    }

}