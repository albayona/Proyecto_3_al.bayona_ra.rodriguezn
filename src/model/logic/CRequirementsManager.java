package model.logic;

import api.IEdge;
import api.IVertex;
import model.algorithms.BFS;
import model.algorithms.DFS;
import model.algorithms.DijkstraQueue;
import model.algorithms.DijkstraSP;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.LinkedList;
import model.data_structures.Lists.Stack;
import model.data_structures.RedBlackTree.RedBlackTree;
import model.vo.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class CRequirementsManager {

    private Graph<Integer, VOZone, SimpleEdge> graph;
    private boolean[] marked;
    private Stack<Integer> stack;

    public String  C1( Graph<Integer, VOIntersection, TripleCostEdge> G, RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> travelTimesTree) throws UnexistingVertexException, UnexistingEdgeException {

        String res = "";

        graph = new Graph<>();
            marked = new boolean[G.V()];



        for (Object o: G.getVertices()) {
            IVertex temp = (IVertex) o;

            VOIntersection v = (VOIntersection) temp;

            VOZone newV = new VOZone(v.getZona(), v.getLatitud(), v.getLongitude());

            if (!graph.hasVertex(v.getZona()) && v.getZona() != -1) {
                graph.addVertex(v.getZona(), newV);

                System.out.print(newV+"\n");
            }

        }

               for (int i = 0; i < 1160; i++){

           if (!graph.hasVertex(i)){

               VOZone newV =  new VOZone(i, -74, 4);


               graph.addVertex(i, newV);
           }

       }

        System.out.print("-----------");



        for (Object o: G.getEdges()) {

            IEdge E = (IEdge) o;

            System.out.print(E + "\n");

            VOIntersection v1 = (VOIntersection) E.getVertex1();
            VOIntersection v2 = (VOIntersection) E.getVertex2();

            try {
                VOZone z1 = (VOZone) graph.getInfoVertex(v1.getZona());
                VOZone z2 = (VOZone) graph.getInfoVertex(v2.getZona());

                if (z1.getId() != -1 && z2.getId() != -1) {


                    SimpleEdge newE = new SimpleEdge(E.weight(1), z1, z2);


                    if (graph.getEdgeInfo(z1.getId(), z2.getId()) == null && graph.getEdgeInfo(z2.getId(), z1.getId()) == null) {

                        if (z1 != null && z2 != null)
                        graph.addEdge(z1.getId(), z2.getId(), newE);
                    }

                }


            } catch (Exception e) {


            }
        }


            res+= "\nArcos: " + graph.E() + ", Vertices: " + graph.V();

        JxMapsC1 maps = new JxMapsC1(graph.getEdges());
        maps.initFrame("Requerimiento C1");

            return res;
}

    public String C2(int origen, int destino, RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> travelTimesTree) throws UnexistingVertexException {

        String res = "";

        DijkstraSP shortestPath = new DijkstraSP(graph, origen, 0);

        if (graph.getInfoVertex(origen) != null && graph.getInfoVertex(destino) != null) {

            res += "\n Costo total solo a partir del archivo de tiempos: " + calculateMeanTime(origen, destino, travelTimesTree);

             res+= " \n Costo total: " +  shortestPath.distTo(destino) + "\n Camino: \n";

            if (shortestPath.hasPathTo(destino)) {
                for (Object o : shortestPath.pathTo(destino)) {
                    IEdge e = (IEdge) o;

                    res += e + "\n";


                }
            }
            else {
                res+= "No se encontro un camino";
            }
        }

        JxMapsC2 maps = new JxMapsC2(shortestPath.pathTo(destino));
        maps.initFrame("Requerimiento C2");


        return  res;
    }

    private double calculateMeanTime(Integer source, Integer destine, RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> travelTimesTree){

        LinkedList<TravelTime> list = null;
        RedBlackTree<Integer, LinkedList<TravelTime>> t1 = travelTimesTree.get(source);

        if (t1 != null){
            list = t1.get(destine);
        }

        double total = 0;
        double meanTime = 0;



        if (list != null) {
            double num = list.getSize();


            for (TravelTime t : list) {

                total += t.getMeanTime();

            }

            meanTime = total;
        }
        else {

            if (source.equals(destine)) meanTime = 10;

            else meanTime = 2000;
        }

        return meanTime;

    }


    public String C3(int origen) throws UnexistingVertexException {

        String res = "";

        BFS bfs = new BFS(graph, origen);

        int max = 0;
        int vertext = 0;


        for (int i =0; i < bfs.getDistTo().length; i++){

                if (max < bfs.getDistTo()[i]) {
                    if (bfs.hasPathTo(i)){
                    max = bfs.getDistTo()[i] ;
                    vertext = i;
                    }
                }

        }

        res+= "\n Numero total de arcos: " + max+ "\n Camino: \n";

        LinkedList<VOZone> list = new LinkedList<>();

        for (Integer r :bfs.pathTo(vertext)){
            res+= r + "->\n";
            list.addAtEnd((VOZone) graph.getInfoVertex(r));
        }

        JxMapsC3 maps = new JxMapsC3(list);
        maps.initFrame("Requerimiento C3");


        return res;
    }


}
