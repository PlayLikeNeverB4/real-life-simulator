package logic;

import graphics.GraphicsManager;

import java.awt.*;

/**
 * A particle used for simulating smoke
 */
public class SmokeParticle extends Particle {
    public SmokeParticle(Position position, Color color, GraphicsManager graphicsManager) {
        super(position, 3, color, graphicsManager);
        // Limit (x,y) movement and encourage z movement
        speed.set(speed.getX() / 2, speed.getY() / 2, speed.getZ() * 2);
    }

    @Override
    public void reset() {
        super.reset();
        speed.set(speed.getX() / 2, speed.getY() / 2, speed.getZ() * 2);
    }
}
