package logic;

import graphics.GraphicsManager;
import graphics.PlayGroundRenderer;
import javafx.geometry.BoundingBox;
import logic.shapes.Fence;
import logic.shapes.GateFence;
import logic.shapes.JoyBox;
import logic.shapes.ShapeSurfaceType;
import logic.utils.CollisionDetectionUtils;
import logic.utils.ParallelepipedUtils;
import logic.utils.RNGUtils;

import java.util.List;
import java.util.TreeSet;

/**
 * This class represents a mini-world where the {@link MainCharacter} could play with {@link JoyBox}es.
 *
 * JoyBox is a cube with different dimension that is generated at different position and with different
 * colors on it sides.
 *
 * The cubes disappear when the main - character is in collision with them. On the destruction of a cube,
 * it produces some particles and also it is possible to generate a random number of new cubes.
 */
public class PlayGround extends AbstractStaticObject {
    /**
     * The maximum number of boxes that can be in the playground at a time
     */
    public static final int MAX_BOXES = 10;

    /**
     * The maximum dimension of a {@link JoyBox}
     */
    public static final int MAX_BOX_DIMENSION = 50;

    /**
     * The maximum height where the {@link JoyBox} can be generated
     */
    public static final int MAX_BOX_Z = 20;

    /**
     * The minimum height where the {@link JoyBox} can be generated
     */
    public static final int MIN_BOX_Z = 2;

    /**
     * The thickness of a fence
     */
    public static final int FENCE_THICKNESS = 3;

    /**
     * The height of a fence
     */
    public static final int FENCE_HEIGHT = 100;

    /**
     * The enclosure of the playground formed by {@link Fence}s
     */
    private AbstractStaticObject[] surroundings;

    /**
     * A TreeSet of all of the {@link JoyBox}es that are in the playground
     */
    private TreeSet<AbstractObject> boxes;
    private GraphicsManager graphicsManager;

    /**
     * The direction where the {@link GateFence} will be rendered
     * 0 - the entrance in playground is from front
     * 1 - the entrance in playground is from right
     * 2 - the entrance in playground is from back
     * 3 - the entrance in playground is from left
     */
    private int direction;

    /**
     * Represents the width and height of the playground
     */
    private double playGroundSize;

    public PlayGround(Position position, double playGroundSize, int direction, GraphicsManager graphicsManager) {
        super(position);
        this.graphicsManager = graphicsManager;
        this.direction = direction;
        this.playGroundSize = playGroundSize;
        boxes = new TreeSet<AbstractObject>();
        surroundings = new AbstractStaticObject[4]; // down - sand - only quad; 4: back, right, left, front
        addJoyBox();
        addJoyBox();
        addJoyBox();
        addJoyBox();
        constructSurroundings();
        renderer = new PlayGroundRenderer(this, graphicsManager);
    }

    public Position getPosition() {
        return position;
    }

    public AbstractStaticObject[] getSurroundings() {
        return surroundings;
    }

    public TreeSet<AbstractObject> getBoxes() {
        return boxes;
    }

    public double getPlayGroundSize() {
        return playGroundSize;
    }

    /**
     * Constructs the enclosure of the playground.
     * It creates {@link Fence}s that delimiters the playground
     */
    private void constructSurroundings() {
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        double xPlusSize = x + playGroundSize;
        double yPlusSize = y + playGroundSize;
        double gateFencesHeight = (50 / 100.0) * FENCE_HEIGHT;
        double gateFenceLength = playGroundSize / 3;
        int idxFenceTexture = 0; // Wood texture
        Fence[] fences = new Fence[] {
            new Fence(new Position(x, y, z),
                      new Dimension(playGroundSize, FENCE_THICKNESS, FENCE_HEIGHT),
                      graphicsManager, idxFenceTexture),  // front
            new Fence(new Position(xPlusSize, y, z),
                      new Dimension(FENCE_THICKNESS, playGroundSize, FENCE_HEIGHT),
                      graphicsManager, idxFenceTexture), // right
            new Fence(new Position(x, yPlusSize, z),
                      new Dimension(playGroundSize, FENCE_THICKNESS, FENCE_HEIGHT),
                      graphicsManager, idxFenceTexture), // back
            new Fence(new Position(x, y, z),
                      new Dimension(FENCE_THICKNESS, playGroundSize, FENCE_HEIGHT),
                      graphicsManager, idxFenceTexture) // left
        };

        // used for gateFences
        Fence[][] secFences = new Fence[][] {
            // front gate
            {
                new Fence(new Position(x, y, z),
                      new Dimension(gateFenceLength, FENCE_THICKNESS, gateFencesHeight),
                      graphicsManager, idxFenceTexture),

                new Fence(new Position(x + (playGroundSize - (gateFenceLength)), y, z),
                      new Dimension(gateFenceLength, FENCE_THICKNESS, gateFencesHeight),
                      graphicsManager, idxFenceTexture)
            },

            // right gate
            {
                new Fence(new Position(xPlusSize, y, z),
                      new Dimension(FENCE_THICKNESS, gateFenceLength, gateFencesHeight),
                      graphicsManager, idxFenceTexture),
                new Fence(new Position(xPlusSize, y + (playGroundSize - (gateFenceLength)), z),
                      new Dimension(FENCE_THICKNESS, gateFenceLength, gateFencesHeight),
                      graphicsManager, idxFenceTexture)
            },

            // back gate
            {
                new Fence(new Position(x, yPlusSize, z),
                      new Dimension(gateFenceLength, FENCE_THICKNESS, gateFencesHeight),
                      graphicsManager, idxFenceTexture),
                new Fence(new Position(x + (playGroundSize - (gateFenceLength)), yPlusSize, z),
                      new Dimension(gateFenceLength, FENCE_THICKNESS, gateFencesHeight),
                      graphicsManager, idxFenceTexture)
            },

            // left gate
            {
                new Fence(new Position(x, y, z),
                      new Dimension(FENCE_THICKNESS, gateFenceLength, gateFencesHeight),
                      graphicsManager, idxFenceTexture),
                new Fence(new Position(x, y + (playGroundSize - (gateFenceLength)), z),
                      new Dimension(FENCE_THICKNESS, gateFenceLength, gateFencesHeight),
                      graphicsManager, idxFenceTexture),
            }
        };

        for(int i = 0; i < 4; i++) {
            if(i == direction) {
                surroundings[i] = new GateFence(secFences[i], graphicsManager);
            } else {
                surroundings[i] = fences[i];
            }
        }
    }

    /**
     * Adds a new generated {@link JoyBox} to the list of JoyBoxes
     */
    private void addJoyBox() {
        JoyBox joyBox;
        do {
            // generate position
            Position pos = position.add(RNGUtils.generate(playGroundSize - MAX_BOX_DIMENSION - FENCE_THICKNESS, MAX_BOX_Z));

            // generate dimension
            Dimension dimension = new Dimension(RNGUtils.generate((25 / 100.0) * MAX_BOX_DIMENSION, MAX_BOX_DIMENSION,
                    MIN_BOX_Z, MAX_BOX_Z).getX());

            // generate a color for each of it's side
            ShapeSurfaceType[] surfaceTypes = ParallelepipedUtils.createShapeSurfaceTypeArrayRNGColors();
            joyBox = new JoyBox(graphicsManager, pos, dimension, surfaceTypes, this);

        } while(CollisionDetectionUtils.detect(joyBox, boxes).size() != 0);
        boxes.add(joyBox);
    }

    /**
     * Says that a {@link JoyBox} was destroyed and creates a random number of new joyBoxes
     */
    private void joyBoxDestroyed() {
        int highValue = MAX_BOXES - boxes.size();
        int lowValue = 0;
        int numberNewJoyBoxes = (int) (Math.random() * ((highValue - lowValue) + lowValue));
        for(int i = 0; i < numberNewJoyBoxes; i++) {
            addJoyBox();
        }
    }

    /**
     * Deletes the joyBox from the list of joyBoxes
     * @param joyBox     The {@link JoyBox} that will be deleted
     */
    public void removeJoyBox(AbstractObject joyBox) {
        boxes.remove(joyBox);
        joyBoxDestroyed();
    }

    @Override
    public BoundingBox[] getBoundingBoxes() {
        BoundingBox[] boundingBoxes = new BoundingBox[5 + MAX_BOXES + 10];
        int cnt = 0;
        for(int i = 0; i < 4; i++) {
            BoundingBox[] component = surroundings[i].getBoundingBoxes();
            for(int j = 0; j < component.length; j++) {
                boundingBoxes[cnt++] = component[j];
            }
        }
        for(AbstractObject joyBox : boxes) {
            BoundingBox[] joyBoxBoundingBoxes = joyBox.getBoundingBoxes();
            boundingBoxes[cnt++] = joyBoxBoundingBoxes[0];
        }
        return boundingBoxes;
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     */
    protected void collisionSpecialEffects(AbstractObject abstractObject) {
        if(!abstractObject.isMovable())
            throw new RuntimeException();

        List<AbstractObject> collisions = CollisionDetectionUtils.detect(abstractObject, boxes);
        for(AbstractObject joyBox : collisions)
            joyBox.collisionSpecialEffects(abstractObject);
    }

}
