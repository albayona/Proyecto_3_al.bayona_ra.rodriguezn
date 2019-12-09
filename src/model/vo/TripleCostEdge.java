package model.vo;

import api.IEdge;
import api.IVertex;

public class TripleCostEdge implements IEdge {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *  Distacia Harvesiana, tiempo promedio, velocidad
     */
    private Double[] weights;


    private IVertex<Integer> vertex1;

    private IVertex<Integer> vertex2;

    /**
     * Constructor---------------------------------------------------
     */
    public TripleCostEdge(Double[] weights, IVertex<Integer> vertex1, IVertex<Integer> vertex2) {
        super();
        this.weights = weights;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }


    @Override
    public double weight() {
        return weights[0];
    }

    @Override
    public double weight(int index) {
        return weights[index];
    }


    @Override
    public IVertex<Integer> getVertex1() {
        return vertex1;
    }

    @Override
    public IVertex<Integer> getVertex2() {
        return vertex2;
    }


    @Override
    public String toString() {
        return " (" + this.vertex1.getId() + "<->" + this.vertex2.getId() + ") ";
    }



    @Override
    public IVertex other(int vertex) {
        // TODO Auto-generated method stub
        if(vertex == vertex1.getId())
            return vertex2;
        else if(vertex == vertex2.getId())
            return vertex1;
        else
            return null;
    }

    @Override
    public IVertex either() {
        // TODO Auto-generated method stub
        return this.vertex1;
    }

}
