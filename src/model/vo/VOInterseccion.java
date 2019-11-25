package model.vo;

import java.awt.Color;

import api.IVertice;

public class VOInterseccion implements IVertice<Integer>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double latitud;
	private double longitud;
	private boolean marcado;
	private int zona;
	private int previousVertexId;


	public VOInterseccion(int id, double latitud, double longitud, int zona) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.marcado = false ;
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

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	@Override
	public Integer darId() {
		Integer I = (Integer) new java.lang.Integer(id);
		return I;
	}

	@Override
	public String toString() {
		return "ID: " + this.id + " | LATITUD: " + this.latitud + " | LONGITUD: " + this.longitud;
	}


	/**
	 * @return the marcado
	 */
	public boolean isMarcado() {
		return marcado;
	}


	/**
	 * @param marcado the marcado to set
	 */
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}


	@Override
	public int compareTo(IVertice<Integer> o) {
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
