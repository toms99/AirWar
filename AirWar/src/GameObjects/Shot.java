package GameObjects;

import GUI.MediaSources;

import java.awt.*;
import java.util.Random;
import java.util.function.LongToIntFunction;

public class Shot extends ObjetoDeJuego {
    double velY;
    public Shot(int x, int y, ID id, double timePressed){
        super(x, y, id);
        velX = 0;
        timePressed /= 2000;
        if (timePressed >= 50) timePressed = 50;
        if (timePressed < 5) timePressed = 5;
        velY = timePressed;
    }
    @Override
    public void thick() {
        x -= velX;
        y -= velY;

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.setColor(Color.green);
        graphics.drawImage(mediaSources.addImage("/Shot.png"), x , y, null);
    }
}
