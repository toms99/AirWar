package GameObjects;

import GUI.MediaSources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Base extends ObjetoDeJuego {
    Random random = new Random();

    public Base(int x, int y, ID id){
        super(x, y, id);
        velX = 0;
        velY = 0;
        ArrayList<Integer> rangosDePosicionXSuperior = new ArrayList<>();
        ArrayList<Integer> rangosDePosicionY = new ArrayList<>();

        rangosDePosicionXSuperior.add(86);
        rangosDePosicionXSuperior.add(90);
        rangosDePosicionXSuperior.add(381);
        rangosDePosicionXSuperior.add(234);

        rangosDePosicionY.add(100);
        rangosDePosicionY.add(50);

        ArrayList<Integer> posicionY = ubicacionBase(rangosDePosicionY);
        ArrayList<Integer> posicionX = ubicacionBase(rangosDePosicionXSuperior);

        this.setX(posicionX.get(0) + random.nextInt(posicionX.get(1) - posicionX.get(0)));
        this.setY(posicionY.get(0) + random.nextInt(posicionY.get(1) - posicionY.get(0)));

    }

    public ArrayList<Integer> ubicacionBase(ArrayList<Integer> listaRangos){
        ArrayList<Integer>  resultado = new ArrayList<>();
        int ran = random.nextInt((listaRangos.size()/2)+1);
        if (ran == 0) {
            ran = 1;
        }
        int minimunRange = 0;
        int maximumRange = 0;
        for (int i = 0; i < ran; i ++){
            minimunRange += listaRangos.get(i);
        }
        maximumRange += minimunRange + listaRangos.get(ran);
        resultado.add(minimunRange); resultado.add(maximumRange);
        return resultado;
    }
    @Override
    public void thick() {

    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.setColor(Color.white);
        graphics.drawImage(mediaSources.addImage("/LandBase.png"), x , y, null);
        graphics.drawImage(mediaSources.addImage("/LandBase.png"), x , y, null);

    }
}
