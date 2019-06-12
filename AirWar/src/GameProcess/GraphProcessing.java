package GameProcess;

import Logic.Node;
import Logic.SingletonGraph;

public class GraphProcessing {

    public static void processGraph(){
        Node[] listaDeNodos = SingletonGraph.getInstance().getNodes();
        Node nodo1 = listaDeNodos[0];
        Node nodo2 = listaDeNodos[1];
        Node nodo3 = listaDeNodos[2];
        Node nodo4 = listaDeNodos[3];
        Node nodo5 = listaDeNodos[4];

        SingletonGraph.getInstance().addEdge(nodo1, nodo2, distanceCalc(nodo1, nodo2));
        SingletonGraph.getInstance().addEdge(nodo1, nodo5, distanceCalc(nodo1, nodo5));
        SingletonGraph.getInstance().addEdge(nodo3, nodo1, distanceCalc(nodo3, nodo1));
        SingletonGraph.getInstance().addEdge(nodo4, nodo3, distanceCalc(nodo4, nodo3));
        SingletonGraph.getInstance().addEdge(nodo5, nodo4, distanceCalc(nodo5, nodo4));
        SingletonGraph.getInstance().addEdge(nodo3, nodo5, distanceCalc(nodo3, nodo5));

        SingletonGraph.getInstance().floydWarshall();
    }

    private static int distanceCalc(Node n1, Node n2){
        int result = (int) Math.sqrt(((n2.getX() + n1.getX())^2) + ((n2.getY() + n1.getY())^2));
        return result;
    }


}
