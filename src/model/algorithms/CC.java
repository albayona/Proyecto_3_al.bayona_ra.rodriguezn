package model.algorithms;

import api.IVertex;

import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Lists.LinkedList;

import java.util.Hashtable;
import java.util.Iterator;

public class CC
{
    private boolean[] marked;
    private int[] id;
    private int count;
    public CC(Graph G) throws UnexistingVertexException {
        marked = new boolean[G.V()];
        id = new int[G.V()];



        for (Object v: G.getVertices()) {
            IVertex temp = (IVertex) v;

            if (!marked[(int) temp.getId()]) {

                DFS dfs = new DFS(G, (Integer) temp.getId(), marked, count, id);
                count++;
            }
        }
    }




    public boolean connected(int v, int w)
    { return id[v] == id[w]; }
    public int id(int v)
    { return id[v]; }
    public int count()
    { return count; }

    public int[] getId() {
        return id;
    }
}