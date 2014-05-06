package graphics.shapes;

import graphics.AbstractRenderer;
import graphics.GraphicsManager;
import graphics.TextureHandler;
import logic.AbstractObject;
import logic.utils.GeometryUtils;
import logic.Position;
import logic.shapes.Road;

import javax.media.opengl.GL2;

public class RoadRenderer extends AbstractRenderer {

    private static TextureHandler roadTexture;

    /**
     * @param renderedObject  The {@link logic.AbstractObject} that will be rendered on the screen
     * @param graphicsManager The {@link graphics.GraphicsManager} which manages the graphics for rendering
     */
    public RoadRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        roadTexture = new TextureHandler(pathToDir + "road.png", graphicsManager);
    }

    /**
     * This method renders the object
     */
    @Override
    public void render() {
        GL2 gl = graphicsManager.getGlObject();
        Road road = (Road)renderedObject;

        double posX = road.getPosition().getX();
        double posY = road.getPosition().getY();
        double posZ = road.getPosition().getZ();

        double sizeX = road.getDimension().getX();
        double sizeY = road.getDimension().getY();

        gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);

        TextureHandler.enableTexturing(gl);
        roadTexture.bind();

        double roadScale = 100;

        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(posX, posY, posZ);

            gl.glTexCoord2d(1, 0);
            Position lowerRight = GeometryUtils.computePointOnCircle(road.getDirection() - Math.PI / 2, sizeX);
            lowerRight = lowerRight.add(new Position(posX, posY, posZ));
            gl.glVertex3d(lowerRight.getX(), lowerRight.getY(), lowerRight.getZ());

            gl.glTexCoord2d(1, sizeY / roadScale);
            Position upperRight = GeometryUtils.computePointOnCircle(road.getDirection(), sizeY);
            upperRight = upperRight.add(lowerRight);
            gl.glVertex3d(upperRight.getX(), upperRight.getY(), upperRight.getZ());

            gl.glTexCoord2d(0, sizeY / roadScale);
            Position upperLeft = GeometryUtils.computePointOnCircle(road.getDirection(), sizeY);
            upperLeft = upperLeft.add(new Position(posX, posY, posZ));
            gl.glVertex3d(upperLeft.getX(), upperLeft.getY(), upperLeft.getZ());
        gl.glEnd();

        gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
        TextureHandler.disableTexturing(gl);
    }
}
