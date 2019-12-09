package model.data_structures.Graphs;

/**
 * Excepci�n utilizada para informar que el v�rtice especificado no existe en el grafo
 * Sacado de (http://cupi2.uniandes.edu.co)
 */
public class UnexistingVertexException extends Exception
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
	 * Identificador del v�rtice buscado
	 */
	private Object idVertice;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la excepci�n
	 * @param mensaje Mensaje de error
	 * @param id Identificador del v�rtice no existente
	 */
	public UnexistingVertexException(String mensaje, Object id )
	{
		super( mensaje );
		idVertice = id;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el identificador del v�rtice no existente
	 * @return identificador del v�rtice no existente
	 */
	public Object darIdentificador( )
	{
		return idVertice;
	}
}
