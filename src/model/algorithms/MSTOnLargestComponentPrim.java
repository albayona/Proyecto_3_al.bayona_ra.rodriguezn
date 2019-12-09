package model.algorithms;

import api.IEdge;
import javafx.util.Pair;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.HashTables.HashTable;

import java.util.Comparator;
import java.util.Iterator;

public class MSTOnLargestComponentPrim {

    Integer max;
    PrimMST MST;
    Integer numVertices;

    HashTable<Integer, Integer> inComponent = new HashTable<>(4);

    public MSTOnLargestComponentPrim(Graph G) throws UnexistingVertexException {
        CC cc = new CC(G);
        max = calculateMaxComponent(cc, G.V());

       inComponent = new HashTable<>(4);


        for (int i = 0; i < cc.getId().length; i++){

            if (cc.id(i) == max) inComponent.put(i, 0);
            System.out.print(i+"|" + "\n" + max);
        }

        cc = null;

        MST = new PrimMST(G,  0);

        System.out.print(">>>>>>>>>>>>");
    }

    public Integer calculateMaxComponent(CC cc, int size) {

        HashTable<Integer, Integer> table = new HashTable<Integer, Integer>(4);

        for (int t : cc.getId()) {

            if (!table.containsKey(t)) {
                table.put(t, 1);
            } else {

                int v = table.get(t);
                v++;
                table.put(t, v);
//                System.out.print(v+"-");
            }

        }

        int maxVal = 0;
        int maxKey = 0;

        for (Iterator<Integer> it = table.keysIterator(); it.hasNext(); ) {
            Integer k = it.next();
            Integer val = table.get(k);


            if (val < size /2) {
                if (maxVal < val) {
                    maxVal = val;
                    maxKey = k;
                }
            }


            System.out.print("\n" +val+"\n");
        }
            numVertices = maxVal;

        return maxKey;
    }

    private class Comp implements Comparator<Pair<Integer, Integer>>{


        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            if (o1.getValue() < o2.getValue() ) return -1;
            if (o1.getValue()  > o2.getValue() ) return 1;
            else return 0;
        }
    }





    public PrimMST getMST() {
        return MST;
    }

    public Integer getNumVertices() {
        return numVertices;
    }

    public Iterable<IEdge> getEdges(){
        return  MST.edges();
    }

    public double totalCost(){
        return MST.weight();
    }

    public Iterator<Integer> getVertices(){

        return  inComponent.keysIterator();
    }



}
