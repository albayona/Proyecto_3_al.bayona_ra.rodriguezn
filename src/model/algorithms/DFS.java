package model.algorithms;

import api.IEdge;
import api.IVertex;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.LinkedList;
import model.data_structures.Lists.Stack;


public class DFS {

    Stack<Integer> stack;

    public  DFS(Graph G, int s, boolean[] marked, int count, int[] id  ) throws UnexistingVertexException {

        stack = new Stack<>();

        stack.push(s);



        while (!stack.isEmpty()) {

            id[s] = count;

            s = stack.pop();

            System.out.print(s);
            if (marked[s]) continue;

            marked[s] = true;

            LinkedList<IVertex> adjs = G.adjVertices(s);

            for (IVertex v : adjs) {

                if (!marked[(int) v.getId()]) {
                    stack.push((int) v.getId());
                }
            }
        }

    }
}
