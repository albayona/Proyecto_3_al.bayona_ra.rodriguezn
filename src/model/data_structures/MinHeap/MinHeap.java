package model.data_structures.MinHeap;




import java.util.Comparator;
import java.util.Iterator;

public class MinHeap<T>{

	private T[] items;

	private int n;

	Comparator<T> comparator;

	public MinHeap(int initCapacity, Comparator<T> comp ) {
		items = (T[]) new Object[initCapacity + 1];
		comparator = comp;
		n = 0;
	}

	public MinHeap(Comparator<T> comp) {

		this(1, comp);

	}


	public boolean isEmpty() {

		return n == 0;
	}


	public int numElements() {
		return n;
	}

	public T getMin() {

		if (isEmpty()) throw new RuntimeException();

		return items[1];

	}

	private void resize(int cap) {

		assert cap > n;

		T[] temp = (T[]) new Object[cap];

		for (int i = 1; i <= n; i++) {

			temp[i] = items[i];

		}
		items = temp;
	}


	public void add(T x) {

		if (n == items.length - 1) resize(2 * items.length);

		items[++n] = x;

		swim(n);


	}



	public T min() {

		if (isEmpty()) return null;

		T min = items[1];

		exch(1, n--);

		sink(1);

		items[n + 1] = null;

		if ((n > 0) && (n == (items.length - 1) / 4)) resize(items.length / 2);


		return min;

	}



	private void swim(int k) {

		while (k > 1 && greater(k / 2, k)) {

			exch(k, k / 2);

			k = k / 2;

		}

	}


	private void sink(int k) {

		while (2 * k <= n) {

			int j = 2 * k;

			if (j < n && greater(j, j + 1)) j++;

			if (!greater(k, j)) break;

			exch(k, j);

			k = j;

		}

	}



	private boolean greater(int i, int j) {

		return comparator.compare(items[i], items[j]) >= 0;

	}


	private void exch(int i, int j) {

		T swap = items[i];

		items[i] = items[j];

		items[j] = swap;

	}




	public Iterator<T> iterador() {

		return new MinHeap.HeapIterator();

	}


	private class HeapIterator implements Iterator<T> {


		// create a new pq

		private MinHeap<T> copia;


		// add all items to copy of heap

		// takes linear time since already in heap order so no keys move

		public HeapIterator() {

			copia = new MinHeap<T>(numElements(), comparator);

			for (int i = 1; i <= n; i++)

				copia.add(items[i]);

		}

		public boolean hasNext() {
			return !copia.isEmpty();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}


		public T next() {

			if (!hasNext()) throw new RuntimeException();

			return copia.min();
		}

	}
}
