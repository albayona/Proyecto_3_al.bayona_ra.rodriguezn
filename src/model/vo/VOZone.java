package model.vo;

import api.IVertex;

public class VOZone implements IVertex<Integer> {

    private static final long serialVersionUID = 1L;
    private int id;
    private double latitud;
    private double longitude;
    private  boolean marked;


    public VOZone(int id, double latitud, double longitude) {
        this.id = id;
        this.latitud = latitud;
        this.longitude = longitude;
        marked = false;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(IVertex<Integer> o) {
        return 0;
    }

    @Override
    public boolean isMarked() {
        return marked;
    }

    @Override
    public void setMarked(boolean marcado) {
        marked = marcado;
    }

    @Override
    public int getPerviousVertexId() {
        return 0;
    }

    @Override
    public void setPerviousVertexId(int perviousVertexId) {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public int getColor() {
        return 0;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return this.id  + " (" + this.longitude + ", " + this.latitud + ")" ;
    }
}
