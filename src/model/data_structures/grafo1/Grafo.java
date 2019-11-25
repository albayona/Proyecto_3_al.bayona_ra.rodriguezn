package model.data_structures.grafo1;

import java.util.Iterator;

import api.IArco;
import api.IGrafo;
import api.IVertice;
import model.data_structures.HashTables.HashTable;
import model.data_structures.listas.LinkedList;


public class Grafo <K extends Comparable<K>, V extends IVertice<K>, A extends IArco> implements IGrafo<K, IVertice<K>, IArco>{

	private HashTable<K, V> vertices;

	private HashTable<String, A> arcos;

	private HashTable<K, LinkedList<K>> listaAdj;
	
	private HashTable<K, LinkedList<IVertice>> listaAdjVertices;

	private HashTable<K, LinkedList<IArco>> listaAdjArcos;


	/**
	 * Crea un grafo No dirigido sin v�rtices y sin arcos
	 */
	public Grafo ( ){
		vertices= new HashTable<K, V>(1000);
		arcos= new HashTable<String, A>(1000); //String es id1+","+id2
		listaAdj= new HashTable<K, LinkedList<K>>(10000);
		listaAdjVertices = new HashTable<K, LinkedList<IVertice>>(100000);
		listaAdjArcos = new HashTable<K, LinkedList<IArco>>(100000);
	}

	public HashTable<K, V> getVertices() {
		return vertices;
	}
	public Iterator darVerticesKeys(){
		return vertices.keysIterator();
	}
	public HashTable<String, A> getArcos() {
		return arcos;
	}

	public HashTable<K, LinkedList<K>> getListaAdj() {
		return listaAdj;
	}

	/**
	 *N�mero de v�rtices
	 */
	@Override
	public int V() {
		return vertices.sizeKeys();
	}

	/**
	 * N�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
	 */
	@Override
	public int E() {
		return arcos.sizeKeys();
	}

	/**
	 * Adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex
	 * @throws VerticeNoExisteException 
	 */
	public void addVertex(K idVertex, V infoVertex) throws VerticeNoExisteException {
		if(infoVertex!=null){
			vertices.put(idVertex,  infoVertex);
			listaAdj.put(idVertex, new LinkedList<K>());
			listaAdjVertices.put(idVertex, new LinkedList<IVertice>());
			listaAdjArcos.put(idVertex, new LinkedList<IArco>());
		}else{
			throw new VerticeNoExisteException("El vertice a a�adir es nulo", idVertex);
		}
	}

	/**
	 * Adiciona el arco No dirigido entre el vertice IdVertexIni y el vertice IdVertexFin. El arco tiene la informaci�n infoArc.
	 * @throws ArcoNoExisteException 
	 */

	public void addEdge(K idVertexIni, K idVertexFin, A infoArc) throws ArcoNoExisteException {
		if(infoArc!=null){
			IVertice v1 = this.vertices.get(idVertexIni);
			IVertice v2 = this.vertices.get(idVertexFin);

			arcos.put(""+idVertexIni+","+idVertexFin+"", infoArc);
			listaAdj.get(idVertexIni).add(idVertexFin);
			listaAdj.get(idVertexFin).add(idVertexIni);
			
			listaAdjVertices.get(idVertexIni).add(v2);
			listaAdjVertices.get(idVertexFin).add(v1);
			listaAdjArcos.get(idVertexIni).add(infoArc);
			listaAdjArcos.get(idVertexFin).add(infoArc);
			
		}else{
			throw new ArcoNoExisteException("El arco a a�adir es nulo", idVertexIni, idVertexFin);
		}
	}
	
	/**
	 * Adiciona el arco Dirigido entre el vertice IdVertexIni y el vertice IdVertexFin. El arco tiene la informaci�n infoArc.
	 * @throws ArcoNoExisteException 
	 */
	public void addEdgeDirigido(K idVertexIni, K idVertexFin, A infoArc) throws ArcoNoExisteException {
	
		if(infoArc!=null){
			IVertice v1 = this.vertices.get(idVertexIni);
			IVertice v2 = this.vertices.get(idVertexFin);

			arcos.put(""+idVertexIni+","+idVertexFin+"", infoArc);
			listaAdj.get(idVertexIni).add(idVertexFin);
			
			listaAdjVertices.get(idVertexIni).add(v2);
		
			listaAdjArcos.get(idVertexIni).add(infoArc);
			
		}else{
			throw new ArcoNoExisteException("El arco a a�adir es nulo", idVertexIni, idVertexFin);
		}
		
	}

	/**
	 * Obtener la informaci�n de un v�rtice
	 */
	@Override
	public IVertice<K> getInfoVertex(K idVertex) {
		return vertices.get(idVertex);
	}

	/**
	 * Modificar la informaci�n del v�rtice idVertex
	 * @throws VerticeNoExisteException 
	 */
	public void setInfoVertex(K idVertex, V infoVertex) throws VerticeNoExisteException {
		if(infoVertex!=null){
			if(vertices.containsKey(idVertex)){
				vertices.put(idVertex, infoVertex);
			}else{
				throw new VerticeNoExisteException("El vertice no est� a�adido", idVertex);
			}
		}else{
			throw new VerticeNoExisteException("El vertice por a�adir es nulo", idVertex);
		}
	}

	/**
	 * Obtener la informaci�n de un arco 	
	 * @throws ArcoNoExisteException 
	 */
	@Override
	public IArco getInfoArc(K idVertexIni, K idVertexFin) throws ArcoNoExisteException {

		if(arcos.containsKey(""+idVertexIni+","+idVertexFin+"")){
			return arcos.get(""+idVertexIni+","+idVertexFin+"");
		}else{
			throw new ArcoNoExisteException("El arco no existe", idVertexIni, idVertexFin);
		}
	}

	/**
	 * Obtener la informaci�n de un arco
	 * @throws ArcoNoExisteException 
	 */
	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc) throws ArcoNoExisteException {
		if(infoArc!=null){
			if(arcos.containsKey(""+idVertexIni+","+idVertexFin+"")){
				arcos.put(""+idVertexIni+","+idVertexFin+"", infoArc);
			}else{
				throw new ArcoNoExisteException("El arco no est� a�adido", idVertexIni, idVertexFin);
			}
		}else{
			throw new ArcoNoExisteException("El arco por a�adir es nulo", idVertexIni, idVertexFin);
		}
	}

	/**
	 * Retorna los identificadores de los v�rtices adyacentes a idVertex
	 * @throws VerticeNoExisteException 
	 */
	@Override
	public Iterator<K> adj(K idVertex) throws VerticeNoExisteException {
		if(listaAdj.containsKey(idVertex)){
			LinkedList<K> l= (LinkedList<K>) listaAdj.get(idVertex);
			return  l.iterator();
		}else{
			throw new VerticeNoExisteException("El vertice no existe en la lista", idVertex);
		}
	}
	
	public LinkedList<IVertice> adjVertexes(K idVertex) throws VerticeNoExisteException{
		if(listaAdjVertices.containsKey(idVertex)){
			LinkedList<IVertice> l= (LinkedList<IVertice>) listaAdjVertices.get(idVertex);
			return  l;
		}else{
			throw new VerticeNoExisteException("El vertice no existe en la lista", idVertex);
		}
	}
	
	public LinkedList<IArco> adjEdges(K idVertex) throws VerticeNoExisteException{
		if(listaAdjArcos.containsKey(idVertex)){
			LinkedList<IArco> l= (LinkedList<IArco>) listaAdjArcos.get(idVertex);
			return  l;
		}else{
			throw new VerticeNoExisteException("El vertice no existe en la lista", idVertex);
		}
	}


	public boolean existeVertice(K i) {
		return vertices.containsKey(i);
	}

	public boolean existeArco(int i, int j) {
		String key = ""+i+","+j+"";
		return arcos.containsKey(key);
	}

	
}
