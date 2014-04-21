package logic;

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

    /**
     * A LinkedList that contains all the events which appear during execution of the application
     */
    private List<AbstractEvent> eventList;

    public InputManager() {
        eventList = new LinkedList<AbstractEvent>();
    }

    /**
     * This method copies and then deletes the existing list of events and then return the copy list of events
     * @return the list of events created in one frame
     */
    public synchronized List<AbstractEvent> popNewEvents() {
        List<AbstractEvent> cpyEventList = new LinkedList<AbstractEvent>();
        for(int i = 0; i < eventList.size(); i++) {
            AbstractEvent event = eventList.get(i);
            if (event instanceof MoveEvent)
                cpyEventList.add(new MoveEvent((MoveEvent) event));
            else if(event instanceof FireEvent) {
                cpyEventList.add(new FireEvent((FireEvent) event));
            } else if(event instanceof SwitchEvent) {
                cpyEventList.add(new SwitchEvent((SwitchEvent) event));
            }
        }
        eventList.clear();
        return cpyEventList;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP : eventList.add(new MoveEvent(MoveEvent.FORWARD));
                break;
            case KeyEvent.VK_DOWN : eventList.add(new MoveEvent(MoveEvent.BACK));
                break;
            case KeyEvent.VK_LEFT : eventList.add(new MoveEvent(MoveEvent.LEFT));
                break;
            case KeyEvent.VK_RIGHT : eventList.add(new MoveEvent(MoveEvent.RIGHT));
                break;
            case KeyEvent.VK_W : eventList.add(new MoveEvent(MoveEvent.FORWARD));
                break;
            case KeyEvent.VK_S : eventList.add(new MoveEvent(MoveEvent.BACK));
                break;
            case KeyEvent.VK_A : eventList.add(new MoveEvent(MoveEvent.LEFT));
                break;
            case KeyEvent.VK_D : eventList.add(new MoveEvent(MoveEvent.RIGHT));
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