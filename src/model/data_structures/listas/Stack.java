package model.data_structures.listas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements Iterable<T>{
	
	//--------------------------------------------------------
	//Atributos
	//--------------------------------------------------------

	/**
	 * Lista enlazada donde se van a almacenar los elementos de la pila
	 */
	private LinkedList<T> lista;
	
	//--------------------------------------------------------
	//Constructores
	//--------------------------------------------------------
	
	/**
	 * Crea una pila vacia.
	 */
	public Stack() {
		lista = new LinkedList<T>();
	}
	
	/**
	 * Crea una pila con el elmento que se pasa por parametro.
	 * @param t El Elemento (T extends Comparable) que llega por parametro
	 */
	public Stack(T t) {
		lista = new LinkedList<T>(t);
	}
	
	//--------------------------------------------------------
	//Metodos
	//--------------------------------------------------------

	/**
	 * Metodo que retorna el iterador de la pila (IteratorList), el cual comienza desde el primer nodo.
	 * @return Iterator El iterador de la pila (IteratorList implements Iterator)
	 */
	public Iterator<T> iterator() {
		return lista.iterator();
	}
	
	/**
	 * Retorna true si la Pila esta vacia
	 * @return true si la Pila esta vacia, false de lo contrario
	 */
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	/**
	 * Retorna el numero de elementos contenidos en la pila
	 * @return el numero de elemntos contenidos en la pila
	 */
	public int size() {
		return lista.getSize();
	}

	/**
	 * Elimina y retorna el elemento agregado mas recientemente
	 * @return el elemento agregado mas recientemente
	 */
	public T pop() {
		return lista.delete(0); //retorna el elemento que eliminó en la primera posicion (0)
	}

	/**
	 * Inserta un nuevo elemento en la Pila
	 * @param t el nuevo elemento que se va ha agregar
	 */
	public void push(T t) {
		lista.add(t); //El metodo add de DoublyLinkedList siempre añade el elemento al inicio de la lista
	}
	
	/**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return lista.getElement(0);
    }
	
	public T[] toArray(Class<T> type) {
		return lista.toArray(type);
	}
}
