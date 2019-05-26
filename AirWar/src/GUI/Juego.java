package GUI;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Juego extends Canvas implements Runnable {
    private static final int  altura = 600, ancho = (altura * 16) / 9;

    private Thread hilo;
    private boolean corriendo = false;


    public Juego(){
        new Window(ancho, altura, "AirWar", this);
    }

    private synchronized void iniciar(){
        hilo = new Thread(this);
        hilo.start();
        corriendo = true;
    }

    private synchronized void detener(){
        try{
            hilo.join();
            corriendo = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

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

    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0,0, ancho, altura);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Juego();

    }
}
