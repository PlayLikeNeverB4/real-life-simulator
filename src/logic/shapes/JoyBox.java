package logic.shapes;

import graphics.GraphicsManager;
import logic.AbstractObject;
import logic.Dimension;
import logic.PlayGround;
import logic.Position;

/**
 * JoyBox is a cube with different dimension that is generated at different position and with different
 * colors on it sides.
 *
 * The cubes disappear when the main - character is in collision with them. On the destruction of a cube,
 * it produces some particles and also it is possible to generate a random number of new cubes.
 */
public class JoyBox extends StaticParallelepiped {

    /**
     * The {@link PlayGround} to which the this joyBox belongs
     */
    private PlayGround playGround;

    public JoyBox(GraphicsManager graphicsManager,
                  Position position,
                  Dimension dimension,
                  ShapeSurfaceType[] shapeSurfaceTypes,
                  PlayGround playGround) {
        super(position, dimension, shapeSurfaceTypes, graphicsManager);
        this.playGround = playGround;
    }

    /**
     * Notifies this object that it collided with another object
     * It updates the other object's state depending on this object's special effects
     * @param abstractObject The other object that this object collided with
     */
    @Override
    public void collisionSpecialEffects(AbstractObject abstractObject) {
        playGround.removeJoyBox(this);
    }

}
