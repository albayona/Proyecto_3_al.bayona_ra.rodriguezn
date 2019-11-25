package controller;

import api.IVertice;
import model.algorithms.CC;

import model.data_structures.HashTables.HashTable;
import model.data_structures.grafo1.Grafo;
import model.data_structures.grafo1.MaxPQ;
import model.data_structures.grafo1.VerticeNoExisteException;
import model.logic.TripsManager;
import model.vo.TripleCost;

import model.data_structures.HashTables.HashTable;


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
				System.out.print(v);
				table.put(t,v);

			}
		}

		MaxPQ<Integer>  pq = new MaxPQ<>();

		for (Integer c: table.valuesIterator()){
			
			pq.insert(c);
		}

		String res ="";

		for (int i = 0; i < 5; i++){
			res+= pq.delMax() + " \n";
		}

		return  res;
	}




}
