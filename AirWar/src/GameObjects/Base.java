package GameObjects;

import GUI.MediaSources;
import Logic.BasePosition;
import Logic.Node;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Base extends ObjetoDeJuego {
    Random random = new Random();
    MediaSources mediaSources = new MediaSources();
    BasePosition generadorDePosicion = new BasePosition();
    Node nodo;
    private int intID = -1;

    ArrayList<Integer> posicionesTransparentes = generadorDePosicion.getPosicionesTransparentes();
    ArrayList<Integer> posicionesPintadas = generadorDePosicion.getPosicionesPintadas();

    BufferedImage mapa = mediaSources.addImage("/Map.png");


    /**
     * Constructor
     * @param x
     * @param y
     * @param id
     */
    public Base(int x, int y, ID id) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.definirPosicionesDeBase();

        nodo = new Node(intID, id, x, y);

    }

    private int definirID(){
        intID++;
        return intID;
    }
    /**
     * Encargado de definir las posiciones random de un objeto base o portaavion
     * a partir de otra funcion que las establece. Este solo las define segun ID.
     */
    public void definirPosicionesDeBase(){
        generadorDePosicion.obtenerPixelesTransparentes(mapa);
        if (this.getId() == ID.Portaaviones){
            establecerPosiciones(posicionesTransparentes);
        } else {
            establecerPosiciones(posicionesPintadas);
        }
        System.out.println(this.getId() + ": " + this.getX() + ", " + this.getY());
    }

    /**
     * Encargado de establecer las posiciones de las bases.
     * @param listaDePxeles
     */
    private void establecerPosiciones(ArrayList<Integer> listaDePxeles){
        int ran = 2 + random.nextInt(listaDePxeles.size());
        if (ran%2 == 1){
            this.setY(listaDePxeles.get(ran));
            this.setX(listaDePxeles.get(ran-1));
        } else {
            this.setY(listaDePxeles.get(ran+1));
            this.setX(listaDePxeles.get(ran));
        }
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
