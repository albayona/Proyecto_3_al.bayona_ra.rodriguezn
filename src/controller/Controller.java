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

				case "A1":
					System.out.println(" \n Ingresar long1 \n");

					try {
						d1 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar lat1 \n");

					try {
						d2 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar long2 \n");

					try {
						d3 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}

					System.out.println(" \n Ingresar lat2\n");

					try {
						d4 = reader.nextDouble();
					} catch (Exception e) {
						System.out.println("Debe ingresar un n�mero");
					}



					System.out.println(model.A1(d1, d2, d3, d4));

					break;


				case "A2":


				case "B1":


					break;


				case "B3":


					break;

				case "C1":


					break;


				case "C2":


					break;

				case "C3":



					break;

				case "C4":



					break;

				default: {
					System.out.println("Opcion invalida");
				}
			}
		}
	}


}
