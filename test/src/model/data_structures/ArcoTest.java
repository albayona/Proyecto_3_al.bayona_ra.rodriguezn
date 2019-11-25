package model.data_structures;

import api.IArco;
import api.IVertice;

//Clase auxiliar para las pruebas del grafo
public class ArcoTest implements IArco{

	private static final long serialVersionUID = 1L;
	private double peso;
	private IVertice vertice1;
	private IVertice vertice2;
	
	public ArcoTest(int peso, IVertice vertice1, IVertice vertice2) {
		this.peso = peso;
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
	}

	@Override
	public double darPeso() {
		// TODO Auto-generated method stub
		return peso;
	}

	@Override
	public IVertice getVertice1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVertice getVertice2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVertice getEitherVertex() {
		// TODO Auto-generated method stub
		return vertice1;
	}

	@Override
	public IVertice getOtherVertex(IVertice vertex) {
		// TODO Auto-generated method stub
		if(vertex.compareTo(vertice1)==0)
			return vertice2;
		else if(vertex.compareTo(vertice2) == 0)
			return vertice1;
		else
			return null;
	}

}
