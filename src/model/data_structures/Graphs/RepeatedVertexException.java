package model.data_structures.Graphs;

/**
 * Excepci�n utilizada para informar que el v�rtice especificado ya existe en el grafo
 * Sacado de (http://cupi2.uniandes.edu.co)
 */
public class RepeatedVertexException extends Exception
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L; 

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Identificador del v�rtice que ya existe en el grafo
	 */
	private Object idVertice;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la excepci�n
	 * @param mensaje Mensaje de error
	 * @param id Identificador del v�rtice que ya existe
	 */
	public RepeatedVertexException(String mensaje, Object id )
	{
		super( mensaje );
		idVertice = id;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el identificador del v�rtice que ya existe
	 * @return identificador del v�rtice que ya existe
	 */
	public Object darIdentificador( )
	{
		return idVertice;
	}
}
