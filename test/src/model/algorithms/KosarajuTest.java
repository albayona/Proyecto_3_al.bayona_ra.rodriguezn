package model.algorithms;

import java.util.Iterator;

import api.IVertice;
import junit.framework.TestCase;
import model.data_structures.ArcoDiTest;
import model.data_structures.ArcoTest;
import model.data_structures.VerticeTest;
import model.data_structures.grafo1.ArcoNoExisteException;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.GrafoDirigido;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.data_structures.listas.LinkedList;
import model.data_structures.listas.Queue;

public class KosarajuTest extends TestCase {

	private GrafoDirigido<Integer, VerticeTest, ArcoDiTest> grafo;
	private KosarajuSCC ksj;
	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		grafo = new GrafoDirigido<Integer, VerticeTest, ArcoDiTest>();
	}


	//------------------------------------------------------
	//pruebas
	//------------------------------------------------------

	public void testDijk() {

		setUpEscenario0();
		LinkedList<VerticeTest> list = new LinkedList<VerticeTest>();

		for (int i = 0; i < 20; i++) {
			VerticeTest v = new VerticeTest(i);
			list.add(v);
			try {
				grafo.addVertex(v.darId(), v);
			} catch (VerticeNoExisteException e) {
				e.printStackTrace();
			}
		}

		ArcoDiTest a = new ArcoDiTest(10000, list.getElement(0), list.getElement(1));
		ArcoDiTest a0 = new ArcoDiTest(1000, list.getElement(1), list.getElement(0));
		ArcoDiTest a3 = new ArcoDiTest(50, list.getElement(1), list.getElement(3));
		ArcoDiTest a1 = new ArcoDiTest(500, list.getElement(1), list.getElement(2));
		ArcoDiTest a2 = new ArcoDiTest(100, list.getElement(2), list.getElement(1));
		ArcoDiTest a4 = new ArcoDiTest(10, list.getElement(3), list.getElement(0));

		ArcoDiTest a5 = new ArcoDiTest(500, list.getElement(4), list.getElement(5));
		ArcoDiTest a6 = new ArcoDiTest(1,list.getElement(5), list.getElement(4));
		ArcoDiTest a7 = new ArcoDiTest(900,list.getElement(5), list.getElement(6));
		ArcoDiTest a9 = new ArcoDiTest(1,list.getElement(5), list.getElement(7));
		ArcoDiTest a8 = new ArcoDiTest(1,list.getElement(6), list.getElement(5));
		ArcoDiTest a10 = new ArcoDiTest(1,list.getElement(7), list.getElement(4));

		ArcoDiTest a11 = new ArcoDiTest(1,list.getElement(8), list.getElement(9));
		ArcoDiTest a12 = new ArcoDiTest(1,list.getElement(9), list.getElement(8));
		ArcoDiTest a13 = new ArcoDiTest(1,list.getElement(9), list.getElement(10));
		ArcoDiTest a14 = new ArcoDiTest(1,list.getElement(9), list.getElement(11));
		ArcoDiTest a15 = new ArcoDiTest(1,list.getElement(10), list.getElement(9));
		ArcoDiTest a16 = new ArcoDiTest(1,list.getElement(11), list.getElement(8));

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
			
			LinkedList<VerticeTest> lista = grafo.getVertices().valuesIterator();
			
			for(IVertice v : lista) {
				System.out.println("ID del vertice: "+v.darId());
				System.out.println("Color del vertice: " + v.getColor());
			}
			System.out.println("Num componenetes: " + ksj.count());
					

		} catch (ArcoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
