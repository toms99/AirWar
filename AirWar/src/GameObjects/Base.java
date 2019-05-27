package GameObjects;

import GUI.MediaSources;
import Logic.BasePosition;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Base extends ObjetoDeJuego {
    Random random = new Random();

    public Base(int x, int y, ID id){
        super(x, y, id);
        velX = 0;
        velY = 0;

        BasePosition generadorDePosicion = new BasePosition();
        ArrayList<Integer> posiciones = generadorDePosicion.definirPosicion(this);
        setX(posiciones.get(0));
        setY(posiciones.get(1));


    }


    @Override
    public void thick() {

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.setColor(Color.white);
        if(this.getId() == ID.Aeropuerto) graphics.drawImage(mediaSources.addImage("/LandBase.png"), x , y, null);
        else if (this.getId() == ID.Portaaviones) graphics.drawImage(mediaSources.addImage("/SeaBase.png"), x , y, null);

    }
}
