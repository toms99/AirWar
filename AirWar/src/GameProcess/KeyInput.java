package GameProcess;

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
