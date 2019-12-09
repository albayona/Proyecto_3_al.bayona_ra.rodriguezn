package model.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import api.IEdge;
import com.opencsv.CSVReader;
import model.data_structures.RedBlackTree.RedBlackTree;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.vo.TripleCostEdge;
import model.vo.TravelTime;


import model.vo.VOIntersection;


import model.data_structures.Lists.LinkedList;

public class DataLoader {

    /**
     * EARTH_RADIUS
     */
    private static final int EARTH_RADIUS = 6371;


    private Graph<Integer, VOIntersection, TripleCostEdge> graph1;

    RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> travelTimesTree;
    private int vertices;


    public DataLoader(Graph<Integer, VOIntersection, TripleCostEdge> graph1) {
        super();
        this.graph1 = graph1;
        vertices = 0;


    }


    public Graph<Integer, VOIntersection, TripleCostEdge> getGraph1() {
        return graph1;
    }


    public int getVertices() {
        return vertices;
    }


    public void loadIntersection(String intersectionFile) throws UnexistingVertexException {
        File f = new File(intersectionFile);
        String[] data;
        VOIntersection i1;

        int count = 0;

        try {
            for (String line : Files.readAllLines(Paths.get(f.getPath()))) {

                if (count > 0) {
                    data = line.split(";");
                    System.out.println(data[0]);
                    i1 = new VOIntersection(Integer.parseInt(data[0]), Double.parseDouble(data[2]), Double.parseDouble(data[1]), Integer.parseInt(data[3]));
                    graph1.addVertex(Integer.parseInt(data[0]), i1);
                }
                count++;
            }
        } catch (NumberFormatException | IOException  | UnexistingVertexException e) {
            e.printStackTrace();
        }

       for (int i = 0; i < 228045; i++){

           if (!graph1.hasVertex(i)){

               VOIntersection newV =  new VOIntersection(i, -74, 4, -1);


               graph1.addVertex(i, newV);
           }

       }
 }


    public RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> loadTree(){
        travelTimesTree = new RedBlackTree<>();

        loadTravelTimesByDay();

        return  travelTimesTree;
    }

    /**
     * Carga los arcos
     */
    public void loadEdges(String edgesFile) {





        String csvFile = edgesFile;


        BufferedReader br = null;
        String line = "";
        //Se define separador ","

        String cvsSplitBy = " ";
        try {
            br = new BufferedReader(new FileReader(csvFile));


            int idInic = 0;
            int idFinal = 0;

            VOIntersection interInic = null;
            VOIntersection interFinal = null;
            IEdge edge = null;

            String[] datos;

            double distance;
            double time;
            double velocity;

            while ((line = br.readLine()) != null) {

                datos = line.split(cvsSplitBy);
                idInic = Integer.parseInt(datos[0]);
                interInic = (VOIntersection) graph1.getInfoVertex(idInic);

                System.out.println(idInic);


                try {


                    for (int i = 1; i < datos.length; i++) {

                        idFinal = Integer.parseInt(datos[i]);
                        interFinal = (VOIntersection) graph1.getInfoVertex(idFinal);

                            distance = distance(interInic.getLatitud(), interInic.getLongitude(), interFinal.getLatitud(), interFinal.getLongitude());
                            time = calculateMeanTime(interInic.getZona(), interFinal.getZona());
                            velocity = distance / time;

                            System.out.println(time + " " + distance + " " + velocity);
                            Double[] pesos = new Double[3];

                            pesos[0] = distance;
                            pesos[1] = time;
                            pesos[2] = velocity;

                            edge = new TripleCostEdge(pesos, interInic, interFinal);



                            if (((VOIntersection)edge.getVertex1()).getZona() != -1) {
                                graph1.addEdge(idInic, idFinal, (TripleCostEdge) edge);
                            }
                    }
                }
                catch (NullPointerException e){

                System.out.println("Vertice no encontrado: " + idFinal);
                }


        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnexistingEdgeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    /**
     * @param startLat
     * @param startLong
     * @param endLat
     * @param endLong
     * @return
     */
    public double distance(double startLat, double startLong, double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    /**
     * Metodos Privados----------------------------------------------
     */

    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }



    public void loadTravelTimesByDay( ){

        LinkedList<TravelTime> travelTimes = new LinkedList<>();

        CSVReader reader = null;
        try {

            reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-WeeklyAggregate.csv"));

            Iterator iter = reader.iterator();

            iter.next();

            while (iter.hasNext()){

                String[] params = (String[]) iter.next();

                travelTimes.add(new TravelTime(Integer.parseInt(params[0]), Integer.parseInt(params[1]),Integer.parseInt(params[2]), Double.parseDouble(params[3]),Double.parseDouble(params[4]) ));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        loadTree(travelTimes);
    }

    public  void loadTree(  LinkedList<TravelTime> travelTimes){



        for (TravelTime temp: travelTimes) {
            if (travelTimesTree.contains(temp.getIdSource())){

                RedBlackTree<Integer, LinkedList<TravelTime>> subTree = travelTimesTree.get(temp.getIdSource());

                putInTree(temp, subTree);

                travelTimesTree.put(temp.getIdSource(), subTree);

            }
            else {
                RedBlackTree<Integer, LinkedList<TravelTime>> subTree = new RedBlackTree<>();

                LinkedList<TravelTime> list = new LinkedList<>();

                list.add(temp);

                subTree.put(temp.getIdDestine(), list);

                travelTimesTree.put(temp.getIdSource(), subTree);
            }
        }


    }

    private void putInTree(TravelTime temp, RedBlackTree<Integer, LinkedList<TravelTime>> subTree) {
        if (subTree.contains(temp.getIdDestine())){

            LinkedList<TravelTime> list = subTree.get(temp.getIdDestine());
            list.add(temp);

            subTree.put(temp.getIdDestine(), list);

        }
        else {


            LinkedList<TravelTime> list = new LinkedList<>();

            list.add(temp);
            subTree.put(temp.getIdDestine(), list);
        }
    }

    private double calculateMeanTime(Integer source, Integer destine){

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

            meanTime = total / num;
        }
        else {

            if (source.equals(destine)) meanTime = 10;

            else meanTime = 100;
        }

        return meanTime;

    }


}