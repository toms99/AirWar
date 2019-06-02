package GameObjects;

import GUI.MediaSources;
import GameProcess.Handler;
import GameProcess.Juego;
import GameProcess.KeyInput;

import java.awt.*;

public class Cargador extends ObjetoDeJuego {
    int maximoMovimiento = 10;
    Handler handler;

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param id
     */
    public Cargador(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 1;
        velY = 1;
        this.handler = handler;
    }

    @Override
    public void thick() {
        if (maximoMovimiento == 0) handler.removeObjeto(this);
        if (getId() == ID.CargadorIzquierdo){
            x += velX;
            y += velY;
        }
        if (getId() == ID.CargadorDerecho) {
            x -= velX;
            y += velY;
        }
        maximoMovimiento -=1;
    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.drawImage(mediaSources.addImage("/Cargador.png"), x , y, null);
    }
}
