package model.data_structures.Lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


public class DoublyLinkedList<E> implements Iterable<E>{

    private Node<E> current;

    private Node<E> first;

    private Node<E> last;

    private int size;


    /* Node Class */
    public static class Node<E> {

        E value;
        Node<E> next, previous;

        // Constructor to create a new node
        Node(E val, Node<E> next, Node<E> previous) {
            this.value = val;
            this.next = next;
            this.previous = previous;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterador();
    }


    public int size() {
        return size;
    }

    public E current() {
        return current.value;
    }

    public E next() {
        return current.next.value;
    }

    public E last() {
        return last.value;
    }
    
    public E first() {
        return first.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

	public E getElement(int index) {
		if(index < 0 || index >= size)  
		{
			throw new IndexOutOfBoundsException("Se est� pidiendo el indice: " + index + " y el tama�o de la lista es de " + size);
		}
		Node<E> actual = first; 
		int posActual = 0; 
		while(actual != null && posActual < index)
		{
			actual = actual.next;
			posActual ++; 
		}
		return actual.value;
	}

    private class ListIterador implements Iterator<E> {

        ListIterador() {
            current = null;
        }

        @Override
        public E next() {

            if (current == null) {
                current = first;
            } else {

                if (current.next != null) current = current.next;

            }

            return (current != null) ? current.value : null;
        }

        @Override
        public boolean hasNext() {

            if (first == null) return false;

            return (current == null) ? true : current.next != null;
        }




    }

    public E removeLast()
    {
        Node<E> temp = last;

        if(size > 0)
        {
            last = last.previous;

            if (last != null) last.next = null;
        }
        size--;

        return (temp == null) ? null : temp.value;
    }

    public E removeFirst()
    {
        Node<E> sacar = null;
        if(size > 0)
        {
            sacar = first;
            first = first.next;
        }

        size--;

        return (sacar == null) ? null: sacar.value;
    }




    public void addAfterCurrent(E val){

        if (current == last){
            addLast(val);
        }


        else {

            Node<E> nuevo = new Node<E>(val, current.next, current);
            current.next = nuevo;
            current.next.previous = nuevo;

            size++;
        }

    }
    
    
	public E delete(int index) {
		E deleted = null;
		Node<E> actual = (Node<E>) this.first;

		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Se est� pidiendo el indice: " + index + " y el tama�o de la lista es de " + size);
		}

		if(index == 0) {
			deleted = first.value;
			first = first.next;
			this.size --;
		}
		else {
			int cont = 0;
			while(cont + 1 < index){
				actual = (Node<E>) actual.next;
				cont++;
			}
			deleted = actual.next.value;
			actual.next  = actual.next.next;

			if(actual.next == null) {
				last = actual;
			}
			else {
				Node<E> c =  actual.next;
				c.previous = actual;
			}
			this.size --;

		}
		return deleted;
	}

    

    public void addBeforeCurrent(E val){

        if (current == first){
            addFirst(val);
        }

        else {

            Node<E> nuevo = new Node<E>(val, current, current.previous);
            current.previous = nuevo;
            nuevo.previous.next = nuevo;

            size++;
        }

    }

    public void addLast(E val) {
        if (isEmpty()) {
            last = new Node(val, null, null);
            first = last;
        } else {

            Node<E> temp = last;

            last = new Node(val, null, temp);

            temp.next = last;
        }

        size++;
    }

    public void addFirst(E val) {
        if (isEmpty()) {
            first = new Node(val, null, null);
            last = first;
        } else {
            Node<E> f = first;
            first = new Node<>(val, f, null);
            f.previous = first;
        }

        size++;
    }


    public boolean contains(Object o) {
        for (E e : this) {
            if(o==null ? e==null : o.equals(e))
                return true;
        }
        return false;
    }


    public void addAll(int index, DoublyLinkedList<E> doublyLinkedList)
    {
        for(Object o : doublyLinkedList)
        {
            this.addLast((E) o );
            ++index;
        }
    }

    // Split a doubly linked list (DLL) into 2 DLLs of
    // half sizes
    Node split(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    Node mergeSort(Node first, Comparator c) {
        if (first == null || first.next == null) {
            return first;
        }
        Node second = split(first);

        // Recur for left and right halves
        first = mergeSort(first, c);
        second = mergeSort(second, c);

        // Merge the two sorted halves
        return merge(first, second, c);
    }

    // Function to merge two linked lists
    Node merge(Node first, Node second, Comparator c) {
        // If first linked list is empty
        if (first == null) {
            return second;
        }

        // If second linked list is empty
        if (second == null) {
            return first;
        }

        // Pick the smaller value
        if (c.compare(first.value, second.value) < 0) {
            first.next = merge(first.next, second, c);
            first.next.previous = first;
            first.previous = null;
            return first;
        } else {
            second.next = merge(first, second.next, c);
            second.next.previous = second;
            second.previous = null;
            return second;
        }
    }


    public Node mergeSort(Comparator c){
        if (!isEmpty()) {
            first = mergeSort(first, c);

            Node<E> temp = first;

            while (temp.next != null) {
                temp = temp.next;
            }
            last = temp;
        }

        return first;
    }

    public Object[] toArray() {

        E[] items = (E[]) new Object[size];

        int i = 0;
        for (E val : this){
            items[i++] = val;
        }

        return items;
    }

    public void addAll(DoublyLinkedList<E> doublyLinkedList)
    {
        for(E o : doublyLinkedList)
        {
            this.addLast(o);
        }
    }

}


