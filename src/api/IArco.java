package api;

import model.data_structures.listas.LinkedList;

import java.io.Serializable;

/**
 * Interfaz utilizada para representar las responsabilidades m�nimas de un arco
 * Sacado de (http://cupi2.uniandes.edu.co)
 */
public interface IArco extends Serializable
{
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el peso del arco
	 * @return Peso del arco
	 */
	public Object darPeso( );
	
	
	/**
     * @return retorna el vertice1 de tipo IVertice
     **/
    public IVertice getVertice1();
    
    /**
     * @return retorna el vertice2 de tipo IVertice
     **/
    public IVertice getVertice2();
    
    public IVertice getEitherVertex();

    public IVertice getOtherVertex(IVertice vertex);

}

