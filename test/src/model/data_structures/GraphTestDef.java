package model.data_structures;

import junit.framework.TestCase;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingVertexException;

public class GraphTestDef extends TestCase {
	private Graph<Integer, VertexTest, EdgeTest> graph;


	//------------------------------------------------------
	//Escenarios
	//------------------------------------------------------

	public void setUpEscenario0() {
		graph = new Graph<Integer, VertexTest, EdgeTest>();
	}

	public void setUpEscenario1() {
		setUpEscenario0();
	
		VertexTest v = new VertexTest(1);
		VertexTest v1 = new VertexTest(2);
		VertexTest v2 = new VertexTest(3);
		VertexTest v3 = new VertexTest(4);
		VertexTest v4 = new VertexTest(5);
		
		try {
			graph.addVertex(v.getId(), v);
			graph.addVertex(v1.getId(), v1);
			graph.addVertex(v2.getId(), v2);
			graph.addVertex(v3.getId(), v3);
			graph.addVertex(v4.getId(), v4);
		} catch (UnexistingVertexException e) {
			e.printStackTrace();
		}
		
	}

	public void setUpEscenario2() {
		
		setUpEscenario1();
		EdgeTest a = new EdgeTest(1, null, null);
		EdgeTest a1 = new EdgeTest(2, null, null);
		EdgeTest a2 = new EdgeTest(3, null, null);
		EdgeTest a3 = new EdgeTest(4, null, null);
		EdgeTest a4 = new EdgeTest(5, null, null);
		
		try {
			graph.addEdge(1, 2, a);
			graph.addEdge(1, 3, a1);
			graph.addEdge(2, 4, a2);
			graph.addEdge(2, 3, a3);
			graph.addEdge(4, 5, a4);

		} catch ( UnexistingEdgeException e) {
			e.printStackTrace();
		}
	}
	
	public void setUpEscenario3() {
		setUpEscenario0();
		
		VertexTest v;
		
		for(int i = 0; i < 100; i++) {
			v = new VertexTest(i);
			try {
				graph.addVertex(v.getId(), v);
			} catch (UnexistingVertexException e) {
				e.printStackTrace();
			}
		}

	}

	//------------------------------------------------------
	//Metodos de prueba
	//------------------------------------------------------


	public void testAddVertices() {
		setUpEscenario0();
		VertexTest v = new VertexTest(1);
		VertexTest v1 = new VertexTest(2);
		VertexTest v2 = new VertexTest(3);
		VertexTest v3 = new VertexTest(4);
		VertexTest v4 = new VertexTest(5);
		
		try {
			graph.addVertex(v.getId(), v);
			graph.addVertex(v1.getId(), v1);
			graph.addVertex(v2.getId(), v2);
			graph.addVertex(v3.getId(), v3);
			graph.addVertex(v4.getId(), v4);
		} catch (UnexistingVertexException e) {
			e.printStackTrace();
		}
		assertTrue(graph.hasVertex(1));
		assertTrue(graph.hasVertex(2));
		assertTrue(graph.hasVertex(3));
		assertTrue(graph.hasVertex(4));
		assertTrue(graph.hasVertex(5));
			
	}	

	public void testAddArcos() {
		setUpEscenario1();
		EdgeTest a = new EdgeTest(1, null, null);
		EdgeTest a1 = new EdgeTest(2, null, null);
		EdgeTest a2 = new EdgeTest(3, null, null);
		EdgeTest a3 = new EdgeTest(4, null, null);
		EdgeTest a4 = new EdgeTest(5, null, null);
		
		try {
			graph.addEdge(1, 2, a);
			graph.addEdge(1, 3, a1);
			graph.addEdge(2, 4, a2);
			graph.addEdge(2, 3, a3);
			graph.addEdge(4, 5, a4);

		} catch (UnexistingEdgeException e) {
			e.printStackTrace();
		}
		
		assertTrue(graph.hasVertex(1, 2));
		assertTrue(graph.hasVertex(1, 3));
		assertTrue(graph.hasVertex(2, 4));
		assertTrue(graph.hasVertex(2, 3));
		assertTrue(graph.hasVertex(4, 5));
			
	}

	public void testGetVertice() {
		setUpEscenario2();
		
		VertexTest v = (VertexTest) graph.getInfoVertex(1);
		VertexTest v1 = (VertexTest) graph.getInfoVertex(2);
		VertexTest v2 = (VertexTest) graph.getInfoVertex(3);
		VertexTest v3 = (VertexTest) graph.getInfoVertex(4);
		VertexTest v4 = (VertexTest) graph.getInfoVertex(5);
		
		assertTrue(1 == v.getId());
		assertTrue(2 == v1.getId());
		assertTrue(3 == v2.getId());
		assertTrue(4 == v3.getId());
		assertTrue(5 == v4.getId());
	}
	
	public void testGetArc() {
		setUpEscenario2();
		try {
			EdgeTest a1 = (EdgeTest) graph.getEdgeInfo(1, 2);
			EdgeTest a2 = (EdgeTest) graph.getEdgeInfo(1, 3);
			EdgeTest a3 = (EdgeTest) graph.getEdgeInfo(2, 4);
			EdgeTest a4 = (EdgeTest) graph.getEdgeInfo(2, 3);
			EdgeTest a5 = (EdgeTest) graph.getEdgeInfo(4, 5);

			assertTrue(1 == a1.weight());
			assertTrue(2 == a2.weight());
			assertTrue(3 == a3.weight());
			assertTrue(4 == a4.weight());
			assertTrue(5 == a5.weight());
			
		} catch (UnexistingEdgeException e) {
			e.printStackTrace();
		}
	}
	
	public void testSetInfoArc() {
		setUpEscenario2();
		EdgeTest v1 = new EdgeTest(100, null, null);
		EdgeTest v2 = new EdgeTest(200, null, null);
		EdgeTest v3 = new EdgeTest(300, null, null);

		try {
			graph.setEdgeInfo(1, 2,  v1);
			graph.setEdgeInfo(1, 3,  v2);
			graph.setEdgeInfo(4, 5,  v3);
			
		} catch (UnexistingEdgeException e) {
			e.printStackTrace();
		}
		
		
		try {
			v1 = (EdgeTest) graph.getEdgeInfo(1, 2);
			v2 = (EdgeTest) graph.getEdgeInfo(1, 3);
			v3 = (EdgeTest) graph.getEdgeInfo(4, 5);
		} catch (UnexistingEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertTrue(v1.weight() == 100);
		assertTrue(v2.weight() == 200);
		assertTrue(v3.weight() == 300);
	}
	
	public void  testSetInfoVertice() {
		setUpEscenario2();
		VertexTest v = new VertexTest(1);
		v.setName("Nuevo1");
		
		VertexTest v2 = new VertexTest(2);
		v2.setName("Nuevo2");

		VertexTest v3 = new VertexTest(3);
		v3.setName("Nuevo3");

		try {
			graph.setInfoVertex(1, v);
			graph.setInfoVertex(2, v2);
			graph.setInfoVertex(3, v3);
			
		} catch (UnexistingVertexException e) {
			e.printStackTrace();
		}
		
		
		v = (VertexTest) graph.getInfoVertex(1);
		v2 = (VertexTest) graph.getInfoVertex(2);
		v3 = (VertexTest) graph.getInfoVertex(3);
		
		
		assertTrue(v.getName().equals("Nuevo1"));
		assertTrue(v2.getName().equals("Nuevo2"));
		assertTrue(v3.getName().equals("Nuevo3"));
	}
	
	public void testNumArcos() {
		
		setUpEscenario3();
		
		EdgeTest a;
		
		for(int i = 0; i < 99; i++) {
			a = new EdgeTest(i*2, null, null);
			
			try {
				graph.addEdge(i, i+1, a);
			} catch (UnexistingEdgeException e) {
				e.printStackTrace();
			}
		}
		
		assertTrue(graph.E() == 99);
		
		
	}
	
	public void testNumVertices() {
		setUpEscenario0();
		
		VertexTest v;
		
		for(int i = 0; i < 100; i++) {
			v = new VertexTest(i);
			try {
				graph.addVertex(v.getId(), v);
			} catch (UnexistingVertexException e) {
				e.printStackTrace();
			}
		} 
		
		assertTrue(graph.V() == 100);
	}

	public void testDummy() {
		
		VertexTest v = new VertexTest(500);
		VertexTest v2 = new VertexTest(500);
		
		assertTrue(v.equals(v2));

	}
}
