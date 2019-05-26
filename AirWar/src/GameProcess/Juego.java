package GameProcess;

import GUI.Window;
import GameObjects.ID;
import GameObjects.Jugador;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Se encarga de toda la logica de graficos del juego.
 * Aqui se une el programa.
 *
 */
public class Juego extends Canvas implements Runnable {
    private static final int  altura = 600, ancho = (altura * 16) / 9;

    private Thread hilo;
    private boolean corriendo = false;

    private Handler handler;

    /**
     * Constructor
     */
    public Juego(){
        new Window(ancho, altura, "AirWar", this);
        handler = new Handler();
        handler.addObjeto(new Jugador(10,10,ID.Jugador));
        handler.addObjeto(new Jugador(100,10,ID.Jugador));
        handler.addObjeto(new Jugador(50,10,ID.Jugador));


    }

    public synchronized void iniciar(){
        hilo = new Thread(this);
        hilo.start();
        corriendo = true;
    }

    public synchronized void detener(){
        try{
            hilo.join();
            corriendo = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Loop del juego.
     * Este loop en particular es bastante popular.
     */
    @Override
    public void run() {
        long ultimaVez = System.nanoTime();
        double cantidadDeTicks = 60.0;
        double ns = 1000000000 / cantidadDeTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (corriendo){
            long ahora = System.nanoTime();
            delta += (ahora - ultimaVez) / ns;
            ultimaVez = ahora;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (corriendo)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        detener();
    }

    private void tick(){
        handler.tick();
    }

    /**
     * Encargado de mostrar los objetos en el juego.
     */
    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0,0, ancho, altura);

        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Juego();

    }
}
