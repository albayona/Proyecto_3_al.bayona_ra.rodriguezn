package model.data_structures;

import api.IDiEdge;
import api.IVertex;

public class DiEdgeTest implements IDiEdge {

	
	private static final long serialVersionUID = 1L;
	private double peso;
	private IVertex vertice1;
	private IVertex vertice2;
	
	public DiEdgeTest(int peso, IVertex vertice1, IVertex vertice2) {
		this.peso = peso;
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
	}

	@Override
	public double darPeso() {
		return peso;
	}

	@Override
	public IVertex getVertexSource() {
		// TODO Auto-generated method stub
		return vertice1;
	}

	@Override
	public IVertex getVertexTarget() {
		// TODO Auto-generated method stub
		return vertice2;
	}

}
