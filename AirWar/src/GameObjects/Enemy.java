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
    }

    @Override
    public void thick() {

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.drawImage(mediaSources.addImage("/Enemy.png"), x , y, null);
    }
}
