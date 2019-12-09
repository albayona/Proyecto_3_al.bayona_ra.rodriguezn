package model.data_structures.Graphs;

/**
 * Excepci�n lanzada cuando el arco especificado ya existe
 * Sacado de: (http://cupi2.uniandes.edu.co)
 */
public class RepeatedEdgeException extends Exception
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
	 * Identificador del v�rtice desde donde deber�a salir el arco
	 */
	private Object origen;

	/**
	 * Identificador del v�rtice hasta donde deber�a llegar el arco
	 */
	private Object destino;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la excepci�n
	 * @param mensaje Mensaje de error
	 * @param idDesde Identificador del v�rtice desde donde sale el arco
	 * @param idHasta Identificador del v�rtice hasta donde llega el arco
	 */
	public RepeatedEdgeException(String mensaje, Object idDesde, Object idHasta )
	{
		super( mensaje );
		origen = idDesde;
		destino = idHasta;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el identificador del v�rtice desde donde debe salir el arco
	 * @return identificador del v�rtice desde donde debe salir el arco
	 */
	public Object darOrigen( )
	{
		return origen;
	}

	/**
	 * Devuelve el identificador del v�rtice hasta donde debe llegar el arco
	 * @return identificador del v�rtice hasta donde debe llegar el arco
	 */
	public Object darDestino( )
	{
		return destino;
	}
}
