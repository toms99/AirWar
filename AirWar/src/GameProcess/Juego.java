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
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(ancho, altura, "AirWar", this);
        handler.addObjeto(new Jugador(50,getAltura() - 100,ID.Jugador));
    }

    public synchronized void start(){
        hilo = new Thread(this);
        hilo.start();
        corriendo = true;
    }

    public synchronized void stop(){
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
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
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

    public static int getAncho() {
        return ancho;
    }

    public static int getAltura() {
        return altura;
    }
}
