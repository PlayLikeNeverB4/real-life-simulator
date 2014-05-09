package graphics;

import logic.AbstractObject;
import logic.AbstractStaticObject;
import logic.PlayGround;

import javax.media.opengl.GL2;
import java.util.TreeSet;

/**
 * Renders a {@link PlayGround}
 */
public class PlayGroundRenderer extends AbstractRenderer {

    /**
     * @param playGround  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public PlayGroundRenderer(AbstractObject playGround, GraphicsManager graphicsManager) {
        super(playGround, graphicsManager);
        this.graphicsManager = graphicsManager;
    }

    /**
     * This method renders the object
     */
    public void render() {
        GL2 gl = graphicsManager.getGlObject();

        double posX = renderedObject.getPosition().getX();
        double posY = renderedObject.getPosition().getY();
        double posZ = renderedObject.getPosition().getZ() + 0.5;

        double size = ((PlayGround) renderedObject).getPlayGroundSize();

        // here is rendered the down side of playground
        gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
        TextureHandler.enableTexturing(gl);
        TextureLoader.sand.bind();
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(posX, posY, posZ);

            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(posX + size, posY, posZ);

            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(posX + size, posY + size, posZ);

            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(posX, posY + size, posZ);
        gl.glEnd();
        gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
        TextureHandler.disableTexturing(gl);

        // rendering the lateral sides of playground - rendering it's fences
        AbstractStaticObject[] fences = ((PlayGround) renderedObject).getSurroundings();
        for(int fenceIdx = 0; fenceIdx < 4; fenceIdx++) {
            fences[fenceIdx].getRenderer().render();
        }

        // rendering it's JoyBoxes
        TreeSet<AbstractObject> joyBoxes = ((PlayGround) renderedObject).getBoxes();
        for(AbstractObject joyBox : joyBoxes) {
            joyBox.getRenderer().render();
        }

    }
}
