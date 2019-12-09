package model.vo;

import api.IVertex;

public class VOIntersection implements IVertex<Integer> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double latitud;
	private double longitude;
	private boolean marked;
	private int zona;
	private int previousVertexId;


	public VOIntersection(int id, double latitud, double longitude, int zona) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitude = longitude;
		this.marked = false ;
		this.previousVertexId=-1;
		this.zona = zona;
	}

	public int getZona() {
		return zona;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitud) {
		this.longitude = longitud;
	}


	@Override
	public Integer getId() {
		Integer I = (Integer) new java.lang.Integer(id);
		return I;
	}

	@Override
	public String toString() {
		return this.id  + " (" + this.longitude + ", " + this.latitud + ")" ;
	}


	/**
	 * @return the marcado
	 */
	public boolean isMarked() {
		return marked;
	}


	/**
	 * @param marcado the marcado to set
	 */
	public void setMarked(boolean marcado) {
		this.marked = marcado;
	}


	@Override
	public int compareTo(IVertex<Integer> o) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * @return the perviousVertexId
	 */
	public int getPerviousVertexId() {
		return previousVertexId;
	}


	/**
	 * @param perviousVertexId the perviousVertexId to set
	 */
	public void setPerviousVertexId(int perviousVertexId) {
		this.previousVertexId = perviousVertexId;
	}


	@Deprecated
	public void setColor(int color) {
		// TODO Auto-generated method stub

	}

	@Deprecated
	public int getColor() {
		// TODO Auto-generated method stub
		return 0;
	}


}
