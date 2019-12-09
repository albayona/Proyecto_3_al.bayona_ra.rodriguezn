package model.data_structures;

import api.IVertex;
//Clase auxiliar para las pruebas del grafo
public class VertexTest implements IVertex<Integer> {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int color;

	public VertexTest(int id) {
		this.id = id;
		name = "";
		color = -1;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(IVertex<Integer> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMarked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMarked(boolean marcado) {
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
