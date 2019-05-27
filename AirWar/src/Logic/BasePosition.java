package Logic;

import GameObjects.ID;
import GameObjects.ObjetoDeJuego;

import java.util.ArrayList;
import java.util.Random;

public class BasePosition {
    ArrayList<Integer> rangosDePosicionXSuperior = new ArrayList<>();
    ArrayList<Integer> rangosDePosicionXInferior = new ArrayList<>();

    ArrayList<Integer> rangosDePosicionYSuperior = new ArrayList<>();
    ArrayList<Integer> rangosDePosicionYInferior = new ArrayList<>();

    Random random = new Random();

    public ArrayList<Integer> definirPosicion(ObjetoDeJuego base){
        ArrayList<Integer> resultado = new ArrayList<>();
        if (base.getId() == ID.Aeropuerto){
            resultado = definirAeropuerto();
        }
        if (base.getId() == ID.Portaaviones){
            resultado = definirPortaaviones();
        }
        return resultado;
    }

    public ArrayList<Integer> definirAeropuerto(){
        rangosDePosicionXSuperior.add(86);
        rangosDePosicionXSuperior.add(90);
        rangosDePosicionXSuperior.add(381);
        rangosDePosicionXSuperior.add(234);

        rangosDePosicionXInferior.add(190);
        rangosDePosicionXInferior.add(55);
        rangosDePosicionXInferior.add(241);
        rangosDePosicionXInferior.add(50);

        rangosDePosicionYSuperior.add(100);
        rangosDePosicionYSuperior.add(50);

        rangosDePosicionYInferior.add(300);
        rangosDePosicionYInferior.add(80);

        ArrayList<Integer> posicionY1 = ubicacionBase(rangosDePosicionYSuperior);
        ArrayList<Integer> posicionX1 = ubicacionBase(rangosDePosicionXSuperior);

        ArrayList<Integer> posicionY2 = ubicacionBase(rangosDePosicionYInferior);
        ArrayList<Integer> posicionX2 = ubicacionBase(rangosDePosicionXInferior);

        int posibilidad1X = posicionX1.get(0) + random.nextInt(posicionX1.get(1) - posicionX1.get(0));
        int posibilidad1Y = posicionY1.get(0) + random.nextInt(posicionY1.get(1) - posicionY1.get(0));
        int posibilidad2X = posicionX2.get(0) + random.nextInt(posicionX2.get(1) - posicionX2.get(0));
        int posibilidad2Y = posicionY2.get(0) + random.nextInt(posicionY2.get(1) - posicionY2.get(0));

        int definiendo = random.nextInt(2) + 1;
        ArrayList<Integer> resultado = new ArrayList<>();
        if (definiendo == 1){
            resultado.add(posibilidad1X);
            resultado.add(posibilidad1Y);
        } else{
            resultado.add(posibilidad2X);
            resultado.add(posibilidad2Y);
        }
        return resultado;
    }

    public ArrayList<Integer> definirPortaaviones(){
        rangosDePosicionXSuperior.add(240);
        rangosDePosicionXSuperior.add(134);
        rangosDePosicionXSuperior.add(470);
        rangosDePosicionXSuperior.add(99);

        rangosDePosicionXInferior.add(30);
        rangosDePosicionXInferior.add(146);
        rangosDePosicionXInferior.add(136);
        rangosDePosicionXInferior.add(157);
        rangosDePosicionXInferior.add(125);
        rangosDePosicionXInferior.add(196);


        rangosDePosicionYSuperior.add(150);
        rangosDePosicionYSuperior.add(30);

        rangosDePosicionYInferior.add(340);
        rangosDePosicionYInferior.add(31);

        ArrayList<Integer> posicionY1 = ubicacionBase(rangosDePosicionYSuperior);
        ArrayList<Integer> posicionX1 = ubicacionBase(rangosDePosicionXSuperior);

        ArrayList<Integer> posicionY2 = ubicacionBase(rangosDePosicionYInferior);
        ArrayList<Integer> posicionX2 = ubicacionBase(rangosDePosicionXInferior);

        int posibilidad1X = posicionX1.get(0) + random.nextInt(posicionX1.get(1) - posicionX1.get(0));
        int posibilidad1Y = posicionY1.get(0) + random.nextInt(posicionY1.get(1) - posicionY1.get(0));
        int posibilidad2X = posicionX2.get(0) + random.nextInt(posicionX2.get(1) - posicionX2.get(0));
        int posibilidad2Y = posicionY2.get(0) + random.nextInt(posicionY2.get(1) - posicionY2.get(0));

        int definiendo = random.nextInt(2) + 1;
        ArrayList<Integer> resultado = new ArrayList<>();
        if (definiendo == 1){
            resultado.add(posibilidad1X);
            resultado.add(posibilidad1Y);
        } else{
            resultado.add(posibilidad2X);
            resultado.add(posibilidad2Y);
        }
        return resultado;
    }

    public ArrayList<Integer> ubicacionBase(ArrayList<Integer> listaRangos){
        ArrayList<Integer>  resultado = new ArrayList<>();
        int ran = random.nextInt((listaRangos.size()/2));
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
}
