package model.vo;

import api.IArco;
import api.IVertice;
import model.data_structures.listas.LinkedList;

public class TripleCost implements IArco {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *  Distacia Harvesiana
     */
    private Double[] pesos;


    private int numViajesEntreEstaciones;

    private IVertice<Integer> vertice1;

    private IVertice<Integer> vertice2;

    /**
     * Constructor---------------------------------------------------
     * @param distaciaHarvesiana
     */
    public TripleCost( Double[] pesos, IVertice<Integer> vertice1, IVertice<Integer> vertice2) {
        super();
        this.pesos = pesos;
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.numViajesEntreEstaciones=0;
    }


    @Override
    public Double[] darPeso() {
        return pesos;
    }


    @Override
    public IVertice<Integer> getVertice1() {
        return vertice1;
    }

    @Override
    public IVertice<Integer> getVertice2() {
        return vertice2;
    }


    @Override
    public String toString() {
        return "Distancia: " + this.pesos.toString() + " | Vertice 1: " + this.vertice1.darId() + " | Vertice 2: " + this.vertice2.darId();
    }



    @Override
    public IVertice getOtherVertex(IVertice vertex) {
        // TODO Auto-generated method stub
        if(vertex.compareTo(vertice1)==0)
            return vertice2;
        else if(vertex.compareTo(vertice2) == 0)
            return vertice1;
        else
            return null;
    }

    @Override
    public IVertice getEitherVertex() {
        // TODO Auto-generated method stub
        return this.vertice1;
    }

}
