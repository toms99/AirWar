package GameObjects;

import GUI.MediaSources;
import GameProcess.Juego;

import java.awt.*;
import java.util.Random;

public class Jugador extends ObjetoDeJuego{
    int anchoMaximo = 827;
    Random random = new Random();
    int pixelMaximoDeVelocidad = 150;

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
        if (pixelMaximoDeVelocidad <= 0){
            velX = random.nextInt(20) + 2;
            pixelMaximoDeVelocidad = 150;
        }
        x += velX;
        pixelMaximoDeVelocidad -= 1;
        if ((x <= 0) || (x >= anchoMaximo)) velX *= -1;
    }

    /**
     * Encargado de actualizar los graficos
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.setColor(Color.white);
        graphics.drawImage(mediaSources.addImage("/Plane1.png"), x , y, null);
    }
}
