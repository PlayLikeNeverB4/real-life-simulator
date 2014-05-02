package graphics;

import logic.AbstractObject;
import logic.GameWorld;

import javax.media.opengl.GL2;

public class GameWorldRenderer extends AbstractRenderer {

    private static TextureHandler groundTexture;

    public GameWorldRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        groundTexture = new TextureHandler(pathToDir + "grass.png", graphicsManager, false);
    }

    @Override
    public void render() {
        GameWorld gameWorld = (GameWorld) renderedObject;
        renderWorldBox();
        for(AbstractObject abstractObject : gameWorld.getObjectList()) {
            abstractObject.getRenderer().render();
        }
    }

    private void renderWorldBox() {
        GL2 gl = graphicsManager.getGlObject();
        GameWorld gameWorld = (GameWorld) renderedObject;

        double sizeX = gameWorld.getDimension().getX();
        double sizeY = gameWorld.getDimension().getY();
        double sizeZ = gameWorld.getDimension().getZ();

        gl.glBegin(GL2.GL_QUADS);
            // front face
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(0, sizeY, 0); // lower left
            gl.glVertex3d(sizeX, sizeY, 0); // lower right
            gl.glVertex3d(sizeX, sizeY, sizeZ); // upper right
            gl.glVertex3d(0, sizeY, sizeZ); // upper left

            // right side face
            gl.glColor3d(1, 1, 0);
            gl.glVertex3d(sizeX, sizeY, 0); // lower left
            gl.glVertex3d(sizeX, 0, 0); // lower right
            gl.glVertex3d(sizeX, 0, sizeZ); // upper right
            gl.glVertex3d(sizeX, sizeY, sizeZ); // upper left

            // left side face
            gl.glColor3d(1, 1, 0);
            gl.glVertex3d(0, sizeY, 0); // lower left
            gl.glVertex3d(0, 0, 0); // lower right
            gl.glVertex3d(0, 0, sizeZ); // upper right
            gl.glVertex3d(0, sizeY, sizeZ); // upper left

            // back face
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(0, 0, 0); // lower left
            gl.glVertex3d(sizeX, 0, 0); // lower right
            gl.glVertex3d(sizeX, 0, sizeZ); // upper right
            gl.glVertex3d(0, 0, sizeZ); // upper left

            // top face
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(0, 0, sizeZ); // lower left
            gl.glVertex3d(sizeX, 0, sizeZ); // lower right
            gl.glVertex3d(sizeX, sizeY, sizeZ); // upper right
            gl.glVertex3d(0, sizeY, sizeZ); // upper left
        gl.glEnd();

        gl.glColor3d(1, 1, 1);
        TextureHandler.enableTexturing(gl);
        groundTexture.bind();
        int groundTextureScale = 32;
        gl.glBegin(GL2.GL_QUADS); // bottom face
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(0, 0, 0);
            gl.glTexCoord2d(sizeX / groundTextureScale, 0); // lower right
            gl.glVertex3d(sizeX, 0, 0);
            gl.glTexCoord2d(sizeX / groundTextureScale, sizeY / groundTextureScale); // upper right
            gl.glVertex3d(sizeX, sizeY, 0);
            gl.glTexCoord2d(0, sizeY / groundTextureScale); // upper left
            gl.glVertex3d(0, sizeY, 0);
        gl.glEnd();

        TextureHandler.disableTexturing(gl);
    }

}