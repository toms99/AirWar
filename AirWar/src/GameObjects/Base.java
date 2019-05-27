package GameObjects;

import GUI.MediaSources;
import Logic.BasePosition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Base extends ObjetoDeJuego {
    Random random = new Random();
    MediaSources mediaSources = new MediaSources();
    BufferedImage mapa = mediaSources.addImage("/Map.png");
    BasePosition generadorDePosicion = new BasePosition();
    ArrayList<Integer> posicionesTransparentes = generadorDePosicion.getPosicionesTransparentes();
    ArrayList<Integer> posicionesPintadas = generadorDePosicion.getPosicionesPintadas();

    public Base(int x, int y, ID id) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.definirPosicionesDeBase();
    }

    public void definirPosicionesDeBase(){
        generadorDePosicion.obtenerPixelesTransparentes(mapa);
        if (this.getId() == ID.Portaaviones){
            int ran = 2 + random.nextInt(posicionesTransparentes.size());
            if (ran%2 == 1){
                this.setY(posicionesTransparentes.get(ran));
                this.setX(posicionesTransparentes.get(ran-1));
            } else {
                this.setY(posicionesTransparentes.get(ran+1));
                this.setX(posicionesTransparentes.get(ran));
            }
        } else {
            int ran = 2 + random.nextInt(posicionesPintadas.size());
            if (ran%2 == 1){
                this.setY(posicionesPintadas.get(ran));
                this.setX(posicionesPintadas.get(ran-1));
            } else {
                this.setY(posicionesPintadas.get(ran+1));
                this.setX(posicionesPintadas.get(ran));
            }

        }
        System.out.println(this.getId() + ": " + this.getX() + ", " + this.getY());
    }


    @Override
    public void thick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        if(this.getId() == ID.Aeropuerto) graphics.drawImage(mediaSources.addImage("/LandBase.png"), x , y, null);
        else if (this.getId() == ID.Portaaviones) graphics.drawImage(mediaSources.addImage("/SeaBase.png"), x , y, null);

    }
}
