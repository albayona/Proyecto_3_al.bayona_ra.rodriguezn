package model.data_structures;

import api.IArcoDi;
import api.IVertice;

public class ArcoDiTest implements IArcoDi {

	
	private static final long serialVersionUID = 1L;
	private double peso;
	private IVertice vertice1;
	private IVertice vertice2;
	
	public ArcoDiTest(int peso, IVertice vertice1, IVertice vertice2) {
		this.peso = peso;
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
	}

	@Override
	public double darPeso() {
		return peso;
	}

	@Override
	public IVertice getVertexSource() {
		// TODO Auto-generated method stub
		return vertice1;
	}

	@Override
	public IVertice getVertexTarget() {
		// TODO Auto-generated method stub
		return vertice2;
	}

}
