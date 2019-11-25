package model.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import api.IArco;
import com.opencsv.CSVReader;
import model.data_structures.HashTables.HashTable;
import model.data_structures.RedBlackTree.RedBlackTree;
import model.data_structures.grafo1.ArcoNoExisteException;
import model.data_structures.grafo1.ArcoYaExisteException;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.vo.TripleCost;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.vo.TravelTime;


import api.IVertice;

import model.vo.VOInterseccion;


import model.data_structures.listas.LinkedList;

public class TripsManager {

    /**
     * EARTH_RADIUS
     */
    private static final int EARTH_RADIUS = 6371;


    private Grafo<Integer, IVertice<Integer>, TripleCost> grafo1;
    private LinkedList<TravelTime> travelTimes;
    private RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> tree;
    private int arcosMixtos;
    private int vertices;


    public TripsManager() {
        super();
        grafo1 = new Grafo<>();
        arcosMixtos = 0;
        vertices = 0;
    }


    public Grafo<Integer, IVertice<Integer>, TripleCost> getGrafo1() {
        return grafo1;
    }

    public int getArcosMixtos() {
        return arcosMixtos;
    }

    public int getVertices() {
        return vertices;
    }


    public void loadIntersection(String intersectionFile) {
        File f = new File(intersectionFile);
        String[] datos;
        VOInterseccion i1;

        int count = 0;

        try {
            for (String line : Files.readAllLines(Paths.get(f.getPath()))) {

                if (count > 0) {
                    datos = line.split(";");
                    System.out.println(datos[0]);
                    i1 = new VOInterseccion(Integer.parseInt(datos[0]), Double.parseDouble(datos[2]), Double.parseDouble(datos[1]), Integer.parseInt(datos[3]));
                    grafo1.addVertex(Integer.parseInt(datos[0]), i1);
                }
                count++;
            }
        } catch (NumberFormatException | IOException  | VerticeNoExisteException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carga los arcos
     */
    public void loadEdges(String edgesFile) {
        loadTravelTimesByDay();
        loadTree();

        String csvFile = edgesFile;


        BufferedReader br = null;
        String line = "";
        //Se define separador ","

        String cvsSplitBy = " ";
        try {
            br = new BufferedReader(new FileReader(csvFile));


            int idInic = 0;
            int idFinal = 0;

            VOInterseccion interInic = null;
            VOInterseccion interFinal = null;
            IArco arco = null;

            String[] datos;

            double distance;
            double time;
            double velocity;

            while ((line = br.readLine()) != null) {

                datos = line.split(cvsSplitBy);
                idInic = Integer.parseInt(datos[0]);
                interInic = (VOInterseccion) grafo1.getInfoVertex(idInic);

                System.out.println(idInic);


                try {


                    for (int i = 1; i < datos.length; i++) {

                        idFinal = Integer.parseInt(datos[i]);
                        interFinal = (VOInterseccion) grafo1.getInfoVertex(idFinal);

                            distance = distance(interInic.getLatitud(), interInic.getLongitud(), interFinal.getLatitud(), interFinal.getLongitud());
                            time = calculateMeanTime(interInic.getZona(), interFinal.getZona());
                            velocity = distance / time;

                            System.out.println(time + " " + distance + " " + velocity);
                            Double[] pesos = new Double[3];

                            pesos[0] = distance;
                            pesos[1] = time;
                            pesos[2] = velocity;

                            arco = new TripleCost(pesos, interInic, interFinal);

                            grafo1.addEdge(idInic, idFinal, (TripleCost) arco);

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
        } catch (ArcoNoExisteException e) {
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

    //==========================================================
    //GUARDAR GRAFO JSON
    //==========================================================

    /**
     * Guarda el grafo en formato JSon en el archivo que pasa por parametro
     *
     * @param ruta La ruta donde se desea guardar el archivo
     */
    public void guardarGrafoJSON(String ruta) {

        JSONObject js = darJsonObjectDeGrafo(grafo1);

        try {
            FileWriter file = new FileWriter(ruta);
            js.writeJSONString(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     * @param grafo
     */
    @SuppressWarnings("unchecked")
    private JSONObject darJsonObjectDeGrafo(Grafo<Integer, IVertice<Integer>, TripleCost> grafo) {
        JSONObject jsGrafo = new JSONObject(); //principal

        JSONArray jsIntercecionesVertices = new JSONArray();
        intersecionesVertices(grafo, jsIntercecionesVertices);
        JSONArray jsArcos = darArcos(grafo);

        jsGrafo.put("numVertices", grafo.V());
        jsGrafo.put("intersecciones", jsIntercecionesVertices);
        jsGrafo.put("arcos", jsArcos);

        return jsGrafo;
    }

    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     * @param grafo
     */
    @SuppressWarnings("unchecked")
    private JSONArray darArcos(Grafo<Integer, IVertice<Integer>, TripleCost> grafo) {
        JSONArray js = new JSONArray();

        HashTable<String, TripleCost> lista = grafo.getArcos();
        Iterator<String> it = lista.keysIterator();
        JSONObject arcoJSON;
        TripleCost arco = null;

        while (it.hasNext()) {
            arco = lista.get(it.next());
            arcoJSON = new JSONObject();
            JSONArray pesos = new JSONArray();

            pesos.add(arco.darPeso()[0]);
            pesos.add(arco.darPeso()[1]);
            pesos.add(arco.darPeso()[2]);


            arcoJSON.put("pesos", pesos);
            arcoJSON.put("id_Vertice_1", arco.getVertice1().darId());
            arcoJSON.put("id_Vertice_2", arco.getVertice2().darId());
            js.add(arcoJSON);
        }

        return js;
    }


    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     */
    @SuppressWarnings("unchecked")
    private void intersecionesVertices(Grafo<Integer, IVertice<Integer>, TripleCost> grafo, JSONArray intersecciones) {

        HashTable<Integer, IVertice<Integer>> lista = grafo.getVertices();

        Iterator<Integer> it = lista.keysIterator();
        JSONObject verticeAdd;

        while (it.hasNext()) {
            IVertice verticeActual = lista.get(it.next());

            if (verticeActual instanceof VOInterseccion) {
                verticeAdd = darJsObjectInterseccion((VOInterseccion) verticeActual);
                intersecciones.add(verticeAdd);
            }
        }
    }


    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     */
    @SuppressWarnings("unchecked")
    private JSONObject darJsObjectInterseccion(VOInterseccion vertice) {
        JSONObject js = new JSONObject();

        js.put("id", vertice.darId());
        js.put("latitud", vertice.getLatitud());
        js.put("longitude", vertice.getLongitud());
        js.put("zona", vertice.getZona());
        return js;
    }

    //==========================================================
    //CARGAR GRAFO JSON
    //==========================================================

    /**
     * Carga el grafo del mundo del archivo que se pasa por parametro
     */
    public void cargarGrafoJSON(String jsonRoute) {

        FileReader file;
        try {
            file = new FileReader(jsonRoute);
            JSONParser parser = new JSONParser();
            JSONObject js = (JSONObject) parser.parse(file);

            grafo1 = darGrafoDeJSonObject(js, grafo1);
            file.close();
        } catch (IOException | ParseException | VerticeNoExisteException | ArcoYaExisteException | ArcoNoExisteException e) {

            e.printStackTrace();
        }
    }

    private Grafo<Integer, IVertice<Integer>, TripleCost> darGrafoDeJSonObject(JSONObject js, Grafo<Integer, IVertice<Integer>, TripleCost> grafo) throws  VerticeNoExisteException, ArcoYaExisteException, ArcoNoExisteException {

        JSONArray inters = (JSONArray) js.get("intersecciones");
        JSONArray arcos = (JSONArray) js.get("arcos");
        cargarInterseccionesAGrafo(inters, grafo);
        cargarArcosAGrafo(arcos, grafo);

        return grafo;
    }

    private void cargarArcosAGrafo(JSONArray arcos, Grafo<Integer, IVertice<Integer>, TripleCost> grafo) throws VerticeNoExisteException, ArcoYaExisteException, ArcoNoExisteException {
        TripleCost a = null;
        JSONObject obj;
        for (int i = 0; i < arcos.size(); i++) {
            obj = (JSONObject) arcos.get(i);
            cargarArcoAGrafo(obj, grafo, a);
        }
    }

    private void cargarArcoAGrafo(JSONObject obj, Grafo<Integer, IVertice<Integer>, TripleCost> grafo, TripleCost a) throws VerticeNoExisteException, ArcoYaExisteException, ArcoNoExisteException {

        JSONArray pesosJs = (JSONArray) obj.get("pesos");


        Double[] pesos = new Double[3];

        int i = 0;
        for (Object peso: pesosJs){

            pesos[i] = (Double) peso;
        i++;
        }

        int vertice1 = (int) (long) obj.get("id_Vertice_1");
        int vertice2 = (int) (long) obj.get("id_Vertice_2");
        a = new   TripleCost(pesos, grafo.getInfoVertex(vertice1), grafo.getInfoVertex(vertice2));
        grafo.addEdge(vertice1, vertice2, a);
    }


    private void cargarInterseccionesAGrafo(JSONArray inters, Grafo<Integer, IVertice<Integer>, TripleCost> grafo) throws VerticeNoExisteException {

        VOInterseccion vo = null;
        for (int i = 0; i < inters.size(); i++) {
            JSONObject obj = (JSONObject) inters.get(i);
            cargarInterseccionAGrafo(obj, grafo, vo);
        }
    }

    private void cargarInterseccionAGrafo(JSONObject obj, Grafo<Integer, IVertice<Integer>, TripleCost> grafo, VOInterseccion vo)  throws VerticeNoExisteException {

        double latitud = (double) obj.get("latitud");
        double longitud = (double) obj.get("longitude");
        long id = (long) obj.get("id");
        long zona = (long) obj.get("zona");

        Integer id1 = (int) id;
        Integer z1 = (int) id;
        vo = new VOInterseccion(id1, latitud, longitud, z1);
        grafo.addVertex(id1, vo);
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



    public void loadTravelTimesByDay(){

        travelTimes = new LinkedList<>();
        CSVReader reader = null;
        try {

            reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));

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
    }

    public  void loadTree(){

        tree = new RedBlackTree<>();

        for (TravelTime temp: travelTimes) {
            if (tree.contains(temp.getIdSource())){

                RedBlackTree<Integer, LinkedList<TravelTime>> subTree = tree.get(temp.getIdSource());

                putInTree(temp, subTree);

                tree.put(temp.getIdSource(), subTree);

            }
            else {
                RedBlackTree<Integer, LinkedList<TravelTime>> subTree = new RedBlackTree<>();

                LinkedList<TravelTime> list = new LinkedList<>();

                list.add(temp);

                subTree.put(temp.getIdDestine(), list);

                tree.put(temp.getIdSource(), subTree);
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
        RedBlackTree<Integer, LinkedList<TravelTime>> t1 = tree.get(source);

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