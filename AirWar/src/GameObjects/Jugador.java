package GameObjects;

import java.awt.*;

public class Jugador extends ObjetoDeJuego{

    public Jugador(int x, int y, ID id){
        super(x, y, id);
        velX = 1;
        velY = 1;
    }
    @Override
    public void thick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x,y,40,40);
    }
}
