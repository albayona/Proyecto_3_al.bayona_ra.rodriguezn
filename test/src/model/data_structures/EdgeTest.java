package model.data_structures;

import api.IEdge;
import api.IVertex;

//Clase auxiliar para las pruebas del grafo
public class EdgeTest implements IEdge {

	private static final long serialVersionUID = 1L;
	private double peso;
	private IVertex vertice1;
	private IVertex vertice2;
	
	public EdgeTest(int peso, IVertex vertice1, IVertex vertice2) {
		this.peso = peso;
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
	}

	@Override
	public double weight() {
		// TODO Auto-generated method stub
		return peso;
	}

	@Override
	public int getVertex1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVertex getVertex2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int either() {
		// TODO Auto-generated method stub
		return vertice1;
	}

	@Override
	public IVertex other(IVertex vertex) {
		// TODO Auto-generated method stub
		if(vertex.compareTo(vertice1)==0)
			return vertice2;
		else if(vertex.compareTo(vertice2) == 0)
			return vertice1;
		else
			return null;
	}

}
