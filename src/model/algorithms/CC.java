package model.algorithms;

import api.IVertice;
import model.data_structures.HashTables.HashTable;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;

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

        Iterator iter = (Iterator) vertices.valuesIterator().iterator();

        while (iter.hasNext()) {
            IVertice temp = (IVertice) iter.next();

            if (!marked[(int) temp.darId()]) {
                dfs(G, temp);
                count++;
            }
        }
    }

    private void dfs(Grafo G, IVertice v)  {
        marked[(int) v.darId()] = true;
        id[(int) v.darId()] = count;
        IVertice w = null;
        try {
        for (int i = 0; i < G.adjVertexes(v).getSize(); i++) {

                w = (IVertice) G.adjVertexes(v).getElement(i);
                if (!marked[(int) w.darId()])
                    dfs(G, w);
            }
        }
                    catch (VerticeNoExisteException e){
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