package model.algorithms;

import api.IVertice;
import model.data_structures.HashTables.HashTable;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.data_structures.listas.LinkedList;
import model.data_structures.listas.Queue;
import model.data_structures.listas.Stack;

//Sacado de Algorithms 4th edition Princeton
public class BreadthFirstPaths<K extends Comparable<K>, V extends IVertice<K>> {

	private boolean[] marked;  // Is a shortest path to this vertex known? 
	private int[] edgeTo;     // last vertex on known path to this vertex
	private int s;      // source

	private HashTable<Integer, IVertice<Integer>> vertices;
	private Grafo G;


	public BreadthFirstPaths(Grafo G, int size) {      
		marked = new boolean[size];      
		edgeTo = new int[size];   
		vertices = G.getVertices();
		this.G = G;
		s = 0;
	} 
	
	public void bfs(IVertice<Integer> verticeInicial) {
		this.s = (int) verticeInicial.darId();      
		try {
			bfs(G, s);
		} catch (VerticeNoExisteException e) {e.printStackTrace();}   

	}

	private void bfs(Grafo G, int s) throws VerticeNoExisteException {      
		Queue<Integer> queue = new Queue<Integer>();
		LinkedList<IVertice<Integer>> adj = null;
		Integer w = 0;
		marked[s] = true;          // Mark the source      
		queue.enqueue(s);          //   and put it on the queue.      
		while (!queue.isEmpty()) {
			int v = queue.dequeue(); // Remove next vertex from the queue.         
			adj = G.adjVertexes(v);
			for (IVertice<Integer> vertice : adj)  {  
				w = (Integer) vertice.darId();
				if (!marked[w]) {  // For every unmarked adjacent vertex,           
					edgeTo[w] = v;     //   save last edge on a shortest path,              
					marked[w] = true;  //   mark it because path is known,              
					queue.enqueue(w);  //   and add it to the queue.            
				}      
			}
		}
	}

	public boolean hasPathTo(int v)   {  
		return marked[v];  
	} 

	public Iterable<IVertice<Integer>> pathTo(IVertice<Integer> verticeFinal)   {
		int v = (int) verticeFinal.darId();      
		if (!hasPathTo(v)) return null;      
		Queue<IVertice<Integer>> path = new Queue<IVertice<Integer>>();      
		for (int x = v; x != s; x = edgeTo[x])        
			path.enqueue(vertices.get(x));      
		path.enqueue(vertices.get(s));      
		return path; 

	}



}
