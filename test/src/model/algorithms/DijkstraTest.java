package model.algorithms;

import java.util.Iterator;
import java.util.LinkedList;

import api.IArco;
import api.IVertice;
import junit.framework.TestCase;
import model.algorithms.DijkstraQueue;
import model.data_structures.ArcoTest;
import model.data_structures.VerticeTest;
import model.data_structures.grafo1.ArcoNoExisteException;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;

public class DijkstraTest extends TestCase {

	private	DijkstraSP djk;

	private Grafo<Integer, VerticeTest, ArcoTest> grafo;


	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		grafo = new Grafo<Integer, VerticeTest, ArcoTest>();
	}

	//------------------------------------------------------
	//pruebas
	//------------------------------------------------------

	public void testDijk() {

		grafo = new Grafo();
		LinkedList<VerticeTest> list = new LinkedList<VerticeTest>();

		for (int i = 0; i < 11; i++) {
			VerticeTest v = new VerticeTest(i);
			list.add(v);
			try {
				grafo.addVertex(v.darId(), v);
			} catch (VerticeNoExisteException e) {
				e.printStackTrace();
			}
		}

		ArcoTest a = new ArcoTest(10000, list.get(0), list.get(1));
		ArcoTest a0 = new ArcoTest(1000, list.get(0), list.get(2));
		ArcoTest a1 = new ArcoTest(500, list.get(0), list.get(4));
		ArcoTest a2 = new ArcoTest(100, list.get(2), list.get(6));
		ArcoTest a3 = new ArcoTest(50, list.get(2), list.get(7));
		ArcoTest a4 = new ArcoTest(10, list.get(3), list.get(9));
		ArcoTest a5 = new ArcoTest(5, list.get(5), list.get(9));
		ArcoTest a6 = new ArcoTest(1,list.get(8), list.get(10));
		ArcoTest a7 = new ArcoTest(1,list.get(7), list.get(8));
		ArcoTest a8 = new ArcoTest(1,list.get(4), list.get(10));

		try {
			grafo.addEdge(0, 1, a);
			grafo.addEdge(0, 2, a0);
			grafo.addEdge(0, 4, a1);
			grafo.addEdge(2, 6, a2);
			grafo.addEdge(2, 7, a3);
			grafo.addEdge(3, 9, a4);
			grafo.addEdge(5, 9, a5);
			grafo.addEdge(8, 10, a6);
			grafo.addEdge(7, 8, a7);
			grafo.addEdge(4, 10, a8);

		} catch (ArcoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		djk = new DijkstraSP(grafo, grafo.V());
		djk.crearCaminoDesdeVertice(list.get(0));
		model.data_structures.listas.LinkedList path = djk.getPath(list.get(10));

		assertNotNull(path);
		assertTrue(path.getSize() > 0);

		Iterator<IVertice> ite = path.iterator();
		
		while(ite.hasNext())
			System.out.println(ite.next().darId());
	
	}




}
