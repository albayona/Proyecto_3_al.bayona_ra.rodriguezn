package model.algorithms;

import java.util.Iterator;
import java.util.LinkedList;

import api.IVertex;
import junit.framework.TestCase;
import model.algorithms.DijkstraQueue;
import model.data_structures.EdgeTest;
import model.data_structures.VertexTest;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;

public class DijkstraTest extends TestCase {

	private	DijkstraSP djk;

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

		graph = new Graph();
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

		EdgeTest a = new EdgeTest(10000, list.get(0), list.get(1));
		EdgeTest a0 = new EdgeTest(1000, list.get(0), list.get(2));
		EdgeTest a1 = new EdgeTest(500, list.get(0), list.get(4));
		EdgeTest a2 = new EdgeTest(100, list.get(2), list.get(6));
		EdgeTest a3 = new EdgeTest(50, list.get(2), list.get(7));
		EdgeTest a4 = new EdgeTest(10, list.get(3), list.get(9));
		EdgeTest a5 = new EdgeTest(5, list.get(5), list.get(9));
		EdgeTest a6 = new EdgeTest(1,list.get(8), list.get(10));
		EdgeTest a7 = new EdgeTest(1,list.get(7), list.get(8));
		EdgeTest a8 = new EdgeTest(1,list.get(4), list.get(10));

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

		djk = new DijkstraSP(graph, graph.V());
		djk.crearCaminoDesdeVertice(list.get(0));
		model.data_structures.Lists.LinkedList path = djk.getPath(list.get(10));

		assertNotNull(path);
		assertTrue(path.getSize() > 0);

		Iterator<IVertex> ite = path.iterator();
		
		while(ite.hasNext())
			System.out.println(ite.next().getId());
	
	}




}
