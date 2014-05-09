package graphics;

import logic.AbstractObject;
import logic.GameWorld;

import javax.media.opengl.GL2;

public class GameWorldRenderer extends AbstractRenderer {

    private static TextureHandler groundTexture;
    private static TextureHandler skyTexture;

    public GameWorldRenderer(AbstractObject renderedObject, GraphicsManager graphicsManager) {
        super(renderedObject, graphicsManager);
    }

    public static void loadTextures(String pathToDir, GraphicsManager graphicsManager) {
        groundTexture = new TextureHandler(pathToDir + "grass.png", graphicsManager, false);
        skyTexture = new TextureHandler(pathToDir + "sky.png", graphicsManager, false);
    }

    @Override
    public void render() {
        GameWorld gameWorld = (GameWorld) renderedObject;
        renderWorldBox();

        // render ordinary objects
        for(AbstractObject abstractObject : gameWorld.getObjectList())
            if(abstractObject.getRenderer() != null)
                abstractObject.getRenderer().render();

        // render untouchable objects
        for(AbstractObject abstractObject : gameWorld.getUntouchableObjectList())
            if(abstractObject.getRenderer() != null)
                abstractObject.getRenderer().render();
    }

    private void renderWorldBox() {
        GL2 gl = graphicsManager.getGlObject();
        GameWorld gameWorld = (GameWorld) renderedObject;

        double sizeX = gameWorld.getDimension().getX();
        double sizeY = gameWorld.getDimension().getY();
        double sizeZ = gameWorld.getDimension().getZ();

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

        skyTexture.bind();
        // front face
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(0, sizeY, 0);
            gl.glTexCoord2d(1, 0); // lower right
            gl.glVertex3d(sizeX, sizeY, 0);
            gl.glTexCoord2d(1, 1); // upper right
            gl.glVertex3d(sizeX, sizeY, sizeZ);
            gl.glTexCoord2d(0, 1); // upper right
            gl.glVertex3d(0, sizeY, sizeZ);
        gl.glEnd();

        // left side face
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(0, sizeY, 0);
            gl.glTexCoord2d(1, 0); // lower right
            gl.glVertex3d(0, 0, 0);
            gl.glTexCoord2d(1, 1); // upper right
            gl.glVertex3d(0, 0, sizeZ);
            gl.glTexCoord2d(0, 1); // upper left
            gl.glVertex3d(0, sizeY, sizeZ);
        gl.glEnd();

        // back face
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(0, 0, 0); // lower left
            gl.glTexCoord2d(1, 0); // lower right
            gl.glVertex3d(sizeX, 0, 0); // lower right
            gl.glTexCoord2d(1, 1); // upper right
            gl.glVertex3d(sizeX, 0, sizeZ); // upper right
            gl.glTexCoord2d(0, 1); // upper left
            gl.glVertex3d(0, 0, sizeZ); // upper left
        gl.glEnd();

        // right side face
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2d(0, 0); // lower left
            gl.glVertex3d(sizeX, sizeY, 0); // lower left
            gl.glTexCoord2d(1, 0); // lower right
            gl.glVertex3d(sizeX, 0, 0); // lower right
            gl.glTexCoord2d(1, 1); // upper right
            gl.glVertex3d(sizeX, 0, sizeZ); // upper right
            gl.glTexCoord2d(0, 1); // upper right
            gl.glVertex3d(sizeX, sizeY, sizeZ); // upper left
        gl.glEnd();
        TextureHandler.disableTexturing(gl);

        // top face
        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3d(42 / 255.0, 108 / 255.0, 172 / 255.0);
            gl.glVertex3d(0, 0, sizeZ); // lower left
            gl.glVertex3d(sizeX, 0, sizeZ); // lower right
            gl.glVertex3d(sizeX, sizeY, sizeZ); // upper right
            gl.glVertex3d(0, sizeY, sizeZ); // upper left
        gl.glEnd();

    }

}