package model.logic;

import model.data_structures.Graphs.Graph;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.UnexistingVertexException;
import model.data_structures.Lists.LinkedList;
import model.data_structures.RedBlackTree.RedBlackTree;
import model.vo.TravelTime;
import model.vo.TripleCostEdge;
import model.vo.VOIntersection;

public class MVCModel {

    private  BRequirementsManager BManager;
    private CRequirementsManager CManager;
    private ARequirementsManager AManager;
    RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> travelTimesTree;

    private Graph<Integer, VOIntersection, TripleCostEdge> graph1;

    public MVCModel() throws UnexistingVertexException {
        BManager = new BRequirementsManager();
        CManager = new CRequirementsManager();
        AManager = new ARequirementsManager();

        graph1 = new Graph<>();




    }

    public void  loadData() throws UnexistingVertexException {
        DataLoader  loader = new DataLoader(graph1);
        loader.loadIntersection("./data/bogota_vertices.txt");
        travelTimesTree = new RedBlackTree<>();


        travelTimesTree = loader.loadTree();

        loader.loadEdges("./data/bogota_arcos.txt");
    }

    public String A1( double longS, double latS, double longD, double latD) throws UnexistingVertexException {
        return  AManager.A1(graph1, longS, latS, longD, latD);

    }

    public String A3() throws UnexistingVertexException {
        return  AManager.A3(graph1);

    }

    public String B1( double longS, double latS,double longD,  double latD ) throws UnexistingVertexException {
        return  BManager.B1(graph1, longS, latS, longD, latD);

    }

    public String B2(  double longS, double latS, double time  ) throws UnexistingVertexException {
        return  BManager.B2(graph1, longS, latS, time);

    }

    public String B3(  ) throws UnexistingVertexException {
        return  BManager.B3(graph1);

    }


    public String C1(  ) throws UnexistingVertexException, UnexistingEdgeException {
        return  CManager.C1(graph1, travelTimesTree);

    }

    public String C2(  int origen, int destino,  ) throws UnexistingVertexException {
        return  CManager.C2(origen, destino, travelTimesTree);

    }

    public String C3(    int origen) throws UnexistingVertexException {
        return  CManager.C3(origen);

    }

    public BRequirementsManager getBManager() {
        return BManager;
    }

    public CRequirementsManager getCManager() {
        return CManager;
    }

    public RedBlackTree<Integer, RedBlackTree<Integer, LinkedList<TravelTime>>> getTravelTimesTree() {
        return travelTimesTree;
    }

    public Graph<Integer, VOIntersection, TripleCostEdge> getGraph1() {
        return graph1;
    }

    public static void main(String[] args) throws UnexistingVertexException, UnexistingEdgeException {

        MVCModel model = new MVCModel();

        double a = 0.0;

        System.out.print(model.CManager.C1(model.graph1, model.travelTimesTree));
      System.out.print(model.CManager.C3(10));




    }

}
