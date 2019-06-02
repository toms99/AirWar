package GameProcess;

import GameObjects.Cargador;
import GameObjects.ID;
import GameObjects.ObjetoDeJuego;
import GameObjects.Shot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private int ultimaTecla = -1;
    double time;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event){
        int tecla = event.getKeyCode();

        for(int i = 0; i < handler.getObjetosEnJuego().size(); i++){
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Jugador) {
                // key events para Jugador
                if (tecla == KeyEvent.VK_SPACE) {
                    time += (System.currentTimeMillis()/1000000000);
                    handler.addObjeto(new Cargador(objeto.getX() - 20, objeto.getY() - 20, ID.CargadorIzquierdo, handler));
                    handler.addObjeto(new Cargador(objeto.getX() + 10 , objeto.getY() - 20, ID.CargadorIzquierdo, handler));
                    handler.addObjeto(new Cargador(objeto.getX() + 60, objeto.getY() - 20, ID.CargadorDerecho, handler));
                    handler.addObjeto(new Cargador(objeto.getX() + 100, objeto.getY() - 20, ID.CargadorDerecho, handler));
                }
                ultimaTecla = tecla;
            }
        }
    }

    public void keyReleased(KeyEvent event){
        int tecla = event.getKeyCode();

        for(int i = 0; i < handler.getObjetosEnJuego().size(); i++) {
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Jugador) {
                if (tecla == KeyEvent.VK_SPACE && ultimaTecla == tecla){
                    ObjetoDeJuego bala = new Shot(objeto.getX(), objeto.getY(), ID.Bala, time);
                    handler.addObjeto(bala);
                    time = 0;
                }
            }
        }
        ultimaTecla = -1;
    }
}
