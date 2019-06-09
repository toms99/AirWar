package GameObjects;

import GUI.MediaSources;

import java.awt.*;

public class Enemy extends ObjetoDeJuego {

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
        velX = 0;
        velY = 0;
    }

    @Override
    public void thick() {
        x = velX;
        y = velY;

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.drawImage(mediaSources.addImage("/Enemy.png"), x , y, null);
    }
}
