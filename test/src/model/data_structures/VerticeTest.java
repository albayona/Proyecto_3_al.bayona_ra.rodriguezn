package model.data_structures;

import java.awt.Color;

import api.IVertice;
//Clase auxiliar para las pruebas del grafo
public class VerticeTest implements IVertice<Integer> {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int color;

	public VerticeTest(int id) {
		this.id = id;
		name = "";
		color = -1;
	}

	@Override
	public Integer darId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(IVertice<Integer> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMarcado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMarcado(boolean marcado) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPerviousVertexId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPerviousVertexId(int perviousVertexId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColor(int color) {
		this.color = color;
	}
	
	
	public int getColor() {
		return color;
	}

}
