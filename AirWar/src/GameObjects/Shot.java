package GameObjects;

import GUI.MediaSources;
import GameProcess.Handler;

import java.awt.*;
import java.util.Random;
import java.util.function.LongToIntFunction;

public class Shot extends ObjetoDeJuego {
    double velY;
    Handler handler;
    public Shot(int x, int y, ID id, double timePressed, Handler handler){
        super(x, y, id);
        velX = 0;
        timePressed /= 2000;
        if (timePressed >= 50) timePressed = 50;
        if (timePressed < 2) timePressed = 2;
        velY = timePressed;
        this.handler = handler;
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
        if (this.getY() <= -10) handler.removeObjeto(this);
    }
}
