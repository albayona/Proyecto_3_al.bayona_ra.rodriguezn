package model.logic;

import api.IEdge;
import api.IVertex;
import model.algorithms.CC;
import model.algorithms.DFS;
import model.algorithms.DijkstraSP;
import model.algorithms.MSTOnLargestComponent;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.DoublyLinkedList;
import model.data_structures.Lists.Stack;
import model.utils.HaversineComparator;
import model.vo.TripleCostEdge;
import model.vo.VOIntersection;

import java.util.Iterator;



public class BRequirementsManager {


    public String B1(Graph<Integer, VOIntersection, TripleCostEdge> graph1,  double longS, double latS,double longD,  double latD ) throws UnexistingVertexException {

        VOIntersection closestS = null;
        VOIntersection closestD = null;

        double minDistS = Integer.MAX_VALUE;
        double minDistD = Integer.MAX_VALUE;

        String res = "";




        for ( VOIntersection v: graph1.getVertices())
        {

            if (minDistS > distance(latS, longS, v.getLatitud(), v.getLongitude())){
                minDistS = distance(latS, longS, v.getLatitud(), v.getLongitude());
                closestS = v;
            }

            if (minDistD > distance(latD, longD, v.getLatitud(), v.getLongitude())){
                minDistD = distance(latD, longD, v.getLatitud(), v.getLongitude());
                closestD = v;
            }

        }

        try {


            DijkstraSP shortestPath = new DijkstraSP(graph1, closestS.getId(), 0);

            Stack<IEdge> iEdges =  shortestPath.pathTo(closestD.getId());

            if (iEdges != null) {
                res = printPath(iEdges);


                JxMapsB1 maps = new JxMapsB1(iEdges);
                maps.initFrame("Requerimiento B1");
            } else {
                res = "No existe tal camino";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;

    }


    public String  printPath(Iterable<IEdge> iEdges){

        String s ="";
        double dist = 0;
        double time = 0;
       int  num = 0;



        for (IEdge e: iEdges   ) {
            String line = String.format("%40s %20s %40s", e.either(), "<---------------------->", e.other((Integer) e.either().getId()));

            dist+= e.weight(0);
            time+= e.weight(1);
            num++;

            s +=  line+ "\n ";

        }

            s = "\nCantidad de vertices: " + num + ", Distancia total: " + dist + ", Tiempo total: " + time + "\n\n" + "Camino: \n" + s;

        return s;
    }

    public double distance(double startLat, double startLong, double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * c; // <-- d
    }

    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public String B2(Graph<Integer, VOIntersection, TripleCostEdge> graph1,  double longS, double latS, double time ) throws UnexistingVertexException {

        VOIntersection closestS = null;


        double minDistS = Integer.MAX_VALUE;

        String res = "\n\n\n\n";


        for ( VOIntersection v: graph1.getVertices())
        {

            if (minDistS > distance(latS, longS, v.getLatitud(), v.getLongitude())){
                minDistS = distance(latS, longS, v.getLatitud(), v.getLongitude());
                closestS = v;
            }
        }

        DoublyLinkedList<IVertex> list = new DoublyLinkedList<>();
        list.addLast(closestS);

        DijkstraSP shortestPath = new DijkstraSP(graph1, closestS.getId(), 1);
            for (int i = 0; i < graph1.V(); i ++) {

                if (shortestPath.distTo(i) <= time && i != closestS.getId()) {
                    res += shortestPath.getVertex(i) + ": time: " + shortestPath.distTo(i) + "\n";
                list.addLast(shortestPath.getVertex(i));
                }

                System.out.print(shortestPath.distTo(i)+"\n" );
            }

        JxMapsB2 maps = new JxMapsB2(list);
        maps.initFrame("Requerimiento B2");

        return res;

    }

    public String B3(Graph<Integer, VOIntersection, TripleCostEdge> graph1) throws UnexistingVertexException {

        String res ="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

        long time1 = System.currentTimeMillis();

        MSTOnLargestComponent mst = new MSTOnLargestComponent(graph1, new HaversineComparator());
        long time2 = System.currentTimeMillis();

        res+= "Total de vertices en la componente: " + mst.getNumVertices() + "\n";

        for (Iterator<Integer> it = mst.getVertices(); it.hasNext(); ) {
            Integer v = it.next();
            res+= "ID: "+ v + "\n";


        }

        res+="\n Costo total: " + mst.totalCost() + "\n";



        for (IEdge e: mst.getEdges()){

            res+= e+"\n";
        }

        long tiempoTotal = time2-time1;
        res+="Tiempo que tarda el algoritmo:" + tiempoTotal+ "\n";


        JxMapsB3 maps = new JxMapsB3(mst.getMST().edges());
        maps.initFrame("Requerimiento B3");

        return res;

    }




}
