package GameProcess;

import GameObjects.ID;
import GameObjects.ObjetoDeJuego;
import GameObjects.Shot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event){
        int tecla = event.getKeyCode();

        for(int i = 0; i < handler.getObjetosEnJuego().size(); i++){
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Jugador){
                // key events para Jugador
                Random random = new Random();
                if (tecla == KeyEvent.VK_RIGHT) objeto.setVelX(random.nextInt(20));
                if (tecla == KeyEvent.VK_LEFT) objeto.setVelX(-1*random.nextInt(20));
                if (tecla == KeyEvent.VK_SPACE) {
                    ObjetoDeJuego bala = new Shot(objeto.getX(), objeto.getY(), ID.Bala);
                    handler.addObjeto(bala);
                }

            }
        }

    }

    public void keyReleased(KeyEvent event){
        int tecla = event.getKeyCode();
    }
}
