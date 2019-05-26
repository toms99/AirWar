package GUI;

import GameProcess.Juego;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(int ancho, int altura, String titulo, Juego juego){
        JFrame marco = new JFrame(titulo);

        marco.setPreferredSize(new Dimension(ancho, altura));
        marco.setMaximumSize(new Dimension(ancho, altura));
        marco.setMinimumSize(new Dimension(ancho, altura));

        marco.setDefaultCloseOperation(marco.EXIT_ON_CLOSE);

        marco.setResizable(false);
        marco.setLocationRelativeTo(null);
        marco.add(juego);
        marco.setVisible(true);

        juego.iniciar();
    }
}
