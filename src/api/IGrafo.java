package api;

import java.util.Iterator;

import model.data_structures.grafo1.ArcoNoExisteException;
import model.data_structures.grafo1.VerticeNoExisteException;

public interface IGrafo <K extends Comparable<K>, V extends IVertice<K>, A extends IArco> {


	public int V();
	public int E();
	//public void addVertex( K idVertex, V infoVertex);
	//public void addEdge(K idVertexIni, K idVertexFin, A infoArc );
	public V getInfoVertex(K idVertex);
	//public void setInfoVertex(K idVertex, V infoVertex);
	public A getInfoArc(K idVertexIni, K idVertexFin) throws ArcoNoExisteException;
	//void setInfoArc(K idVertexIni, K idVertexFin, A infoArc);
	public Iterator <K> adj (K idVertex) throws VerticeNoExisteException;
	


}
