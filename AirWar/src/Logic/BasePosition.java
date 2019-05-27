package Logic;

import GameObjects.ID;
import GameObjects.ObjetoDeJuego;
import GameProcess.Juego;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class BasePosition {

    private ArrayList<Integer> posicionesTransparentes = new ArrayList<>();
    private ArrayList<Integer> posicionesPintadas = new ArrayList<>();
    private int margenDeError = 18;

    /**
     * Define las listas de posiciones con pixeles transparentes y no transparentes de una image.
     * @param image
     */
    public void obtenerPixelesTransparentes(BufferedImage image){
        for (int i = 10; i < (image.getWidth() - 30); i++){
            for (int j = 10; j < (image.getHeight()- 30); j++){
                if (contieneTransparencia(image, i+margenDeError, j+margenDeError)){
                    posicionesTransparentes.add(i);
                    posicionesTransparentes.add(j);
                } else {
                    posicionesPintadas.add(i);
                    posicionesPintadas.add(j);
                }
            }
        }
    }

    /**
     * Retorna un valor bool sobre si hay o no transparencia en un pixel.
     * @param image
     * @param x
     * @param y
     * @return
     */
    public boolean contieneTransparencia(BufferedImage image, int x, int y){
        int pixel = image.getRGB(x, y);
        return (pixel >> 24) == 0x00;
    }

    public ArrayList<Integer> getPosicionesPintadas() {
        return posicionesPintadas;
    }

    public ArrayList<Integer> getPosicionesTransparentes() {
        return posicionesTransparentes;
    }

}
