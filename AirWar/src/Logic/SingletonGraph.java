package Logic;

import java.util.*;

import static java.lang.String.format;

public class SingletonGraph {

    /*
     *----------------------------------------------------------------------------
     *                             ATRIBUTES
     *----------------------------------------------------------------------------
     */
    private static volatile SingletonGraph graph;
    private int vertices = 5;
    private int matrix[][] = new int[vertices][vertices];
    private Node[] nodes = new Node[vertices];
    private Map<String,Waze> allPaths = new HashMap<>();
    private static List<Integer> stops = new LinkedList<>();
    private String actualPath;


    /*
     *----------------------------------------------------------------------------
     *                             GET METHODS
     *----------------------------------------------------------------------------
     */

    public int getVertices() {
        return vertices;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Map<String,Waze> getAllPaths() {
        return allPaths;
    }

    /*
     *----------------------------------------------------------------------------
     *                             SET METHODS
     *----------------------------------------------------------------------------
     */

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setAllPaths(Map<String, Waze> allPaths) {
        this.allPaths = allPaths;
    }

    /*
     *----------------------------------------------------------------------------
     *                      CONSTRUCTOR & PRINTING METHODS
     *----------------------------------------------------------------------------
     */

    /**
     *
     * Default Graph constructor
     */
    private SingletonGraph(){

        //Prevent form the reflection api.
        if (graph != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static SingletonGraph getInstance(){
        if (graph == null){ //if there is no instance available... create new one
            graph = new SingletonGraph();
            graph.initGraph();
        }

        return graph;
    }

    /**
     * Crea una visualizacion String del grafo.
     * @return
     */
    @Override
    public String toString() {
        String sMatrix = "";
        sMatrix += String.format("Graph [vertex=%s]\n",this.vertices);

        // It iterates in the matrix to show in the console.
        for (int i = 0;i < vertices;i++) {
            for (int j = 0;j < vertices;j++) {

                if(matrix[i][j] == (int)Double.POSITIVE_INFINITY){
                    sMatrix+= String.format("[%s]","/");
                }else{
                    sMatrix+= String.format("[%s]", matrix[i][j]);
                }

            }
            sMatrix += "\n";
        }
        return sMatrix;
        }

    /**
     * Hace print de los Nodes
     * @return
     */
    public String showNodes() {
        String sNodes = "";
        for (int i = 0; i < nodes.length; i++) {
            sNodes += format("[%s]", nodes[i]);
        }
        return sNodes;
    }

    /*
     *----------------------------------------------------------------------------
     *                             PUBLIC METHODS
     *----------------------------------------------------------------------------
     */

    private void initGraph(){
        for (int i = 0;i < vertices;i++) {
            for (int j = 0;j < vertices;j++) {
                // -1 represents -1, because in this project we will not use negative distances
                matrix[i][j] = (int)Double.POSITIVE_INFINITY;
            }
        }
    }

    public void addEdge(Node from, Node to, int distance){
        matrix[from.getId()][to.getId()] = distance;
    }

    public void addNode(Node node){
        nodes[node.getId()]=node;

    }

    /*
     *----------------------------------------------------------------------------
     *                            FLOYD-WARSHALL ALGORYTHM
     *----------------------------------------------------------------------------
     */

    /**
     * Este método ejecuta el algoritmo Floyd-Warshall para encontrar todas las rutas entre los vértices del grafo.
     *
     */
    public void floydWarshall(){
        int[][] distances = this.matrix;
        int vertex = this.vertices;
        double[][] distance = new double[vertex][vertex];
        for (double[] row : distance) {
            Arrays.fill(row,Double.POSITIVE_INFINITY);
        }

        for (int i = 0;i< vertex;i++) {
            for (int j = 0;j<vertex;j++) {
                if(i != j){
                    distance[i][j] = distances[i][j];
                }
            }
        }


        int[][] next = new int[vertex][vertex];
        for (int i = 0;i<next.length;i++) {
            for (int j = 0;j<next.length;j++) {
                if(i != j){
                    next[i][j] = j+1;
                }
            }
        }

        for (int k = 0;k<next.length;k++) {
            for (int i = 0;i<next.length;i++) {
                for (int j = 0;j<next.length;j++) {
                    if(distance[i][k] + distance[k][j] < distance[i][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        saveAllPaths(distance,next);

    }

    /**
     * This method is responsible for storing all the routes in a HashMap [pathId, Path] where pathId is something like "0-1" and is
     * associated with a route.
     * @param distance : Adjacency matrix of the graph
     * @param next : Final matrix that has the shortest routes
     */
    private void saveAllPaths(double[][] distance, int[][] next) {

        for (int i = 0;i<next.length;i++) {
            for (int j = 0;j<next.length;j++) {
                if(i != j){

                    int fromId = i+1;
                    int toId = j+1;
                    int dist = (int)distance[i][j];
                    String pathId = String.format("%s-%s",fromId-1,toId-1);
                    String path = String.format("%s",fromId-1);

                    do {
                        fromId = next[fromId-1][toId-1];
                        path += format("->%s",fromId-1);
                    } while (fromId != toId);
                    // Create a new Path to put it in the map of routes
                    Waze tmp = new Waze(path, dist);
                    allPaths.put(pathId, tmp);
                }

            }
        }
        System.out.println(allPaths.toString());

    }

    //@Path("/generateTrip/{from}")
    public String setTravel(/*@PathParam("from")*/ int from, int toId, int stop) {
        String pathId = String.format("%s-%s",from,toId);
        //this.fromId = from;
        this.actualPath = allPaths.get(pathId).getPath();
        //this.actPosition = from;
        this.stops.add(stop);
        String result = this.pathUpdate(from, toId);
        return result;

    }

    private String pathUpdate(int fromId, int toId) {

        System.out.println(stops.toString());
        int tmpFrom = fromId;
        int tmpTo = stops.get(0);

        String momentPath = "";
        for(int i = 0; i < stops.size(); i++) {
            String pathId = String.format("%s-%s",tmpFrom,tmpTo);
            momentPath += allPaths.get(pathId).getPath()+"/";
            tmpFrom = stops.get(i);
            if(i != stops.size()-1) {
                tmpTo = stops.get(i+1);
            }
        }
        String pathId = String.format("%s-%s",tmpTo, toId);
        momentPath += allPaths.get(pathId).getPath()+"/";
        this.actualPath = momentPath;

        System.out.println(momentPath);
        return momentPath;
    }

}
