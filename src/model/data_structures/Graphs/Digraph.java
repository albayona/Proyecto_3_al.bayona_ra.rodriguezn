//package model.data_structures.Graphs;
//
//import java.util.Iterator;
//
//import api.IDiEdge;
//import api.IVertex;
//import model.data_structures.HashTables.HashTable;
//import model.data_structures.Lists.LinkedList;
//
//public class Digraph<K extends Comparable<K>, V extends IVertex<K>, A extends IDiEdge> {
//
//	private HashTable<K, V> vertices;
//
//	private HashTable<String, A> edges;
//
//	private HashTable<K, LinkedList<K>> adjs;
//
//	private HashTable<K, LinkedList<IVertex>> adjVertices;
//
//	private HashTable<K, LinkedList<IDiEdge>> adjEdges;
//
//
//	/**
//	 * Crea un grafo No dirigido sin v�rtices y sin arcos
//	 */
//	public Digraph( ){
//		vertices= new HashTable<K, V>(1000);
//		edges = new HashTable<String, A>(1000); //String es id1+","+id2
//		adjs = new HashTable<K, LinkedList<K>>(10000);
//		adjVertices = new HashTable<K, LinkedList<IVertex>>(100000);
//		adjEdges = new HashTable<K, LinkedList<IDiEdge>>(100000);
//	}
//
//	public HashTable<K, V> getVertices() {
//		return vertices;
//	}
//	public Iterator darVerticesKeys(){
//		return vertices.keysIterator();
//	}
//	public HashTable<String, A> getEdges() {
//		return edges;
//	}
//
//	public HashTable<K, LinkedList<K>> getAdjs() {
//		return adjs;
//	}
//
//	/**
//	 *N�mero de v�rtices
//	 */
//	public int V() {
//		return vertices.sizeKeys();
//	}
//
//	/**
//	 * N�mero de arcos. Cada arco No dirigido debe contarse una �nica vez.
//	 */
//	public int E() {
//		return edges.sizeKeys();
//	}
//
//	/**
//	 * Adiciona un v�rtice con un Id �nico. El v�rtice tiene la informaci�n InfoVertex
//	 * @throws UnexistingVertexException
//	 */
//	public void addVertex(K idVertex, V infoVertex) throws UnexistingVertexException {
//		if(infoVertex!=null){
//			vertices.put(idVertex,  infoVertex);
//			adjs.put(idVertex, new LinkedList<K>());
//			adjVertices.put(idVertex, new LinkedList<IVertex>());
//			adjEdges.put(idVertex, new LinkedList<IDiEdge>());
//		}else{
//			throw new UnexistingVertexException("El vertice a a�adir es nulo", idVertex);
//		}
//	}
//
//
//	/**
//	 * Adiciona el arco Dirigido entre el vertice IdVertexIni y el vertice IdVertexFin. El arco tiene la informaci�n infoArc.
//	 * @throws UnexistingEdgeException
//	 */
//	public void addEdge(K idVertexIni, K idVertexFin, A infoArc) throws UnexistingEdgeException {
//
//		if(infoArc!=null){
//			IVertex v1 = this.vertices.get(idVertexIni);
//			IVertex v2 = this.vertices.get(idVertexFin);
//
//			edges.put(""+idVertexIni+","+idVertexFin+"", infoArc);
//			adjs.get(idVertexIni).add(idVertexFin);
//
//			adjVertices.get(idVertexIni).add(v2);
//
//			adjEdges.get(idVertexIni).add(infoArc);
//
//		}else{
//			throw new UnexistingEdgeException("El arco a a�adir es nulo", idVertexIni, idVertexFin);
//		}
//
//	}
//
//	/**
//	 * Obtener la informaci�n de un v�rtice
//	 */
//	public IVertex<K> getInfoVertex(K idVertex) {
//		return vertices.get(idVertex);
//	}
//
//	/**
//	 * Modificar la informaci�n del v�rtice idVertex
//	 * @throws UnexistingVertexException
//	 */
//	public void setInfoVertex(K idVertex, V infoVertex) throws UnexistingVertexException {
//		if(infoVertex!=null){
//			if(vertices.containsKey(idVertex)){
//				vertices.put(idVertex, infoVertex);
//			}else{
//				throw new UnexistingVertexException("El vertice no est� a�adido", idVertex);
//			}
//		}else{
//			throw new UnexistingVertexException("El vertice por a�adir es nulo", idVertex);
//		}
//	}
//
//	/**
//	 * Obtener la informaci�n de un arco
//	 * @throws UnexistingEdgeException
//	 */
//	public IDiEdge getEdgeInfo(K idVertexIni, K idVertexFin) throws UnexistingEdgeException {
//
//		if(edges.containsKey(""+idVertexIni+","+idVertexFin+"")){
//			return edges.get(""+idVertexIni+","+idVertexFin+"");
//		}else{
//			throw new UnexistingEdgeException("El arco no existe", idVertexIni, idVertexFin);
//		}
//	}
//
//	/**
//	 * Obtener la informaci�n de un arco
//	 * @throws UnexistingEdgeException
//	 */
//	public void setInfoArc(K idVertexIni, K idVertexFin, A infoArc) throws UnexistingEdgeException {
//		if(infoArc!=null){
//			if(edges.containsKey(""+idVertexIni+","+idVertexFin+"")){
//				edges.put(""+idVertexIni+","+idVertexFin+"", infoArc);
//			}else{
//				throw new UnexistingEdgeException("El arco no est� a�adido", idVertexIni, idVertexFin);
//			}
//		}else{
//			throw new UnexistingEdgeException("El arco por a�adir es nulo", idVertexIni, idVertexFin);
//		}
//	}
//
//	/**
//	 * Retorna los identificadores de los v�rtices adyacentes a idVertex
//	 * @throws UnexistingVertexException
//	 */
//	public Iterator<K> adj(K idVertex) throws UnexistingVertexException {
//		if(adjs.containsKey(idVertex)){
//			LinkedList<K> l= (LinkedList<K>) adjs.get(idVertex);
//			return  l.iterator();
//		}else{
//			throw new UnexistingVertexException("El vertice no existe en la lista", idVertex);
//		}
//	}
//
//	public LinkedList<IVertex> adjVertices(K idVertex) throws UnexistingVertexException {
//		if(adjVertices.containsKey(idVertex)){
//			LinkedList<IVertex> l= (LinkedList<IVertex>) adjVertices.get(idVertex);
//			return  l;
//		}else{
//			throw new UnexistingVertexException("El vertice no existe en la lista", idVertex);
//		}
//	}
//
//	public LinkedList<IDiEdge> adjEdges(K idVertex) throws UnexistingVertexException {
//		if(adjEdges.containsKey(idVertex)){
//			LinkedList<IDiEdge> l= (LinkedList<IDiEdge>) adjEdges.get(idVertex);
//			return  l;
//		}else{
//			throw new UnexistingVertexException("El vertice no existe en la lista", idVertex);
//		}
//	}
//
//
//	public boolean hasVertex(K i) {
//		return vertices.containsKey(i);
//	}
//
//	public boolean hasEdge(int i, int j) {
//		String key = ""+i+","+j+"";
//		return edges.containsKey(key);
//	}
//
//	public Digraph reverse()   {
//		Digraph R = new Digraph();
//		LinkedList<IVertex> adj = null;
//		LinkedList<IVertex> vertices = (LinkedList<IVertex>) this.vertices.valuesIterator();
//		K id2 = null;
//
//		for(IVertex v : vertices) {
//			try {
//				R.addVertex(v.getId(), v);
//			} catch (UnexistingVertexException e) {
//				e.printStackTrace();
//			}
//		}
//		for(K id : this.vertices.keysIterator2()) {
//			try {
//				adj = this.adjVertices(id);
//				for(IVertex v : adj) {
//					id2 = (K) v.getId();
//					R.addEdge(id2, id, this.getEdgeInfo(id, id2));
//				}
//			} catch (UnexistingVertexException | UnexistingEdgeException e) {e.printStackTrace();}
//
//		}
//		//for (int v = 0; v < this.V(); v++)
//			//for (int w : adj(v))
//				//R.addEdge(w, v);
//		return R;
//	}
//
//
//
//}
