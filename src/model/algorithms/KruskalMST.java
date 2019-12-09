package model.algorithms;

import api.IEdge;
import model.data_structures.Graphs.Graph;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Lists.DoublyLinkedList;
import model.data_structures.Lists.Queue;
import model.data_structures.MinHeap.MinHeap;

import java.util.Comparator;

public class KruskalMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;                        // weight of MST
    private Queue<IEdge> mst = new Queue<IEdge>();  // edges in MST
    private int numVertices;
    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public KruskalMST(Graph G, Comparator comp, int component, HashTable<Integer, Integer> table, int numVertices) {
        // more efficient to build heap by passing array of edges
        this.numVertices = numVertices;
        MinHeap<IEdge> pq = new MinHeap<IEdge>(comp);
        for (Object o : G.getEdges()) {
            IEdge e = (IEdge)o;

            if (table.containsKey((int)e.getVertex1().getId()) || table.containsKey((int)e.getVertex2().getId())) {
               System.out.print(e+"\n");
                pq.add(e);
            }
        }

        // run greedy algorithm
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < numVertices-1) {

            System.out.print("Size " + mst.size() + "\n") ;
            IEdge e = pq.min();
            int v = (int)e.either().getId();
            int w = (int)e.other(v).getId();
            if (uf.find(v) != uf.find(w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += (double)e.weight();
            }

        }

        System.out.print("Termino----------------");

    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<IEdge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }


}