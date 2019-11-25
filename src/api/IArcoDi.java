package api;

import java.io.Serializable;

public interface IArcoDi extends Serializable{

	/**
	 * Devuelve el peso del arco
	 * @return Peso del arco
	 */
	public double darPeso( );


	IVertice<Integer> getVertexSource();

	IVertice<Integer> getVertexTarget();


}
