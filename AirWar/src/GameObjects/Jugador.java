package GameObjects;

import GameProcess.Juego;

import java.awt.*;
import java.util.Random;

public class Jugador extends ObjetoDeJuego{

    /**
     * Constructor del objeto Jugador
     * @param x
     * @param y
     * @param id
     */
    public Jugador(int x, int y, ID id){
        super(x, y, id);
        velY = 0;
    }

    /**
     * Encargado del movimiento
     */
    @Override
    public void thick() {
        x += velX; y += velY;
        if ((x <= 0) || (x >= Juego.getAncho())) velX *= -1;
        else if ((y <= 0) || (y >=  Juego.getAltura())) y = 1;
    }

    /**
     * Encargado de actualizar los graficos
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x,y,40,40);
    }
}
