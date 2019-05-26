package GameObjects;

import java.awt.*;

public class Jugador extends ObjetoDeJuego{

    public Jugador(int x, int y, ID id){
        super(x, y, id);
    }
    @Override
    public void thick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,40,40);
    }
}
