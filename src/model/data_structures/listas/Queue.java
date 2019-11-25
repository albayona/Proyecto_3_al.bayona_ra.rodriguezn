package model.data_structures.listas;

import java.util.Iterator;

/**
 * Clase Queue (Cola)
 */
public class Queue<E extends Comparable<E>> implements Iterable<E>{

	
	//--------------------------------------------------------
	//Atributos
	//--------------------------------------------------------
	
	/**
	 * Lista enlazada donde se van a almacenar los elementos de la cola
	 */
	private LinkedList <E> list;

	//--------------------------------------------------------
	//Constructores
	//--------------------------------------------------------
	
	/**
	 * Crea una cola vacia.
	 */
	public Queue() {
		list= new LinkedList<E>();
	}
	

	/**
	 * Crea una cola a partir del elmento que se pasa por parametro.
	 * @param t El Elemento (T extends Comparable) que llega por parametro
	 */
	public Queue(E e) {
		list = new LinkedList<E>(e);
	}
	
	
	//--------------------------------------------------------
	//Metodos
	//--------------------------------------------------------

	public LinkedList<E> getList() {
		return list;
	}
	
	/**
	 * Quita y retorna el elemento agregado menos recientemente
	 * @return el elemento agregado menos recientemente
	 * @throws Exception 
	 */
	public E dequeue() {
		return (E) list.delete(0);
	}

	/**
	 * Retorna el numero de elementos contenidos
	 * @return el numero de elemntos contenidos
	 */
	public int size() {
		return list.getSize();
	}

	/**
	 * Metodo que retorna el iterador de la cola (IteratorList), el cual comienza desde el primer nodo.
	 * @return Iterator El iterador de la cola (IteratorList implements Iterator)
	 */
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	/**
	 * Retorna true si la Cola esta vacia
	 * @return true si la Cola esta vacia, false de lo contrario
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Inserta un nuevo elemento en la Cola
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void enqueue(E item) {
		list.addAtEnd(item);
	}

	public E[] toArray(Class<E> type) { //TODO Andrea
		return list.toArray(type);
	}

}
