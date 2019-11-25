package model.data_structures.MinHeap;



public class MinHeap <T extends Comparable<T>>{
	
	
	private int capacity;
	private int size; 
	private T[] items;
	
/**
 * Crea un objeto de la clase con una capacidad m�xima de
 * elementos pero vac�a.
 * 
 */
	public MinHeap(int max) {
		capacity=max;
		size=0;
		items = (T[]) new Comparable[max];
		
	}
	
	
	private int getLeftChildIndex(int parentIndex){
		return 2*parentIndex+1;
	}
	private int getRightChildIndex(int parentIndex){
		return 2*parentIndex+2;
	}
	private int getParentIndex(int childIndex){
		return (childIndex-1)/2;
	}
	private boolean hasLeftChild(int index){
		return getLeftChildIndex(index)<size;
	}
	private boolean hasRightChild(int index){
		return getRightChildIndex(index)<size;
	}
	private boolean hasParent(int i){
		return getParentIndex(i)>=0;
	}
	private T leftChild(int i){
		return items[getLeftChildIndex(i)];
	}
	private T rightChild(int i){
		return items[getRightChildIndex(i)];
	}
	private T parent(int i) {
		return items[getParentIndex(i)];
	}
	private void swap (int indexOne, int indexTwo) {
		T temp=items[indexOne];
		items[indexOne]=items[indexTwo];
		items[indexTwo]=temp;
	}
	
	/**
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna; 
	 * @return null en caso de cola vac�a
	 * 
	 */
	public T min( ) {
		if (size==0) throw new IllegalStateException("La cola esta vacia");
		T item= items[0];
		items[0]=items[size-1];
		size--;
		heapifyDown();
		return item;
	}
	
	
	/**
	 *  Retorna n�mero de elementos presentes en la cola de prioridad
	 */
	public int darNumElementos(){
		return size;
		
	}
	/**
	 * Agrega un elemento a la cola. Se genera Exception en el 
	 * caso que se sobrepase el tama�o m�ximo de la cola
	 * @throws Exception 
	 */
	public void agregar(T elemento) throws Exception {
		if(capacity==size) {
			throw new Exception("No puede a�adir m�s elementos; se ha alcanzado el tope maximo");
		}else {
			items[size]=elemento;
			size++;
			heapifyUp();
		}
		
	}
	
	private void heapifyUp() {
		int index=size-1;
		while(hasParent(index) && parent(index).compareTo(items[index])>0 ) {
			swap(getParentIndex(index), index);
			index=getParentIndex(index);
		}
		
	}

	private void heapifyDown() {
		int index=0;
		while(hasLeftChild(index)) {
			int smallerChildIndex=getLeftChildIndex(index);
			if(hasRightChild(index) && rightChild(index).compareTo(leftChild(index))<0) {
				smallerChildIndex=getRightChildIndex(index);
			}
			
			if(items[index].compareTo(items[smallerChildIndex])<0) {
				break;
			}else {
				swap(index, smallerChildIndex);
			}
				index=smallerChildIndex;	
		}
	}



	/**
	 * Saca/atiende el elemento m�ximo en la cola y lo retorna; 
	 * @return null en caso de cola vac�a
	 * 
	 */
	
	/**
	 * Retorna si la cola est� vac�a o no
	 */
	public boolean esVacia() {
		return items[0]==null;
		
	}
	/**
	 * Retorna la capacidad m�xima de la cola
	 */
	public int tamanoMax() {
		return capacity;
	}
}
