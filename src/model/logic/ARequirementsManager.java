package model.logic;

import api.IEdge;
import javafx.util.Pair;
import model.algorithms.DijkstraSP;
import model.algorithms.MSTOnLargestComponent;
import model.algorithms.MSTOnLargestComponentPrim;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.Stack;
import model.data_structures.MinHeap.MinHeap;
import model.utils.HaversineComparator;
import model.vo.TripleCostEdge;
import model.vo.VOIntersection;

import java.util.Comparator;
import java.util.Iterator;

public class ARequirementsManager {
    public String A1(Graph<Integer, VOIntersection, TripleCostEdge> graph1, double longS, double latS, double longD, double latD ) throws UnexistingVertexException {

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


            DijkstraSP shortestPath = new DijkstraSP(graph1, closestS.getId(), 1);

            Stack<IEdge> iEdges =  shortestPath.pathTo(closestD.getId());

            if (iEdges != null) {
                res = printPath(iEdges);


                JxMapsB1 maps = new JxMapsB1(iEdges);
                maps.initFrame("Requerimiento A1");
            } else {
                res = "No existe tal camino";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;

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

        s = "\nCantidad de vertices: " + num + ", Tiempo total: " + dist + ", Tiempo total: " + time + "\n\n" + "Camino: \n" + s;

        return s;
    }


    public String A3(Graph<Integer, VOIntersection, TripleCostEdge> graph1) throws UnexistingVertexException {

        String res ="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

        MSTOnLargestComponentPrim mst = new MSTOnLargestComponentPrim(graph1);

        res+= "Total de vertices en la componente: " + mst.getNumVertices() + "\n";

        for (Iterator<Integer> it = mst.getVertices(); it.hasNext(); ) {
            Integer v = it.next();
            res+= "ID: "+ v + "\n";


        }

        res+="\n Costo total: " + mst.totalCost() + "\n";


        for (IEdge e: mst.getEdges()){

            res+= e+"\n";
        }

        JxMapsB3 maps = new JxMapsB3(mst.getMST().edges());
        maps.initFrame("Requerimiento A3");

        return res;

    }

    public String A2(Graph<Integer, VOIntersection, TripleCostEdge> graph1, int n) throws UnexistingVertexException {

        String res= "\n";
        MinHeap<Pair<Double, VOIntersection>> min = new MinHeap(new Compare());

        for (VOIntersection a : graph1.getVertices()) {

            double sum = 0;
            double count = 0;

            for (IEdge ac : graph1.adjEdges(a.getId())) {
                count++;
                sum += ac.weight(2);
            }
            double prom = sum / count;

            Pair<Double, VOIntersection> pair = new Pair<>(prom, a)

            min.add(pair);
        }

        for (int i =0; i< n;i++){

        }
    }

    private class Compare implements Comparator<Pair<Double, VOIntersection>> {


        @Override
        public int compare(Pair<Double, VOIntersection> o1, Pair<Double, VOIntersection> o2) {
            if (o1.getKey() < o2.getKey() ) return -1;
            if (o1.getKey()  > o2.getKey() ) return 1;
            else return 0;
        }
    }


}
