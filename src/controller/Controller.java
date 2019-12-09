package controller;

import api.IVertex;


import model.data_structures.Graphs.MaxPQ;
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

		double latitute = -1;
		double longitute = -1;

		double lo = -1;
		double hi = -1;

		int zone = -1;
		int hour = -1;



		while (!stop) {
			view.printMenu();

			String option = String.valueOf(reader.next());

			switch (option) {
				case "1":

					model.loadData();

					break;

				case "A1":
					System.out.println(" \n Ingresar cantidad de letras mas frecuentes a mostrar: \n");

					try {
						N = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(model.A1());

					break;


				case "A2":
					System.out.println(" \n Ingresar longitud: \n");

					try {
						longitute = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar latitud: \n");

					try {
						latitute = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printListA2(model.A2(latitute,longitute));


					break;

				case "A3":
					System.out.println(" \n Ingresar tiempo inferior: \n");

					try {
						lo = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar tiempo superior: \n");

					try {
						hi = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printArrA3(model.A3(lo,hi));

					break;


				case "B1":
					System.out.println(" \n Ingresar cantidad a mostrar: \n");

					try {
						N = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printArrayB1(model.B1(N));

					break;

				case "B2":


					System.out.println(" \n Ingresar longitud: \n");

					try {
						longitute = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar latitud: \n");

					try {
						latitute = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}


					printListB2(model.B2(latitute,longitute));


					break;


				case "B3":
					System.out.println(" \n Ingresar desviacion inferior: \n");


					try {
						lo = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar desviacion superior: \n");

					try {
						hi = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printArrB3(model.B3(lo,hi));

					break;

				case "C1":
					System.out.println(" \n Ingresar zona de origen: \n");

					try {
						zone = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar una hora: \n");

					try {
						hour = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printListC2(model.C1(zone,hour));

					break;


				case "C2":
					System.out.println(" \n Ingresar zona de destino: \n");

					try {
						zone = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar hora inferior: \n");

					try {
						lo = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar hora superior: \n");

					try {
						hi = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printListC2(model.C2(zone,(int)lo,(int)hi));

					break;

				case "C3":

					System.out.println(" \n Ingresar cantidad zonas a mostrar: \n");

					try {
						N = reader.nextInt();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					printArrC3(model.C3(N));

					break;

				case "C4":

					System.out.println(model.C4());

					break;

				default: {
					System.out.println("Opcion invalida");
				}
			}
		}
	}


}
