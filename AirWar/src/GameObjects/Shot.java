package GameObjects;

import GUI.MediaSources;

import java.awt.*;
import java.util.Random;

public class Shot extends ObjetoDeJuego {

    public Shot(int x, int y, ID id){
        super(x, y, id);
        Random random = new Random();
        velX = 0;
        velY = random.nextInt(15);
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
