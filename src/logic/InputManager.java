package logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

public class InputManager implements MouseMotionListener, KeyListener {

    private List<AbstractEvent> eventList;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public InputManager() {
        eventList = new LinkedList<AbstractEvent>();
    }

    public synchronized List<AbstractEvent> popNewEvents() {
        List<AbstractEvent> cpyEventList = eventList;
        eventList.clear();
        return cpyEventList;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP : eventList.add(new MoveEvent(UP));
                break;
            case KeyEvent.VK_DOWN : eventList.add(new MoveEvent(DOWN));
                break;
            case KeyEvent.VK_LEFT : eventList.add(new MoveEvent(LEFT));
                break;
            case KeyEvent.VK_RIGHT : eventList.add(new MoveEvent(RIGHT));
                break;
            case KeyEvent.VK_V : eventList.add(new MoveEvent(UP));
                break;
            case KeyEvent.VK_S : eventList.add(new MoveEvent(DOWN));
                break;
            case KeyEvent.VK_A : eventList.add(new MoveEvent(LEFT));
                break;
            case KeyEvent.VK_D : eventList.add(new MoveEvent(RIGHT));
                break;
            case KeyEvent.VK_F : eventList.add(new FireEvent());
                break;
            case KeyEvent.VK_E : eventList.add(new SwitchEvent());
                break;
            default: System.out.println("This key is not a valid one!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}