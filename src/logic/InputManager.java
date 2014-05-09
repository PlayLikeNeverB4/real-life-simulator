package logic;

import graphics.GraphicsManager;
import graphics.MainWindow;
import logic.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * This class manages all the input events fired by pressing a key or by moving the mouse
 */
public class InputManager implements MouseMotionListener, KeyListener {

    private static final int FORWARD = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;

    private GameWorld gameWorld;
    private GraphicsManager graphicsManager;

    /**
     * BitMask containing the directions towards which the mainCharacter moves
     */
    private int activeDirections;

    /**
     * A LinkedList that contains all the events which appear during execution of the application
     */
    private List<AbstractEvent> eventList;

    public InputManager(GameWorld gameWorld, GraphicsManager graphicsManager) {
        this.gameWorld = gameWorld;
        this.graphicsManager = graphicsManager;
        eventList = new LinkedList<AbstractEvent>();
        activeDirections = 0;
    }

    /**
     * This method copies and then deletes the existing list of events and then return the copy list of events
     * @return the list of events created in one frame
     */
    public synchronized List<AbstractEvent> popNewEvents() {
        List<AbstractEvent> cpyEventList = new LinkedList<AbstractEvent>();
        for(AbstractEvent event : eventList) {
            cpyEventList.add(event);
        }
        eventList.clear();
        return cpyEventList;
    }

    /**
     * Sets the bit at index send as parameter
     * @param index     The bit position that is set
     */
    private void markPressed(int index) {
        activeDirections |= 1 << index;
    }

    /**
     * Clears the bit at index send as parameter
     * @param index     The bit position that is cleared
     */
    private void markReleased(int index) {
        activeDirections &= ~(1 << index);
    }

    /**
     * Clears the bits for the opposite moving directions
     * @return the new and correct combination of moving directions
     */
    private int clearOppositeDirections() {
        int tempActiveDirections = activeDirections;
        if( (tempActiveDirections & (1 << 0)) > 0 && (tempActiveDirections & (1 << 2)) > 0 ) { // both forward and backward
            tempActiveDirections &= ~(1 << 0);
            tempActiveDirections &= ~(1 << 2);
        }
        if( (tempActiveDirections & (1 << 1)) > 0 && (tempActiveDirections & (1 << 3)) > 0 ) { // both left and right
            tempActiveDirections &= ~(1 << 1);
            tempActiveDirections &= ~(1 << 3);
        }
        return tempActiveDirections;
    }

    /**
     * Checks for active move directions. If at least one direction is active it creates a new MoveMainCharacterEvent
     * @param movingTime    Amount of time in which the mainCharacter moves
     */
    public void checkMoveKeys(long movingTime) {
        int tempActiveDirections = clearOppositeDirections();
        if(tempActiveDirections != 0)
            eventList.add(new MoveMainCharacterEvent(tempActiveDirections, gameWorld, movingTime));
        else
            eventList.add(new StopMainCharacterEvent(gameWorld));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP :    markPressed(FORWARD);
                break;
            case KeyEvent.VK_LEFT :  markPressed(LEFT);
                break;
            case KeyEvent.VK_DOWN :  markPressed(DOWN);
                break;
            case KeyEvent.VK_RIGHT : markPressed(RIGHT);
                break;
            case KeyEvent.VK_W :     markPressed(FORWARD);
                break;
            case KeyEvent.VK_A :     markPressed(LEFT);
                break;
            case KeyEvent.VK_S :     markPressed(DOWN);
                break;
            case KeyEvent.VK_D :     markPressed(RIGHT);
                break;
            case KeyEvent.VK_F : eventList.add(new FireEvent());
                return;
            case KeyEvent.VK_E : eventList.add(new SwitchEvent());
                return;
            default: {
                System.out.println("This key is not a valid one!");
                return;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP :    markReleased(FORWARD);
                break;
            case KeyEvent.VK_LEFT :  markReleased(LEFT);
                break;
            case KeyEvent.VK_DOWN :  markReleased(DOWN);
                break;
            case KeyEvent.VK_RIGHT : markReleased(RIGHT);
                break;
            case KeyEvent.VK_W :     markReleased(FORWARD);
                break;
            case KeyEvent.VK_A :     markReleased(LEFT);
                break;
            case KeyEvent.VK_S :     markReleased(DOWN);
                break;
            case KeyEvent.VK_D :     markReleased(RIGHT);
                break;
            default: {
                System.out.println("This key is not a valid one!");
                return;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int middleX = graphicsManager.getCanvas().getWidth() / 2;
        int middleY = graphicsManager.getCanvas().getHeight() / 2;

//        System.out.println(e.getY() + " " + middleY);

        eventList.add(new MouseMoveEvent(e.getX() - middleX,
                                         e.getY() - middleY,
                                         gameWorld.getMainCharacter(),
                                         graphicsManager));

        Robot robot = null;
        try {
            robot = new Robot();
            MainWindow mainWindow = graphicsManager.getMainWindow();
            Point point = new Point(middleX, middleY);
            SwingUtilities.convertPointToScreen(point, graphicsManager.getCanvas());
            robot.mouseMove(mainWindow.getX() + mainWindow.getWidth() / 2,
                    (int) point.getY());
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}