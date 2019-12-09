package api;


import java.io.Serializable;

/**
 * Sacado de (http://cupi2.uniandes.edu.co)
 * Interfaz utilizada para implementar el elemento de un v�rtice
 * @param <ID_VERT> Tipo del id del v�rtice
 */
public interface IVertex<K extends Comparable<K>> extends Serializable, Comparable<IVertex<K>>
{
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el ID del v�rtice
	 * @return Identificador del v�rtice
	 */
	public K getId();

	public int compareTo(IVertex<Integer> o);
	
	
	public boolean isMarked();
	public void setMarked(boolean marcado);
	
	public int getPerviousVertexId();
	public void setPerviousVertexId(int perviousVertexId);
	
	public void setColor(int color);
	public int getColor();
	
}