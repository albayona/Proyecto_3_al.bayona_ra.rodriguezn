package model.algorithms;

import java.util.Iterator;

import api.IVertex;
import junit.framework.TestCase;
import model.data_structures.EdgeTest;
import model.data_structures.VertexTest;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.LinkedList;
import model.data_structures.Lists.Queue;

public class BFSTest extends TestCase {

	private BreadthFirstPaths bfs;
	
	private Graph<Integer, VertexTest, EdgeTest> graph;
	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		graph = new Graph<Integer, VertexTest, EdgeTest>();
	}


	//------------------------------------------------------
	//pruebas
	//------------------------------------------------------

	public void testDijk() {

		setUpEscenario0();
		LinkedList<VertexTest> list = new LinkedList<VertexTest>();

		for (int i = 0; i < 11; i++) {
			VertexTest v = new VertexTest(i);
			list.add(v);
			try {
				graph.addVertex(v.getId(), v);
			} catch (UnexistingVertexException e) {
				e.printStackTrace();
			}
		}

		EdgeTest a = new EdgeTest(10000, list.getElement(0), list.getElement(1));
		EdgeTest a0 = new EdgeTest(1000, list.getElement(0), list.getElement(2));
		EdgeTest a1 = new EdgeTest(500, list.getElement(0), list.getElement(4));
		EdgeTest a2 = new EdgeTest(100, list.getElement(2), list.getElement(6));
		EdgeTest a3 = new EdgeTest(50, list.getElement(2), list.getElement(7));
		EdgeTest a4 = new EdgeTest(10, list.getElement(3), list.getElement(9));
		EdgeTest a5 = new EdgeTest(5, list.getElement(5), list.getElement(9));
		EdgeTest a6 = new EdgeTest(1,list.getElement(8), list.getElement(10));
		EdgeTest a7 = new EdgeTest(1,list.getElement(7), list.getElement(8));
		EdgeTest a8 = new EdgeTest(1,list.getElement(4), list.getElement(10));

		try {
			graph.addEdge(0, 1, a);
			graph.addEdge(0, 2, a0);
			graph.addEdge(0, 4, a1);
			graph.addEdge(2, 6, a2);
			graph.addEdge(2, 7, a3);
			graph.addEdge(3, 9, a4);
			graph.addEdge(5, 9, a5);
			graph.addEdge(8, 10, a6);
			graph.addEdge(7, 8, a7);
			graph.addEdge(4, 10, a8);

		} catch (UnexistingEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bfs = new BreadthFirstPaths<>(graph, graph.V());
		bfs.bfs(list.getElement(0));
		Queue<IVertex<Integer>> path = (Queue<IVertex<Integer>>) bfs.pathTo(list.lastElement());

		assertNotNull(path);
		assertTrue(path.size() > 0);

		Iterator<IVertex<Integer>> ite = path.iterator();
		
		while(ite.hasNext())
			System.out.println(ite.next().getId());
	
	}

}
