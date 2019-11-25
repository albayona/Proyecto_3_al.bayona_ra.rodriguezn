package model.data_structures;

import junit.framework.TestCase;
import model.data_structures.grafo1.ArcoNoExisteException;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.VerticeNoExisteException;

public class GraphTestDef extends TestCase {
	private Grafo<Integer, VerticeTest, ArcoTest> grafo;


	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		grafo = new Grafo<Integer, VerticeTest, ArcoTest>();
	}

	public void setUpEscenario1() {
		setUpEscenario0();
	
		VerticeTest v = new VerticeTest(1); 
		VerticeTest v1 = new VerticeTest(2); 
		VerticeTest v2 = new VerticeTest(3); 
		VerticeTest v3 = new VerticeTest(4); 
		VerticeTest v4 = new VerticeTest(5); 
		
		try {
			grafo.addVertex(v.darId(), v);
			grafo.addVertex(v1.darId(), v1);
			grafo.addVertex(v2.darId(), v2);
			grafo.addVertex(v3.darId(), v3);
			grafo.addVertex(v4.darId(), v4);
		} catch (VerticeNoExisteException e) {
			e.printStackTrace();
		}
		
	}

	public void setUpEscenario2() {
		
		setUpEscenario1();
		ArcoTest a = new ArcoTest(1, null, null);
		ArcoTest a1 = new ArcoTest(2, null, null);
		ArcoTest a2 = new ArcoTest(3, null, null);
		ArcoTest a3 = new ArcoTest(4, null, null);
		ArcoTest a4 = new ArcoTest(5, null, null);
		
		try {
			grafo.addEdge(1, 2, a);
			grafo.addEdge(1, 3, a1);
			grafo.addEdge(2, 4, a2);
			grafo.addEdge(2, 3, a3);
			grafo.addEdge(4, 5, a4);

		} catch ( ArcoNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUpEscenario3() {
		setUpEscenario0();
		
		VerticeTest v;
		
		for(int i = 0; i < 100; i++) {
			v = new VerticeTest(i);
			try {
				grafo.addVertex(v.darId(), v);
			} catch (VerticeNoExisteException e) {
				e.printStackTrace();
			}
		}

	}

	//------------------------------------------------------
	//Metodos de prueba
	//------------------------------------------------------


	public void testAddVertices() {
		setUpEscenario0();
		VerticeTest v = new VerticeTest(1); 
		VerticeTest v1 = new VerticeTest(2); 
		VerticeTest v2 = new VerticeTest(3); 
		VerticeTest v3 = new VerticeTest(4); 
		VerticeTest v4 = new VerticeTest(5); 
		
		try {
			grafo.addVertex(v.darId(), v);
			grafo.addVertex(v1.darId(), v1);
			grafo.addVertex(v2.darId(), v2);
			grafo.addVertex(v3.darId(), v3);
			grafo.addVertex(v4.darId(), v4);
		} catch (VerticeNoExisteException e) {
			e.printStackTrace();
		}
		assertTrue(grafo.existeVertice(1));
		assertTrue(grafo.existeVertice(2));
		assertTrue(grafo.existeVertice(3));
		assertTrue(grafo.existeVertice(4));
		assertTrue(grafo.existeVertice(5));
			
	}	

	public void testAddArcos() {
		setUpEscenario1();
		ArcoTest a = new ArcoTest(1, null, null);
		ArcoTest a1 = new ArcoTest(2, null, null);
		ArcoTest a2 = new ArcoTest(3, null, null);
		ArcoTest a3 = new ArcoTest(4, null, null);
		ArcoTest a4 = new ArcoTest(5, null, null);
		
		try {
			grafo.addEdge(1, 2, a);
			grafo.addEdge(1, 3, a1);
			grafo.addEdge(2, 4, a2);
			grafo.addEdge(2, 3, a3);
			grafo.addEdge(4, 5, a4);

		} catch (ArcoNoExisteException e) {
			e.printStackTrace();
		}
		
		assertTrue(grafo.existeArco(1, 2));
		assertTrue(grafo.existeArco(1, 3));
		assertTrue(grafo.existeArco(2, 4));
		assertTrue(grafo.existeArco(2, 3));
		assertTrue(grafo.existeArco(4, 5));
			
	}

	public void testGetVertice() {
		setUpEscenario2();
		
		VerticeTest v = (VerticeTest) grafo.getInfoVertex(1);
		VerticeTest v1 = (VerticeTest) grafo.getInfoVertex(2);
		VerticeTest v2 = (VerticeTest) grafo.getInfoVertex(3);
		VerticeTest v3 = (VerticeTest) grafo.getInfoVertex(4);
		VerticeTest v4 = (VerticeTest) grafo.getInfoVertex(5);
		
		assertTrue(1 == v.darId());
		assertTrue(2 == v1.darId());
		assertTrue(3 == v2.darId());
		assertTrue(4 == v3.darId());
		assertTrue(5 == v4.darId());
	}
	
	public void testGetArc() {
		setUpEscenario2();
		try {
			ArcoTest a1 = (ArcoTest) grafo.getInfoArc(1, 2);
			ArcoTest a2 = (ArcoTest) grafo.getInfoArc(1, 3);
			ArcoTest a3 = (ArcoTest) grafo.getInfoArc(2, 4);
			ArcoTest a4 = (ArcoTest) grafo.getInfoArc(2, 3);
			ArcoTest a5 = (ArcoTest) grafo.getInfoArc(4, 5);

			assertTrue(1 == a1.darPeso());
			assertTrue(2 == a2.darPeso());
			assertTrue(3 == a3.darPeso());
			assertTrue(4 == a4.darPeso());
			assertTrue(5 == a5.darPeso());
			
		} catch (ArcoNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	public void testSetInfoArc() {
		setUpEscenario2();
		ArcoTest v1 = new ArcoTest(100, null, null);
		ArcoTest v2 = new ArcoTest(200, null, null);
		ArcoTest v3 = new ArcoTest(300, null, null);

		try {
			grafo.setInfoArc(1, 2,  v1);
			grafo.setInfoArc(1, 3,  v2);
			grafo.setInfoArc(4, 5,  v3);
			
		} catch (ArcoNoExisteException e) {
			e.printStackTrace();
		}
		
		
		try {
			v1 = (ArcoTest) grafo.getInfoArc(1, 2);
			v2 = (ArcoTest) grafo.getInfoArc(1, 3);
			v3 = (ArcoTest) grafo.getInfoArc(4, 5);
		} catch (ArcoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertTrue(v1.darPeso() == 100);
		assertTrue(v2.darPeso() == 200);
		assertTrue(v3.darPeso() == 300);
	}
	
	public void  testSetInfoVertice() {
		setUpEscenario2();
		VerticeTest v = new VerticeTest(1);
		v.setName("Nuevo1");
		
		VerticeTest v2 = new VerticeTest(2);
		v2.setName("Nuevo2");

		VerticeTest v3 = new VerticeTest(3);
		v3.setName("Nuevo3");

		try {
			grafo.setInfoVertex(1, v);
			grafo.setInfoVertex(2, v2);
			grafo.setInfoVertex(3, v3);
			
		} catch (VerticeNoExisteException e) {
			e.printStackTrace();
		}
		
		
		v = (VerticeTest) grafo.getInfoVertex(1);
		v2 = (VerticeTest) grafo.getInfoVertex(2);
		v3 = (VerticeTest) grafo.getInfoVertex(3);
		
		
		assertTrue(v.getName().equals("Nuevo1"));
		assertTrue(v2.getName().equals("Nuevo2"));
		assertTrue(v3.getName().equals("Nuevo3"));
	}
	
	public void testNumArcos() {
		
		setUpEscenario3();
		
		ArcoTest a;
		
		for(int i = 0; i < 99; i++) {
			a = new ArcoTest(i*2, null, null);
			
			try {
				grafo.addEdge(i, i+1, a);
			} catch (ArcoNoExisteException e) {
				e.printStackTrace();
			}
		}
		
		assertTrue(grafo.E() == 99);
		
		
	}
	
	public void testNumVertices() {
		setUpEscenario0();
		
		VerticeTest v;
		
		for(int i = 0; i < 100; i++) {
			v = new VerticeTest(i);
			try {
				grafo.addVertex(v.darId(), v);
			} catch (VerticeNoExisteException e) {
				e.printStackTrace();
			}
		} 
		
		assertTrue(grafo.V() == 100);
	}

	public void testDummy() {
		
		VerticeTest v = new VerticeTest(500);
		VerticeTest v2 = new VerticeTest(500);
		
		assertTrue(v.equals(v2));

	}
}
