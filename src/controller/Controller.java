package controller;

import api.IVertex;


import model.data_structures.Graphs.MaxPQ;
import model.data_structures.Graphs.UnexistingEdgeException;
import model.data_structures.Graphs.UnexistingVertexException;
import model.logic.DataLoader;
import model.logic.JSONDataManager;
import model.logic.MVCModel;
import model.vo.TripleCostEdge;

import model.data_structures.HashTables.HashTable;
import view.MVCView;

import java.util.Scanner;


public class Controller {



	private MVCModel model;


	private MVCView view;


	public Controller() throws UnexistingVertexException {
		model = new MVCModel();
		view = new MVCView();
	}


	public void run() throws UnexistingVertexException {

		Scanner reader = new Scanner(System.in);
		boolean stop = false;


		int N = -1;

		double d1 = -1;
		double d2 = -1;

		double d3 = -1;
		double d4 = -1;

		int n = -1;
		int hour = -1;



		while (!stop) {
			view.printMenu();

			String option = String.valueOf(reader.next());

			switch (option) {
				case "1":

					model.loadData();

					break;

				//================================================================
				// PARTE A
				//================================================================

				case "A1":
					System.out.println(" \n Ingresar longitud de origen  \n");

					try {
						d1 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar latitud de origen \n");

					try {
						d2 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar longitud de destino \n");

					try {
						d3 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar latitud de destino \n");

					try {
						d4 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}



					System.out.println(model.A1(d1, d2, d3, d4));

					break;

				//================================================================


				case "A2":

//				System.out.println("Determinar	los	n	vértices	con	menor	velocidad	promedio	en	la	ciudad	de	Bogotá");
//				System.out.println(" \n Ingresar el numero \n");
//				int nn = 0;
//				try {
//					nn = reader.nextInt();
//				} catch (Exception e) {
//					System.out.println("Debe ingresar un nï¿½mero");
//				}
//				System.out.println(model.A2(nn));
//				break;

					//================================================================

				case "A3":

					System.out.println("Calcular un	árbol de expansión mínima");

					System.out.println(model.A3());

					System.out.println("Tiempo que tarda el algoritmo");
					break;



				//================================================================
				// PARTE B
				//================================================================

				case "B1":

					System.out.println("Encontrar el camino	de	menor costo	(menor	distancia Haversine)");

					System.out.println(" \n Ingresar longitud de origen  \n");

					try {
						d1 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar latitud de origen \n");

					try {
						d2 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar longitud de destino \n");

					try {
						d3 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar latitud de destino\n");

					try {
						d4 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(model.B1(d1, d2, d3, d4));

					break;

				//================================================================

				case "B2":

					System.out.println("Indicar	cuáles vértices son	alcanzables	para un	tiempo T ");

					System.out.println(" \n Ingresar longitud de origen  \n");

					try {
						d1 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar latitud de destino \n");

					try {
						d2 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar el tiempo T en segundos \n");

					double tiempo = 0;
					try {
						tiempo = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(model.B2(d1, d2, tiempo));
					break;

				//================================================================

				case "B3":

					System.out.println(" Calcular un árbol de expansión	mínima (MST) con criterio distancia");


					System.out.println(model.B3());


					break;

				//================================================================
				// PARTE C
				//================================================================


				case "C1":

					System.out.println("Construir un nuevo grafo simplificado No dirigido de las zonas Uber");
					try {
						System.out.println(model.C1());
					} catch (UnexistingEdgeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				//================================================================

				case "C2":

					System.out.println("Calcular	el	camino	de	costo	mínimo	(algoritmo	de	Dijkstra)	basado	en	el	tiempo	promedio");

					System.out.println(" \n Ingresar zona origen  \n");
					int origen = 0;
					try {
						origen = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(" \n Ingresar zona destino \n");
					int destino = 0;
					try {
						destino = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println(model.C2(origen, destino));
					break;
				//================================================================

				case "C3":

					System.out.println(" \n Ingresar zona origen  \n");
					int origen1 = 0;
					try {
						origen = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un nï¿½mero");
					}

					System.out.println("calcular	los	caminos	de	menor	longitud desde una zona origen");

					System.out.println(model.C3(origen1));
					break;

				//================================================================

				default: {
					System.out.println("Opcion invalida");
				}
			}
		}
	}


}
