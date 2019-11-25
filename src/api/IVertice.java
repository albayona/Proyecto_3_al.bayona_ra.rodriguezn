package api;


import java.awt.Color;
import java.io.Serializable;

/**
 * Sacado de (http://cupi2.uniandes.edu.co)
 * Interfaz utilizada para implementar el elemento de un v�rtice
 * @param <ID_VERT> Tipo del id del v�rtice
 */
public interface IVertice<K extends Comparable<K>> extends Serializable, Comparable<IVertice<K>>
{
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el ID del v�rtice
	 * @return Identificador del v�rtice
	 */
	public K darId( );

	public int compareTo(IVertice<Integer> o);
	
	
	public boolean isMarcado();
	public void setMarcado(boolean marcado);
	
	public int getPerviousVertexId();
	public void setPerviousVertexId(int perviousVertexId);
	
	public void setColor(int color);
	public int getColor();
	
}