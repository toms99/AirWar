package GameObjects;

import java.awt.*;

public class Jugador extends ObjetoDeJuego{

    /**
     * Constructor del objeto Jugador
     * @param x
     * @param y
     * @param id
     */
    public Jugador(int x, int y, ID id){
        super(x, y, id);
        velX = 1;
        velY = 1;
    }

    /**
     * Encargado del movimiento
     */
    @Override
    public void thick() {
        x += velX;
        y += velY;
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
