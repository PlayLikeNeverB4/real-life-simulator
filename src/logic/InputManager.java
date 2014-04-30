package logic;

import graphics.GraphicsManager;
import graphics.MainWindow;

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

    private GameWorld gameWorld;

    private GraphicsManager graphicsManager;

    /**
     * A LinkedList that contains all the events which appear during execution of the application
     */
    private List<AbstractEvent> eventList;

    public InputManager(GameWorld gameWorld, GraphicsManager graphicsManager) {
        this.gameWorld = gameWorld;
        this.graphicsManager = graphicsManager;
        eventList = new LinkedList<AbstractEvent>();
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

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP : eventList.add(new MoveObjectEvent(MoveObjectEvent.FORWARD));
                break;
            case KeyEvent.VK_DOWN : eventList.add(new MoveObjectEvent(MoveObjectEvent.BACK));
                break;
            case KeyEvent.VK_LEFT : eventList.add(new MoveObjectEvent(MoveObjectEvent.LEFT));
                break;
            case KeyEvent.VK_RIGHT : eventList.add(new MoveObjectEvent(MoveObjectEvent.RIGHT));
                break;
            case KeyEvent.VK_W : eventList.add(new MoveObjectEvent(MoveObjectEvent.FORWARD));
                break;
            case KeyEvent.VK_S : eventList.add(new MoveObjectEvent(MoveObjectEvent.BACK));
                break;
            case KeyEvent.VK_A : eventList.add(new MoveObjectEvent(MoveObjectEvent.LEFT));
                break;
            case KeyEvent.VK_D : eventList.add(new MoveObjectEvent(MoveObjectEvent.RIGHT));
                break;
            case KeyEvent.VK_F : eventList.add(new FireEvent());
                break;
            case KeyEvent.VK_E : eventList.add(new SwitchEvent());
                break;
            default: {
                System.out.println("This key is not a valid one!");
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}