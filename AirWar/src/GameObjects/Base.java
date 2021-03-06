package GameObjects;

import GUI.MediaSources;
import GameProcess.Handler;
import Logic.BasePosition;
import Logic.Node;
import Logic.SingletonGraph;
import Logic.Waze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Base extends ObjetoDeJuego {
    Handler handler;
    Random random = new Random();
    MediaSources mediaSources = new MediaSources();
    BasePosition generadorDePosicion = new BasePosition();
    int IDint;
    private int nivel = 1;
    int tiempoParaNuevoAvion = random.nextInt(20*nivel);

    ArrayList<Integer> posicionesTransparentes = generadorDePosicion.getPosicionesTransparentes();
    ArrayList<Integer> posicionesPintadas = generadorDePosicion.getPosicionesPintadas();

    BufferedImage mapa = mediaSources.addImage("/Map.png");

    Map<String, Waze> paths;


    /**
     * Constructor
     * @param x
     * @param y
     * @param id
     */
    public Base(int x, int y, int IDint,  ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.definirPosicionesDeBase();
        this.handler = handler;
        this.IDint = IDint;

        Node nodo = new Node(IDint, id, this.getX(), this.getY());
        SingletonGraph.getInstance().addNode(nodo);

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

    public void setRoute(){
        paths =  SingletonGraph.getInstance().getAllPaths();
        String routes = paths.toString();
        paths.get(2).toString();
    }


    @Override
    public void thick() {
        if (tiempoParaNuevoAvion <= 0) {
            ObjetoDeJuego avionEnemigo = new Enemy(x, y, ID.Enemigo, IDint, handler);
            handler.addObjeto(avionEnemigo);
            tiempoParaNuevoAvion = 500 + random.nextInt(100 * nivel);
        }
        tiempoParaNuevoAvion -= 1;

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        if(this.getId() == ID.Aeropuerto) graphics.drawImage(mediaSources.addImage("/LandBase.png"), x , y, null);
        else if (this.getId() == ID.Portaaviones) graphics.drawImage(mediaSources.addImage("/SeaBase.png"), x , y, null);

    }
}
