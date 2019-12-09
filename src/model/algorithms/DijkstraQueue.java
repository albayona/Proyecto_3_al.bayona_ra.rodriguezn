package model.algorithms;

import java.util.Iterator;


import api.IEdge;
import api.IVertex;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Lists.LinkedList;
import model.data_structures.MinHeapIndexed.IndexMinHeap;
import model.data_structures.MinHeapIndexed.IndexNode;


//sacado de https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-using-priority_queue-stl/

public class DijkstraQueue<K extends Comparable<K>, V extends IVertex<K>, A extends IEdge> {

    private HashTable<K, V> vertices;
    private HashTable<String, A> arcos;

    private HashTable<IVertex, IVertex> settledNodes;
    //private LinkedList<IVertice> settledNodes;
    //private LinkedList<IVertice> unSettledNodes;
    private IndexMinHeap<Integer, IVertex<Integer>> unSettledNodes;

    private Integer[] dist;

    private HashTable<IVertex<Integer>, IVertex<Integer>> predecessors;

    private Graph grafo;

    public DijkstraQueue(Graph graph) {
        // create a copy of the array so that we can operate on this array
        vertices = graph.getVertices();
        arcos = graph.getEdges();
        grafo = graph;

    }

    public void crearCaminoDesdeVertice(IVertex<Integer> source) {
        //La llave K de Ivertice tiene que ser de tipo Integer
        unSettledNodes = new IndexMinHeap<Integer, IVertex<Integer>>(vertices.sizeKeys());
        predecessors = new HashTable<IVertex<Integer>, IVertex<Integer>>(100);
        settledNodes = new HashTable<IVertex, IVertex>(100);
        dist = new Integer[400700];
        Iterator iter = vertices.keysIterator();
        while(iter.hasNext()) {
            dist[(int) iter.next()] = Integer.MAX_VALUE;
        }

        dist[source.getId()] = 0;
        try {unSettledNodes.agregar(new Integer(0), source);} catch (Exception e1) {e1.printStackTrace();}
        IndexNode<Integer, IVertex<Integer>> node = null;
        int u = -1;
        while (unSettledNodes.darNumElementos() > 0) {
            node = unSettledNodes.min();
            settledNodes.put(node.getValue(), node.getValue());
            u = node.getValue().getId();

            try {findMinimalDistances(node, u);} catch (Exception e) {	e.printStackTrace();}

            //System.out.println("Actual Size: "+unSettledNodes.darNumElementos());
        }
    }

    private void findMinimalDistances(IndexNode<Integer, IVertex<Integer>> node, int u) throws Exception {
        LinkedList<IVertex> adjacentNodes = getNeighbors(node);
        int distanciaActual = 0;
        int distanciaEvaluada = 0;

        for (IVertex target : adjacentNodes) {
            distanciaActual = dist[(int) target.getId()];
            distanciaEvaluada = dist[u] + getDistance(target, node.getValue());
            if(distanciaActual > distanciaEvaluada) {
                dist[(int) target.getId()] = distanciaEvaluada;
                unSettledNodes.agregar(distanciaEvaluada, target);
                predecessors.put(target, node.getValue());
            }
        }

    }

    private int getDistance(IVertex node, IVertex<Integer> other) throws Exception {
        IEdge arco = null;
        try {
            arco = grafo.getEdgeInfo(node.getId(), other.getId());
        } catch (Exception e) {
            arco = grafo.getEdgeInfo(other.getId(), node.getId());
        }
        if(arco == null) {
            throw new Exception("No existe el arco. Debería exitir");
        }
        else
            return (int) arco.weight();
    }

    private LinkedList<IVertex> getNeighbors(IndexNode<Integer, IVertex<Integer>> node) {
        LinkedList<IVertex> neighbors = new LinkedList<IVertex>();
        try {
           LinkedList<IVertex> adj = grafo.adjVertices(node.getValue().getId());
            for(IVertex c : adj) {
                if (!isSettled(c)) {
                    neighbors.add(c);
                }
            }

        } catch (  UnexistingVertexException e) {e.printStackTrace();}

        return neighbors;
    }


    private boolean isSettled(IVertex vertex) {
        return settledNodes.containsKey(vertex);
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<IVertex> getPath(IVertex target) {
       LinkedList<IVertex> path = new LinkedList<IVertex>();
        IVertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        return path;
    }


}
