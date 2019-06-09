package GameObjects;

import GUI.MediaSources;
import GameProcess.Handler;

import java.awt.*;
import java.util.Objects;

public class Enemy extends ObjetoDeJuego {
    Handler handler;
    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.handler = handler;
    }

    @Override
    public void thick() {
        x += 1;
        y += 1;
        for (int i = 0; i < handler.getObjetosEnJuego().size(); i++){
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Bala){
                if (this.getX() + 32 <= objeto.getX() + 68 && this.getX() + 32 >= objeto.getX()){
                    if (objeto.getY() <= this.getY() + 20){
                        handler.removeObjeto(this);
                    }
                }
            }

        }

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.drawImage(mediaSources.addImage("/Enemy.png"), x , y, null);
    }
}
