package model.data_structures.Lists;

public class Node<E> {

	//--------------------------------------------------------
	//Atributos
	//--------------------------------------------------------
	
	/**
	 * Elemento almacenado en el nodo.
	 */
	protected E elemento;
	
	/**
	 * Siguiente nodo.
	 */
	private Node<E> siguiente; 
	
	/**
	 * Anterior nodo.
	 */
	private Node<E> anterior;
	
	//--------------------------------------------------------
	//Constructores
	//--------------------------------------------------------
	
	/**
	 * Constructor del nodo.
	 * @param elemento El elemento que se almacenar� en el nodo. elemento != null
	 */
	public Node(E elemento)
	{
		this.elemento = elemento;
	}
	
	//--------------------------------------------------------
	//Metodos
	//--------------------------------------------------------
	
	/**
	 * M�todo que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(Node<E> siguiente)
	{
		this.siguiente = siguiente;
	}
	
	/**
	 * M�todo que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public E darElemento()
	{
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenar� en el nodo.
	 */
	public void cambiarElemento(E elemento)
	{
		this.elemento = elemento;
	}
	
	
	/**
	 * M�todo que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public Node<E> darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * M�todo que retorna el nodo anterior.
	 * @return Nodo anterior.
	 */
	public Node<E> darAnterior()
	{
		return anterior;
	}
	
	/**
	 * M�todo que cambia el nodo anterior por el que llega como par�metro.
	 * @param anterior Nuevo nodo anterior.
	 */
	public void cambiarAnterior(Node<E> anterior)
	{
		this.anterior = anterior;
	}


}
