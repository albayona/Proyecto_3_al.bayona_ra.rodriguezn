package controller;

import api.IVertice;
import model.algorithms.CC;

import model.data_structures.HashTables.HashTable;
import model.data_structures.MaxHeap;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.MaxPQ;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.logic.TripsManager;
import model.vo.TripleCost;

import model.data_structures.HashTables.HashTable;

import java.util.Comparator;
import java.util.Iterator;


public class Controller {

	/**
	 * Reference to the services manager
	 */
	@SuppressWarnings("unused")
	private static TripsManager manager = new TripsManager();
	
	public static void guardarGrafoEnFormatoJSON() {
		manager.guardarGrafoJSON("./data/grafoDefinitivo.json");
	}
	
	public static void cargarGrafoEnFormatoJSON() {
		manager.cargarGrafoJSON("./data/grafoDefinitivo.json");;
	}
	
	public static void loadIntersections() {
		manager.loadIntersection("./data/bogota_vertices.txt");
	}
	
	public static void loadEdges() {
		manager.loadEdges("./data/bogota_arcos.txt");
	}
	
	public static HashTable<Integer, IVertice<Integer>> vertices() {
		return manager.getGrafo1().getVertices();
	}
	
	public static HashTable<String, TripleCost> arcos() {
		return manager.getGrafo1().getArcos();
	}

	public static int cc() throws VerticeNoExisteException {

		CC cc = new CC(manager.getGrafo1(), 228046);

		return  cc.count();}

	public static String verticesNumForTop5() throws VerticeNoExisteException {

		CC cc = new CC(manager.getGrafo1(), 228046);

		HashTable<Integer,Integer> table = new HashTable<Integer, Integer>();

		for (int t: cc.getId()){

			if (!table.containsKey(t)){
				table.put(t, 1);
			}
			else {

				int v = table.get(t);
				v++;
				table.put(t,v);

			}
		}

		MaxHeap<Integer> pq = new MaxHeap(new comp());

		String res= "";

		int i = 0;
		for (Iterator<Integer> it = table.keysIterator(); it.hasNext() && i < 5; ) {
			Integer key = it.next();

			Integer temp = table.get(key);

			res = res +  temp + "\n";
			i++;
		}


		return  res;
	}


	private static class comp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}


}
