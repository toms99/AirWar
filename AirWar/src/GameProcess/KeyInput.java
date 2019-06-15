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

    static int eventoArduino;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public static void ArduinoReceptor(String tecla){
        if (tecla == "s") eventoArduino = KeyEvent.VK_SPACE;
        if (tecla == "i") eventoArduino = KeyEvent.VK_LEFT;
        if (tecla == "d") eventoArduino = KeyEvent.VK_RIGHT;
    }

    public void keyPressed(KeyEvent event){
        int tecla = event.getKeyCode();
        KeyControl(tecla);
    }

    public void KeyControl(int tecla){
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
                if (tecla == KeyEvent.VK_LEFT) objeto.setVelX(-5);
                if (tecla == KeyEvent.VK_RIGHT) objeto.setVelX(5);
                ultimaTecla = tecla;
            }
        }
       //ArduinoSuperior(ultimaTecla);
    }

    public void keyReleased(KeyEvent event){
        int tecla = event.getKeyCode();
        ArduinoSuperior(tecla);

    }


    public void ArduinoSuperior(int tecla){
        for(int i = 0; i < handler.getObjetosEnJuego().size(); i++) {
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Jugador) {
                if (tecla == KeyEvent.VK_SPACE && ultimaTecla == tecla){
                    ObjetoDeJuego bala = new Shot(objeto.getX(), objeto.getY(), ID.Bala, time, handler);
                    handler.addObjeto(bala);
                    time = 0;
                }
                if (tecla == KeyEvent.VK_LEFT) objeto.setVelX(0);
                if (tecla == KeyEvent.VK_RIGHT) objeto.setVelX(0);
            }
        }
        ultimaTecla = -1;
    }
}
