package model.algorithms;

import api.IVertex;
import junit.framework.TestCase;
import model.data_structures.DiEdgeTest;
import model.data_structures.VertexTest;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.Digraph;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.LinkedList;

public class KosarajuTest extends TestCase {

	private Digraph<Integer, VertexTest, DiEdgeTest> grafo;
	private KosarajuSCC ksj;
	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		grafo = new Digraph<Integer, VertexTest, DiEdgeTest>();
	}


	//------------------------------------------------------
	//pruebas
	//------------------------------------------------------

	public void testDijk() {

		setUpEscenario0();
		LinkedList<VertexTest> list = new LinkedList<VertexTest>();

		for (int i = 0; i < 20; i++) {
			VertexTest v = new VertexTest(i);
			list.add(v);
			try {
				grafo.addVertex(v.getId(), v);
			} catch (UnexistingVertexException e) {
				e.printStackTrace();
			}
		}

		DiEdgeTest a = new DiEdgeTest(10000, list.getElement(0), list.getElement(1));
		DiEdgeTest a0 = new DiEdgeTest(1000, list.getElement(1), list.getElement(0));
		DiEdgeTest a3 = new DiEdgeTest(50, list.getElement(1), list.getElement(3));
		DiEdgeTest a1 = new DiEdgeTest(500, list.getElement(1), list.getElement(2));
		DiEdgeTest a2 = new DiEdgeTest(100, list.getElement(2), list.getElement(1));
		DiEdgeTest a4 = new DiEdgeTest(10, list.getElement(3), list.getElement(0));

		DiEdgeTest a5 = new DiEdgeTest(500, list.getElement(4), list.getElement(5));
		DiEdgeTest a6 = new DiEdgeTest(1,list.getElement(5), list.getElement(4));
		DiEdgeTest a7 = new DiEdgeTest(900,list.getElement(5), list.getElement(6));
		DiEdgeTest a9 = new DiEdgeTest(1,list.getElement(5), list.getElement(7));
		DiEdgeTest a8 = new DiEdgeTest(1,list.getElement(6), list.getElement(5));
		DiEdgeTest a10 = new DiEdgeTest(1,list.getElement(7), list.getElement(4));

		DiEdgeTest a11 = new DiEdgeTest(1,list.getElement(8), list.getElement(9));
		DiEdgeTest a12 = new DiEdgeTest(1,list.getElement(9), list.getElement(8));
		DiEdgeTest a13 = new DiEdgeTest(1,list.getElement(9), list.getElement(10));
		DiEdgeTest a14 = new DiEdgeTest(1,list.getElement(9), list.getElement(11));
		DiEdgeTest a15 = new DiEdgeTest(1,list.getElement(10), list.getElement(9));
		DiEdgeTest a16 = new DiEdgeTest(1,list.getElement(11), list.getElement(8));

		try {
			grafo.addEdge(0, 1, a);
			grafo.addEdge(1, 0, a0);
			grafo.addEdge(1, 3, a3);
			grafo.addEdge(1, 2, a1);
			grafo.addEdge(2, 1, a2);
			grafo.addEdge(3, 0, a4);
			
			
			grafo.addEdge(4, 5, a5);
			grafo.addEdge(5, 4, a6);
			grafo.addEdge(5, 6, a7);
			grafo.addEdge(5, 7, a9);
			grafo.addEdge(6, 5, a8);
			grafo.addEdge(7, 4, a10);

			grafo.addEdge(8, 9, a11);
			grafo.addEdge(9, 8, a12);
			grafo.addEdge(9, 10, a13);
			grafo.addEdge(9, 11, a14);
			grafo.addEdge(10, 9, a15);
			grafo.addEdge(11, 8, a16);
			
			ksj = new KosarajuSCC(grafo);
			
			LinkedList<VertexTest> lista = grafo.getVertices().valuesIterator();
			
			for(IVertex v : lista) {
				System.out.println("ID del vertice: "+v.getId());
				System.out.println("Color del vertice: " + v.getColor());
			}
			System.out.println("Num componenetes: " + ksj.count());
					

		} catch (UnexistingEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
