package model.algorithms;

import api.IEdge;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Lists.Queue;
import model.data_structures.MinHeapIndexed.IndexMinPQ;

public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private IEdge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
    private double[] distTo;      // distTo[v] = weight of shortest such edge
    private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
    private IndexMinPQ<Double> pq;
    private int weightIndex;

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public PrimMST(Graph G, int weightIndex, HashTable<Integer, Integer> inComponent) throws UnexistingVertexException {
        this.weightIndex = weightIndex;
        edgeTo = new IEdge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)      // run from each vertex to find

            if (!marked[v] && inComponent.containsKey(v)) prim(G, v, inComponent);      // minimum spanning forest

    }

    // run Prim's algorithm in graph G, starting from vertex s
    private void prim(Graph G, int s, HashTable<Integer, Integer> inComponent) throws UnexistingVertexException {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();

            if (inComponent.containsKey(v))
            scan(G, v);
        }
    }

    // scan vertex v
    private void scan(Graph G, int v) throws UnexistingVertexException {
        marked[v] = true;
        for (Object o : G.adjEdges(v)) {
            IEdge e = (IEdge) o;

            int w = (int) e.other(v).getId();
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight(weightIndex) < distTo[w]) {
                distTo[w] = e.weight(weightIndex);
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<IEdge> edges() {
        Queue<IEdge> mst = new Queue<IEdge>();
        for (int v = 0; v < edgeTo.length; v++) {
            IEdge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public IEdge edgeTo(int v){
        return edgeTo[v];
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        double weight = 0.0;
        for (IEdge e : edges())
            weight += (double)e.weight();
        return weight;
    }

}