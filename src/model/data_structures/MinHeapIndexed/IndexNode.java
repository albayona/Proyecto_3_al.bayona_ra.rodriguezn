package model.data_structures.MinHeapIndexed;


public class IndexNode<K extends Comparable,V> implements Comparable<IndexNode>{
	
	private K key;
	
	private V value;
	public IndexNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(IndexNode o) {
	
		int comp = key.compareTo(o.key);
		
		if(comp > 0)
			return 1;
		if(comp < 0)
			return -1;
		else
			return 0;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

}
