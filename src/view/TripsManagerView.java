package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.Graphs.UnexistingVertexException;

public class TripsManagerView
{
	public static void main(String[] args) throws UnexistingVertexException {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;

		double latitute = -1;
		double longitute = -1;

		double lo = -1;
		double hi = -1;

		int zone = -1;
		int n = -1;

		while(!fin)
		{
			printMenu();
			
			int option = sc.nextInt();

			try {


				switch (option) {

					case  0:
						Controller.loadGraph();
						break;

					case 1:
						Controller.saveGraphIntoJSON();

						System.out.println("Revise su archivo en la carpeta ./data");
						break;

					case 2:
						Controller.loadGraphFromJSON();

						break;

					case 3:

						System.out.println(" \n Ingresar longitud origen: \n");

						try {
							longitute = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.println(" \n Ingresar latitud origen: \n");

						try {
							latitute = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}


						System.out.println(" \n Ingresar longitud destino: \n");

						try {
							lo = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.println(" \n Ingresar latitud destino: \n");

						try {
							hi = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}


						System.out.print(Controller.B1(longitute, latitute , lo, hi));
						break;

					case 4:

						System.out.println(" \n Ingresar longitud: \n");

						try {
							longitute = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.println(" \n Ingresar latitud : \n");

						try {
							latitute = sc.nextDouble();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.println(" \n Ingresar tiempo : \n");
						try {
							n = sc.nextInt();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}


						System.out.print(Controller.B2(longitute, latitute , n));

						break;

					case 5:


						System.out.print(Controller.B3());



						break;
					case 6:

						System.out.print(Controller.C1());
						break;

					case 7:

						System.out.println(" \n Ingresar origen: \n");

						try {
							n = sc.nextInt();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.println(" \n Ingresar destino : \n");

						try {
							zone = sc.nextInt();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.print(Controller.C2(n, zone));

						break;

					case 8:
						System.out.println(" \n Ingresar origen: \n");

						try {
							n = sc.nextInt();
						} catch (Exception e) {
							System.out.println("Debe ingresar un n�mero");
						}

						System.out.print(Controller.C3(n));
						break;



					default: {
						System.out.println("Opcion invalida");
					}



						fin = true;
						sc.close();
						break;
				}
			}
			catch (Exception e ){
				e.printStackTrace();
			}
		}
	}

	private static void printMenu() {
		System.out.println("0. Cargar mundo");
		System.out.println("1. Guarde el grafo en archivo Json");
		System.out.println("2. Cargue el grafo de un archivo Json");
		System.out.println("3. B1");
		System.out.println("4. B2");
		System.out.println("5. B3");
		System.out.println("6. C1");
		System.out.println("7. C2");
		System.out.println("8. C3");

		
	}
}
