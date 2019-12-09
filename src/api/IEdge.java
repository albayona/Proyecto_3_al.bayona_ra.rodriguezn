package api;

import java.io.Serializable;

/**
 * Interfaz utilizada para representar las responsabilidades m�nimas de un arco
 * Sacado de (http://cupi2.uniandes.edu.co)
 */
public interface IEdge extends Serializable
{
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el peso del arco
	 * @return Peso del arco
	 */
	public double weight();

	public double weight( int index);
	
	
	/**
     * @return retorna el vertice1 de tipo IVertice
     **/
    public IVertex getVertex1();
    
    /**
     * @return retorna el vertice2 de tipo IVertice
     **/
    public IVertex getVertex2();
    
    public IVertex either();

    public IVertex other(int vertex);

    public String toString();

}

