package api;

import java.util.Iterator;

import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.UnexistingVertexException;

public interface IGraph<K extends Comparable<K>, V extends IVertex<K>, A extends IEdge> {


	public int V();
	public int E();
	//public void addVertex( K idVertex, V infoVertex);
	//public void addEdge(K idVertexIni, K idVertexFin, A infoArc );
	public V getInfoVertex(K idVertex);
	//public void setInfoVertex(K idVertex, V infoVertex);
	public A getEdgeInfo(K idVertexIni, K idVertexFin) throws UnexistingEdgeException;
	//void setInfoArc(K idVertexIni, K idVertexFin, A infoArc);
	public Iterator <K> adj (K idVertex) throws UnexistingVertexException;
	


}
