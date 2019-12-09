package api;

import java.io.Serializable;

public interface IDiEdge extends Serializable{

	/**
	 * Devuelve el peso del arco
	 * @return Peso del arco
	 */
	public double darPeso( );


	IVertex<Integer> getVertexSource();

	IVertex<Integer> getVertexTarget();


}
