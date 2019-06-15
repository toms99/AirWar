package GameObjects;

import GUI.MediaSources;
import GameProcess.Handler;
import Logic.Node;
import Logic.SingletonGraph;

import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Enemy extends ObjetoDeJuego {
    Handler handler;
    int toInit = 0;
    String path;
    LinkedList<Integer> route = new LinkedList<>();


    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id, int IDint, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 0;
        this.handler = handler;
//        int randomGoal = new Random().nextInt(4);
//        int randomStop = new Random().nextInt(4);
//        path = SingletonGraph.getInstance().setTravel(IDint, randomGoal, randomStop);
//        char[] pathArray = path.toCharArray();
//        for (int i =0; i<pathArray.length; i++){
//            if (pathArray[i] == '-' || pathArray[i] == '/'){
//                route.add(Character.getNumericValue(pathArray[i-1]));
//            }
//        }
    }

    @Override
    public void thick() {
        if (toInit <100) {toInit += 1;}
        //else {travelTo(route.get(0)); route.remove(0);}

        for (int i = 0; i < handler.getObjetosEnJuego().size(); i++){
            ObjetoDeJuego objeto = handler.getObjetosEnJuego().get(i);
            if (objeto.getId() == ID.Bala){
                if (this.getX() + 32 <= objeto.getX() + 68 && this.getX() + 32 >= objeto.getX()){
                    if (objeto.getY() <= this.getY() + 20){
                        handler.removeObjeto(this);
                    }
                }
            }

        }

    }

    private void travelTo(int id) {
        Node nodes[] = SingletonGraph.getInstance().getNodes();
        for (int i = 0; i < nodes.length; i++){
            if (nodes[i].getId() == id){
                if (x < nodes[i].getX()) x+=5;
                if (x > nodes[i].getX()) x-=5;
                if (x == nodes[i].getX()) x+=0;
                if (y < nodes[i].getY()) y+=2;
                if (y > nodes[i].getY()) y-=2;
                if (y == nodes[i].getY()) y +=0;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        MediaSources mediaSources = new MediaSources();
        graphics.drawImage(mediaSources.addImage("/Enemy.png"), x , y, null);
    }
}
