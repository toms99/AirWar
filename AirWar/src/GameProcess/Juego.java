package GameProcess;

import GUI.MediaSources;
import GUI.Window;
import GameObjects.Base;
import GameObjects.ID;
import GameObjects.Jugador;
import Logic.BasePosition;
import Logic.SingletonGraph;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Se encarga de toda la logica de graficos del juego.
 * Aqui se une el programa.
 *
 */
public class Juego extends Canvas implements Runnable {
    private static final int  altura = 600, ancho = (altura * 16) / 9;

    private Thread hilo;
    private boolean corriendo = false;

    public static Handler getHandler() {
        return handler;
    }

    private static Handler handler;
    MediaSources mediaSources = new MediaSources();

    /**
     * Constructor
     */
    public Juego(){

        this.handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(ancho, altura, "AirWar", this);

        handler.addObjeto(new Jugador(50,getAltura() - 125,ID.Jugador));
        handler.addObjeto(new Base(0,0, 0, ID.Aeropuerto, handler));
        handler.addObjeto(new Base(0,0, 1, ID.Aeropuerto, handler));
        handler.addObjeto(new Base(0,0, 2, ID.Portaaviones, handler));
        handler.addObjeto(new Base(0,0, 3, ID.Portaaviones, handler));
        handler.addObjeto(new Base(0,0, 4, ID.Portaaviones, handler));

        GraphProcessing.processGraph();
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
        this.requestFocus();
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
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.fillRect(0,0, ancho, altura);
        graphics.setColor(Color.black);
        graphics.drawImage(mediaSources.addImage("/Wallpaper.png"), 0 , 0, null);
        graphics.drawImage(mediaSources.addImage("/Map.png"), 0 , 0, null);
        graphics.drawImage(mediaSources.addImage("/Grid.png"), 0 , 0, null);



        handler.render(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Juego();
        //Arduino();
    }
     public static void Arduino(){
            ArduinoControl arduino = new ArduinoControl();
            ArduinoControl.detectPort();
            String newValue = ArduinoControl.portList.get(0);
            arduino.connectArduino(newValue);
     }

    public static int getAncho() {
        return ancho;
    }

    public static int getAltura() {
        return altura;
    }
}
