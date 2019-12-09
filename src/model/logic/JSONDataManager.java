package model.logic;

import api.IVertex;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.RepeatedEdgeException;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.UnexistingVertexException;
import model.vo.TripleCostEdge;
import model.vo.VOIntersection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class JSONDataManager {

    //==========================================================
    //GUARDAR GRAFO JSON
    //==========================================================

    /**
     * Guarda el grafo en formato JSon en el archivo que pasa por parametro
     *
     * @param ruta La ruta donde se desea guardar el archivo
     */
    public void saveGraphIntoJSON(String ruta, Graph<Integer, VOIntersection, TripleCostEdge> graph1) {

        JSONObject js = getGraphJSONObject(graph1);

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
     * @param graph
     */
    @SuppressWarnings("unchecked")
    private JSONObject getGraphJSONObject(Graph<Integer, VOIntersection, TripleCostEdge> graph) {
        JSONObject jsGraph = new JSONObject(); //principal

        JSONArray jsVertices = new JSONArray();
        getVertices(graph, jsVertices);
        JSONArray jsEdges = getEdges(graph);

        jsGraph.put("numVertices", graph.V());
        jsGraph.put("intersecciones", jsVertices);
        jsGraph.put("arcos", jsEdges);

        return jsGraph;
    }

    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     * @param graph
     */
    @SuppressWarnings("unchecked")
    private JSONArray getEdges(Graph<Integer, VOIntersection, TripleCostEdge> graph) {
        JSONArray js = new JSONArray();

        HashTable<String, TripleCostEdge> list = graph.getEdges();
        Iterator<String> it = list.keysIterator();
        JSONObject JSONEdge;
        TripleCostEdge edge = null;

        while (it.hasNext()) {
            edge = list.get(it.next());
            JSONEdge = new JSONObject();
            JSONArray weights = new JSONArray();

            weights.add(edge.weight(0));
            weights.add(edge.weight(1));
            weights.add(edge.weight(2));


            JSONEdge.put("pesos", weights);
            JSONEdge.put("id_Vertice_1", edge.getVertex1().getId());
            JSONEdge.put("id_Vertice_2", edge.getVertex2().getId());
            js.add(JSONEdge);
        }

        return js;
    }


    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     */
    @SuppressWarnings("unchecked")
    private void getVertices(Graph<Integer, VOIntersection, TripleCostEdge> graph, JSONArray vertices) {

        HashTable<Integer, VOIntersection> list = graph.getVertices();

        Iterator<Integer> it = list.keysIterator();
        JSONObject vertexToAdd;

        while (it.hasNext()) {
            IVertex tempVertex = list.get(it.next());

            if (tempVertex instanceof VOIntersection) {
                vertexToAdd = getJsEdgeObject((VOIntersection) tempVertex);
                vertices.add(vertexToAdd);
            }
        }
    }


    /**
     * Metodo auxiliar para guardar el grafo en un archivo
     */
    @SuppressWarnings("unchecked")
    private JSONObject getJsEdgeObject(VOIntersection vertex) {
        JSONObject js = new JSONObject();

        js.put("id", vertex.getId());
        js.put("latitud", vertex.getLatitud());
        js.put("longitude", vertex.getLongitude());
        js.put("zona", vertex.getZona());
        return js;
    }

    //==========================================================
    //CARGAR GRAFO JSON
    //==========================================================

    /**
     * Carga el grafo del mundo del archivo que se pasa por parametro
     */
    public void loadGraphFromJSON(String jsonRoute, Graph<Integer, VOIntersection, TripleCostEdge> graph1) {

        FileReader file;
        try {
            file = new FileReader(jsonRoute);
            JSONParser parser = new JSONParser();
            JSONObject js = (JSONObject) parser.parse(file);

            graph1 = getGraphFromJSONObject(js, graph1);
            file.close();
        } catch (IOException | ParseException | UnexistingVertexException | RepeatedEdgeException | UnexistingEdgeException e) {

            e.printStackTrace();
        }
    }

    private Graph<Integer,VOIntersection, TripleCostEdge> getGraphFromJSONObject(JSONObject js, Graph<Integer,VOIntersection, TripleCostEdge> graph) throws UnexistingVertexException, RepeatedEdgeException, UnexistingEdgeException {

        JSONArray vertices = (JSONArray) js.get("intersecciones");
        JSONArray edges = (JSONArray) js.get("arcos");
        loadVertices(vertices, graph);
        loadEdges(edges, graph);

        return graph;
    }

    private void loadEdges(JSONArray arcos, Graph<Integer, VOIntersection, TripleCostEdge> graph) throws UnexistingVertexException, RepeatedEdgeException, UnexistingEdgeException {
        TripleCostEdge a = null;
        JSONObject obj;
        for (int i = 0; i < arcos.size(); i++) {
            obj = (JSONObject) arcos.get(i);
            loadEdge(obj, graph, a);
        }
    }

    private void loadEdge(JSONObject obj, Graph<Integer, VOIntersection, TripleCostEdge> graph, TripleCostEdge a) throws UnexistingVertexException, RepeatedEdgeException, UnexistingEdgeException {

        JSONArray weightsJs = (JSONArray) obj.get("pesos");


        Double[] weights = new Double[3];

        int i = 0;
        for (Object peso: weightsJs){

            weights[i] = (Double) peso;
            i++;
        }

        int vertex1 = (int) (long) obj.get("id_Vertice_1");
        int vertex2 = (int) (long) obj.get("id_Vertice_2");
        a = new TripleCostEdge(weights, graph.getInfoVertex(vertex1), graph.getInfoVertex(vertex2));
        graph.addEdge(vertex1, vertex2, a);
    }


    private void loadVertices(JSONArray inters, Graph<Integer,VOIntersection, TripleCostEdge> graph) throws UnexistingVertexException {

        VOIntersection vo = null;
        for (int i = 0; i < inters.size(); i++) {
            JSONObject obj = (JSONObject) inters.get(i);
            loadVertex(obj, graph, vo);
        }
    }

    private void loadVertex(JSONObject obj, Graph<Integer, VOIntersection, TripleCostEdge> graph, VOIntersection vo)  throws UnexistingVertexException {

        double latitud = (double) obj.get("latitud");
        double longitude = (double) obj.get("longitude");
        long id = (long) obj.get("id");
        long zone = (long) obj.get("zona");

        Integer id1 = (int) id;
        Integer z1 = (int) zone;
        vo = new VOIntersection(id1, latitud, longitude, z1);
        graph.addVertex(id1, vo);
    }
}
