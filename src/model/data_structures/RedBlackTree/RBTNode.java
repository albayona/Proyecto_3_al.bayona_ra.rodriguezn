package model.data_structures.RedBlackTree;

import java.util.ArrayList;

import model.data_structures.listas.LinkedList;

public class RBTNode<Key extends Comparable<Key>, Value> {

	private static final boolean RED   = true; 
	private static final boolean BLACK = false;

	//--------------------------------------------------------
	//Atributos
	//--------------------------------------------------------

	/**
	 * Llave asociada.
	 */
	private Key key;           

	/**
	 * Valor asociado.
	 */
	private Value value;         

	/**
	 * Nodo de la izquierda.
	 */
	private RBTNode<Key, Value> leftNode;  

	/**
	 * Nodo de la derecha.
	 */
	private RBTNode<Key, Value> rightNode;  

	/**
	 * Color del nodo.
	 */
	private boolean color;     

	//--------------------------------------------------------
	//Constructores
	//--------------------------------------------------------

	/**
	 * Constructor del nodo
	 * @param key
	 * @param val
	 * @param color
	 * @param size
	 */
	public RBTNode(Key key, Value val, boolean color) {
		this.key = key;
		this.value = val;
		this.color = color;
	}

	//--------------------------------------------------------
	//Metodos de consulta
	//--------------------------------------------------------

	public boolean isRed() {  
		return color == RED; 
	}

	public boolean isBlack() {
		return color == BLACK;
	}

	public void keysList(LinkedList<Key> keys) {
		if(leftNode != null)
			leftNode.keysList(keys);

		keys.add(key);

		if(rightNode != null)
			rightNode.keysList(keys);
	}

	public void valuesList(ArrayList<Value> values) {
		if(leftNode != null)
			leftNode.valuesList(values);

		values.add(value);

		if(rightNode != null)
			rightNode.valuesList(values);
	}

	public void valuesInRange(ArrayList<Value> values, Key init, Key end) {
		int inferior = init.compareTo(key);
		int superior = end.compareTo(key);

		if(inferior <= 0 && superior >= 0) { // la llave es mayor al limInferior y menor al limSuperior
			values.add(value); // se agrega
			if(rightNode != null)
				rightNode.valuesInRange(values, init, end);;
				if(leftNode != null)
					leftNode.valuesInRange(values, init, end);;
		}
		if(inferior > 0 && rightNode != null) { // la llave es menor al inferior, se va por la derecha
			rightNode.valuesInRange(values, init, end);;
		}
		if(superior < 0 && leftNode != null){ // la llave es mayor al superior, se va por la derecha
			leftNode.valuesInRange(values, init, end);
		}
	}

	public void keysInRange(LinkedList<Key> keys, Key init, Key end) {
		int inferior = init.compareTo(key);
		int superior = end.compareTo(key);

		if(inferior <= 0 && superior >= 0) { // la llave es mayor al limInferior y menor al limSuperior
			keys.add(key); // se agrega
			if(rightNode != null)
				rightNode.keysInRange(keys, init, end);;
				if(leftNode != null)
					leftNode.keysInRange(keys, init, end);;
		}
		if(inferior > 0 && rightNode != null) { // la llave es menor al inferior, se va por la derecha
			rightNode.keysInRange(keys, init, end);;
		}
		if(superior < 0 && leftNode != null){ // la llave es mayor al superior, se va por la derecha
			leftNode.keysInRange(keys, init, end);
		}
	}



	/**
	 * Retorna la altura del nodo
	 * @return int con la altura del nodo
	 */
	public int height() {
		int alturaDerecha = rightNode == null ? 0 : rightNode.height();
		int alturaIquierda = leftNode == null ? 0 : leftNode.height();
		return 1 + Math.max(alturaDerecha, alturaIquierda);
	}

	/**
	 * Retorna la cantidad de subnodos que tiene el nodo actual, incluido �l mismo
	 * @return int nodos del actual
	 */
	public int getSize() {
		int peso1 = (leftNode == null) ? 0: leftNode.getSize();
		int peso2 = (rightNode == null) ? 0: rightNode.getSize();
		return 1 + peso1 + peso2;		
	}

	public int maxNumberBlackSubNodes() {
		int blacks1 = (leftNode == null) ? 0 : leftNode.maxNumberBlackSubNodes();
		int blacks2 = (rightNode == null) ? 0 : rightNode.maxNumberBlackSubNodes();
		return isBlack() ? 1 + Math.max(blacks1, blacks2) : Math.max(blacks1, blacks2);
	}


	public Value get(Key llaveBuscada) {

		Value buscado = null;
		int comp = key.compareTo(llaveBuscada);
		if(comp == 0) {
			buscado = value;
		}
		else if(comp < 0 && rightNode != null) {
			buscado = rightNode.get(llaveBuscada);
		}
		else if(comp > 0 && leftNode != null) {
			buscado = leftNode.get(llaveBuscada);
		}
		return buscado;
	}

	public RBTNode<Key, Value> getMin()
	{
		return (leftNode == null) ? this: leftNode.getMin();
	}
	public RBTNode<Key, Value> getMax()
	{
		return (rightNode == null) ? this: rightNode.getMax();
	}

	//--------------------------------------------------------
	//Metodos de incersion y eliminacion
	//--------------------------------------------------------

	public void put(RBTNode<Key, Value> nuevo) {

		int comparacion = key.compareTo(nuevo.getKey());

		if(comparacion == 0) { // son iguales
			this.value = nuevo.value; //se reemplaza el valor por el nuevo
		}
		else if(comparacion < 0) { // la llave que se pasa por parametro es mayor
			if(rightNode != null) {
				rightNode.put(nuevo);
			}
			else {
				this.rightNode = nuevo;
			}
		}
		else if(comparacion > 0) {// la llave que se pasa por parametro es menor
			if(leftNode != null) {
				leftNode.put(nuevo);
			}
			else {
				this.leftNode = nuevo;
			}
		}
		// se ajustan los colores para que cumplan con las reglas
		setColorsAfterInsertion();
	}

	/**
	 *Elimina el nodo cuya llave llega por par�metro. Al eliminar se busca un reemplazo.
	 * @param pKey la llave asociada al valor que se desea eliminar
	 * @return El sucesor del nodo que se elimina
	 */
	public RBTNode<Key, Value> delete(Key pKey) {

		if (pKey.compareTo(key) >= 0)  { // la llave parametro es mayor o igual
			if (leftNode != null && this.leftNode.isRed())
				rotateInRightDirection();
			if (pKey.compareTo(key) == 0 && this.rightNode == null && this.leftNode == null)
				return null;
			if (rightNode != null && this.rightNode.isBlack() && this.rightNode.leftNode !=  null && this.rightNode.leftNode.isBlack()) {
				swapColors();
				if (leftNode != null && leftNode.leftNode != null && leftNode.leftNode.isRed()) { 
					rotateInRightDirection();
					swapColors();
				}
			}
			if (pKey.compareTo(key) == 0) {
				RBTNode<Key, Value> temp = rightNode.getMin();
				key = temp.key;
				value = temp.value;
				rightNode = rightNode.deleteMin();
			}
			else rightNode = rightNode.delete(pKey);
		}
		else {
			if (leftNode != null && leftNode.isBlack() && leftNode.leftNode != null && leftNode.leftNode.isBlack()) {
				swapColors();
				if (rightNode.leftNode.isRed()) { 
					rightNode.rotateInRightDirection();
					rotateInLeftDirection();
					swapColors();
				}
			}
			leftNode = leftNode.delete(pKey);
		}

		return balanceNodes();

	}


	private RBTNode<Key, Value> deleteMin() { 
		if (leftNode == null)
			return null;

		if (leftNode.isBlack() && leftNode.getLeft() != null && leftNode.getLeft().isBlack()) {
			swapColors();
			if (rightNode.leftNode.isRed()) { 
				rightNode.rotateInRightDirection();
				rotateInLeftDirection();
				swapColors();
			}
		}
		leftNode = leftNode.deleteMin();
		return balanceNodes();
	}

	//--------------------------------------------------------
	//Metodos de rotacion y ajuste de colores
	//--------------------------------------------------------

	private RBTNode<Key, Value> balanceNodes() {

		if (rightNode != null && rightNode.isRed()) {
			rotateInLeftDirection();
		}
		if(leftNode != null && leftNode.isRed() && leftNode.getLeft() != null && leftNode.getLeft().isRed()) {
			rotateInRightDirection();
		}
		if(leftNode != null && leftNode.isRed() && rightNode != null && rightNode.isRed()) {
			swapColors();
		}

		return this;
	}

	public void setColorsAfterInsertion() {
		//Si ambos hijos son rojos, intercambiar colores
		if(leftNode != null && leftNode.isRed() && rightNode != null && rightNode.isRed()) {
			swapColors();
		}
		if(leftNode == null && rightNode != null && rightNode.isRed()) {
			rotateInLeftDirection();
		}
		//Si el hijo de la derecha es rojo y el de la izquierda es negro, rotar hacia la izquierda
		if(rightNode != null && rightNode.isRed() && leftNode != null && leftNode.isBlack()) {
			rotateInLeftDirection(); 
		}
		//Si el hijo de la izquierda y su hijo izquierdo son rojos, rotar hacia la derecha
		if(leftNode != null && leftNode.isRed() && leftNode.getLeft() != null && leftNode.getLeft().isRed()) {
			rotateInRightDirection();
		}
	}

	private void swapColors() {
		// TODO Auto-generated method stub
		color = color == RED ? BLACK : RED;
		rightNode.color = rightNode.color == RED ? BLACK : RED;
		leftNode.color = leftNode.color == RED ? BLACK : RED;
	}

	private void rotateInRightDirection() {
		RBTNode<Key, Value> thisNode = new RBTNode<Key, Value>(this.key, this.value, this.color);
		thisNode.rightNode = rightNode;

		this.key = leftNode.key;
		this.value = leftNode.value;
		this.color = leftNode.color;
		this.rightNode = leftNode.rightNode;
		this.leftNode = leftNode.leftNode;

		thisNode.leftNode = rightNode;
		rightNode = thisNode;
		color = rightNode.color;
		rightNode.color = RED;
	}

	private void rotateInLeftDirection() {
		RBTNode<Key, Value> thisNode = new RBTNode<Key, Value>(this.key, this.value, this.color);
		thisNode.leftNode = leftNode;

		this.key = rightNode.key;
		this.value = rightNode.value;
		this.color = rightNode.color;
		this.leftNode = rightNode.leftNode;
		this.rightNode = rightNode.rightNode;

		thisNode.rightNode = leftNode;
		leftNode = thisNode;
		color = leftNode.color;
		leftNode.color = RED;
	}

	//--------------------------------------------------------
	//Getters y setters
	//--------------------------------------------------------

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getVal() {
		return value;
	}

	public void setVal(Value val) {
		this.value = val;
	}

	public RBTNode<Key, Value> getLeft() {
		return leftNode;
	}

	public void setLeft(RBTNode<Key, Value> left) {
		this.leftNode = left;
	}

	public RBTNode<Key, Value> getRight() {
		return rightNode;
	}

	public void setRight(RBTNode<Key, Value> right) {
		this.rightNode = right;
	}

	public boolean getColor() {
		return color;
	}

	/**
	 * Cambia el color del nodo a rojo
	 */
	public void setColorToRed() {
		this.color = RED;
	}

	/**
	 * Cambia el color del nodo a negro
	 */
	public void setColorToBlack() {
		this.color = BLACK;
	}

	public boolean hasChildren() {
		return leftNode != null || rightNode != null;
	}

	public String toString() {
		String colorS = (color == RED) ? "RED" : "BLACK";
		return "Key: " +key.toString() + "| Value: " + value.toString() + "(" + colorS +  ")";
	}

	public int getHeight(Key k, int height) {
		height = height+1;
		int comp = key.compareTo(k);
		if(comp == 0) {
		}
		else if(comp < 0 && rightNode != null) {
			return rightNode.getHeight(k, height);
			
		}
		else if(comp > 0 && leftNode != null) {
			return leftNode.getHeight(k, height);
			
		}
		return height;
	}

	public Key min() {
		return (leftNode == null) ? this.key: leftNode.min();

	}

	public Key max() {
		return (rightNode == null) ? this.key: rightNode.max();
	}

	public boolean padresMayoresQueHijoIzquierdo() {
		if(leftNode != null && key.compareTo(leftNode.key) < 0) {
			return false;
		}
		else {
			if(leftNode != null && rightNode == null)
				return leftNode.padresMayoresQueHijoIzquierdo();
			else if(rightNode != null && leftNode == null)
				return rightNode.padresMayoresQueHijoIzquierdo();
			else if(rightNode == null && leftNode == null)
				return true;
			else
				return leftNode.padresMayoresQueHijoIzquierdo() && rightNode.padresMayoresQueHijoIzquierdo();
		}
	}

	public boolean padresMenoresQueHijoDerecho() {
		if(rightNode != null && key.compareTo(rightNode.key) > 0) {
			return false;
		}
		else {
			if(leftNode != null && rightNode == null)
				return leftNode.padresMenoresQueHijoDerecho();
			else if(rightNode != null && leftNode == null)
				return rightNode.padresMenoresQueHijoDerecho();
			else if(rightNode == null && leftNode == null)
				return true;
			else
				return leftNode.padresMenoresQueHijoDerecho() && rightNode.padresMenoresQueHijoDerecho();
		}
	}

	public boolean hijoDerechoNoEsRojo() {
		if(rightNode != null && rightNode.isRed()) {
			return false;
		}
		else {
			if(leftNode != null && rightNode == null)
				return leftNode.hijoDerechoNoEsRojo();
			else if(rightNode != null && leftNode == null)
				return rightNode.hijoDerechoNoEsRojo();
			else if(rightNode == null && leftNode == null)
				return true;
			else
				return leftNode.hijoDerechoNoEsRojo() && rightNode.hijoDerechoNoEsRojo();
		}
	}

	public boolean noHayPadreNiHijoRojoSeguidos() {
		if(this.isRed()) {

			if(rightNode != null && rightNode.isRed())
				return false;
			if(leftNode != null && leftNode.isRed())
				return false;
		}
		else {
			if(leftNode != null && rightNode == null)
				return leftNode.noHayPadreNiHijoRojoSeguidos();
			else if(rightNode != null && leftNode == null)
				return rightNode.noHayPadreNiHijoRojoSeguidos();
			else if(rightNode == null && leftNode == null)
				return true;
			else
				return leftNode.noHayPadreNiHijoRojoSeguidos() && rightNode.noHayPadreNiHijoRojoSeguidos();
		}
		return true;
	}

}
