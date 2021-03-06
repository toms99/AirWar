package GameProcess;

import GameObjects.ObjetoDeJuego;

import java.awt.*;
import java.util.LinkedList;

/**
 * Esta clase se encarga de hacer el manejo de los objetos en el juego
 */
public class Handler {
    private LinkedList<ObjetoDeJuego>  objetosEnJuego = new LinkedList<ObjetoDeJuego>();

    public void tick(){
        for (int i = 0; i < objetosEnJuego.size(); i++){
            ObjetoDeJuego objeto = objetosEnJuego.get(i);
            objeto.thick();

        }
    }

    public void render(Graphics graphics){
        for (int i = 0; i < objetosEnJuego.size(); i++){
            ObjetoDeJuego objeto = objetosEnJuego.get(i);
            objeto.render(graphics);

        }
    }

    public void addObjeto(ObjetoDeJuego objeto){
        this.objetosEnJuego.add(objeto);
    }

    public void removeObjeto(ObjetoDeJuego objeto){
        this.objetosEnJuego.remove(objeto);
    }

    public LinkedList<ObjetoDeJuego> getObjetosEnJuego() {
        return objetosEnJuego;
    }
}
