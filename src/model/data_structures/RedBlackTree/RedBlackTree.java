package model.data_structures.RedBlackTree;

import java.util.ArrayList;
import java.util.Iterator;

import model.data_structures.Lists.LinkedList;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

	//--------------------------------------------------------
	//Constantes
	//--------------------------------------------------------

	private static final boolean RED   = true; 
	private static final boolean BLACK = false;


	//--------------------------------------------------------
	//Atributos
	//--------------------------------------------------------

	private RBTNode<Key, Value> root; 


	//--------------------------------------------------------
	//Constructores
	//--------------------------------------------------------

	public RedBlackTree() {
		root = null;
	}

	//--------------------------------------------------------
	//Metodos de insercion y eliminacion
	//--------------------------------------------------------

	/**
	 * Inserta un nuevo elemento al rbol
	 * @param pKey La llave asociada al valor que se desea insertar
	 * @param pVal El valor que se desea insertar
	 */
	public void put(Key pKey, Value pVal) {

		RBTNode<Key, Value> nuevo = new RBTNode<Key, Value>(pKey, pVal, RED);

		if(root != null) {
			root.put(nuevo);
			root.setColorToBlack();
		}
		else {
			root = nuevo;
			root.setColorToBlack();
		}
	}

	/**
	 * Elimina el valor asociado a la llave que se pasapor parametro.
	 * @param pKey la llave asociada al valor buscado
	 */
	public void delete(Key pKey) throws Exception {
		if(root != null ) {
			int actualSize = root.getSize();
			if(root.getLeft() != null && root.getLeft().isRed() && root.getRight() != null && root.getRight().isRed()){ 
				root.setColorToRed();
			}
			root = root.delete(pKey);
			if(root != null)
				root.setColorToBlack();
			if(root != null && actualSize == root.getSize())
				throw new Exception("No existe ningun valor asociado a esa llave");
		}
		else {
			throw new Exception("El arbol esta vacio");
		}
	}


	//--------------------------------------------------------
	//Metodos de busqueda o consulta
	//--------------------------------------------------------

	/**
	 * Indica si el �rbol est� vacio, si el nodo raiz es null
	 * @return true si est� vac�o, false de lo contrario
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Retorna el elemento asociado a la llave que se pasa por parametros
	 * @param llaveBuscada llava asociada al vlor buscado
	 * @return Value: el valor o buscado o null si no existe o el arbol est� vacio
	 */
	public Value get(Key llaveBuscada) {
		if(root != null) {
			return root.get(llaveBuscada);
		}
		return null;
	}

	public Value getClosestKeyValue(Key llaveBuscada) {
		if(root != null) {
				if (contains(llaveBuscada)) return root.get(llaveBuscada);
				else  return root.getClosest(llaveBuscada);
		}
		return null;
	}

	/**
	 * Indica si el �rbol contiene la llave que se pasa por parametro
	 * @param key Llave que se desea buscar
	 * @return true si existe, false de lo contrario
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * Retorna la altura del �rbol definida como la altura de la rama m�s alta 
	 * (aquella que tenga mayor n�mero de enlaces desde la ra�z a una hoja). 
	 * @return int : la altura del �rbol
	 */
	public int height() {
		return root == null ? 0 : root.height();
	}

	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key (si la llave existe). 
	 * @return int : la altura del camino desde la raiz, �1 si la llave No existe 
	 */
	public int getHeight(Key k) {
		int height = 0;
		if(root != null) {
			height = root.getHeight(k, height);
			if(!this.contains(k)) {
				height = -1;
			}
		}
		return height;
	}

	/**
	 * Retornar el n�mero de parejas [llave,Valor] del �rbol 
	 * @return int : el tama�o del arbol
	 */
	public int size() {
		return root == null ? 0 : root.getSize();
	}

	/**
	 * Retorna todas llaves del �rbol como un iterador
	 * @return Iterator<Key> : iterador de todas las llaves del �rbol
	 */
	public Iterator<Key> keysIterator(){
		LinkedList<Key> keys = new LinkedList<Key>();
		if(root != null) {
			root.keysList(keys);
		}
		return keys.iterator();
	}

	/**
	 * Retorna todas llaves del �rbol como un iterador
	 * @return Iterator<Key> : iterador de todas las llaves del �rbol
	 */
	public Iterator<Value> valuesIterator(){
		ArrayList<Value> values = new ArrayList<Value>();
		if(root != null) {
			root.valuesList(values);
		}
		return values.iterator();
	}


	/**
	 * Retorna todos los valores V en el �rbol que est�n asociados al rango de llaves dado. 
	 * Por eficiencia, debe intentarse No recorrer todo el �rbol. 
	 * @param init Llave inicia del rango
	 * @param end Llave final del rango
	 * @return Iterator<Key> : iterador de los valores que se encuentran en el rango dado
	 */
	public Iterator<Value> valuesInRange(Key init, Key end){
		ArrayList<Value> values = new ArrayList<Value>();
		if(root != null) {
			root.valuesInRange(values, init, end);
		}
		return values.iterator();
	}

	/**
	 * Retorna todas las llaves K en el �rbol que se encuentran en el rango de llaves dado. 	 
	 * Por eficiencia, debe intentarse No recorrer todo el �rbol. 
	 * @param init Llave inicia del rango
	 * @param end Llave final del rango
	 * @return Iterator<Key> : iterador de las llaves que se encuentran en el rango dado
	 */
	public Iterator<Key> keysInRange(Key init, Key end){
		LinkedList<Key> keys = new LinkedList<Key>();
		if(root != null) {
			root.keysInRange(keys, init, end);
		}
		return keys.iterator();
	}

	/**
	 * Retorna el valor asociado a la llave menor
	 * @return Value : el valor asociado a la llave menor, o null si el �rbol est� vac�o
	 */
	public Value getMinValue() {
		return root == null ? null: root.getMin().getVal();
	}

	/**
	 * Retorna el valor asociado a la llave mayor
	 * @return Value : el valor asociado a la llave mayor, o null si el �rbol est� vac�o
	 */
	public Value getMaxValue() {
		return root == null ? null: root.getMax().getVal();
	}

	/**
	 * Retorna la llave m�s peque�a del �rbol. 
	 * @return Key la llave menor del �rbol o null si el �rbol est� vac�o 
	 */
	public Key min() {
		return root == null ? null: root.min();
	}

	/**
	 * Retorna la llave m�s grande del �rbol. 
	 * @return Key la llave m�s grande del �rbol o null si el �rbol est� vac�o 
	 */
	public Key max() {
		return root == null ? null: root.max();
	}

	/**
	 * Valida si el �rbol es Binario Ordenado y est� balanceado RojoNegro a la izquierda. 
	 * @return true si cumple con los requerimientos, false de lo contrario
	 */
	public boolean check() {
		return padresMayoresQueHijoIzquierdo() && padresMenoresQueHijoDerecho() 
				&& hijoDerechoNoEsRojo() && noHayPadreNiHijoRojoSeguidos() 
				&& nodosNegrosBalanceados();
	}

	//--------------------------------------------------------
	//Metodos auxiliares
	//--------------------------------------------------------

	/**
	 * Valida que la llave de cada nodo sea mayor que cualquiera de su sub�rbol izquierdo.
	 * @return true si cumple con los requerimientos, false de lo contrario 
	 */
	public boolean padresMayoresQueHijoIzquierdo() {
		return root == null ? true : root.padresMayoresQueHijoIzquierdo() ;
	}

	public boolean padresMenoresQueHijoDerecho() {
		return root == null ? true : root.padresMenoresQueHijoDerecho() ;
	}

	public boolean hijoDerechoNoEsRojo() {
		return root == null ? true : root.hijoDerechoNoEsRojo() ;

	}

	public boolean noHayPadreNiHijoRojoSeguidos() {
		return root == null ? true : root.noHayPadreNiHijoRojoSeguidos() ;

	}

	public boolean nodosNegrosBalanceados() {

		if(root != null) {
			int max = root.maxNumberBlackSubNodes();
			System.out.println(max);
			return isBalanced(root, max);

		}
		return true;
	}

	private boolean isBalanced(RBTNode<Key, Value> node, int num) {
		if (node == null) return num == 0;
		if (node.isBlack())
			num--;
		return isBalanced(node.getLeft(), num) && isBalanced(node.getRight(), num);
	} 


}
