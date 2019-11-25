package model.algorithms;

import api.IVertice;
import model.data_structures.HashTables.HashTable;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.data_structures.listas.LinkedList;

import java.util.Hashtable;
import java.util.Iterator;

public class CC
{
    private boolean[] marked;
    private int[] id;
    private int count;
    public CC(Grafo G, int size) throws VerticeNoExisteException {
        marked = new boolean[size];
        id = new int[size];


        HashTable vertices = G.getVertices();

       LinkedList listavertices =  vertices.valuesIterator();

        for (Object v: listavertices) {
            IVertice temp = (IVertice) v;

            if (!marked[(int) temp.darId()]) {
                dfs(G, temp);
                count++;
            }
        }
    }

    private void dfs(Grafo G, IVertice v) throws VerticeNoExisteException {
        marked[(int) v.darId()] = true;
        id[(int) v.darId()] = count;

        LinkedList<IVertice> adjs = G.adjVertexes(v.darId());
        if (adjs != null) {
            for (IVertice w : adjs) {

                if (!marked[(int) w.darId()])
                    dfs(G, w);
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