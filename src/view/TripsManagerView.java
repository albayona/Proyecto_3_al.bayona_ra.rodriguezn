package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.grafo1.VerticeNoExisteException;

public class TripsManagerView
{
	public static void main(String[] args) throws VerticeNoExisteException {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			printMenu();
			
			int option = sc.nextInt();

			try {


				switch (option) {
					case 1:
						Controller.loadIntersections();
						System.out.println("Vertices agreagados: " + Controller.vertices().sizeKeys());
						break;

					case 2:
						Controller.loadEdges();
						System.out.println("Arcos agreagados: " + Controller.arcos().sizeKeys());
						break;

					case 3:
						System.out.println(Controller.cc());
						break;

					case 4:
						System.out.println(Controller.verticesNumForTop5());
						break;

					case 5:

						Controller.guardarGrafoEnFormatoJSON();
						System.out.println("Revise su archivo en la carpeta ./data");

						break;
					case 6:
						Controller.cargarGrafoEnFormatoJSON();
						break;

					case 7:
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
		System.out.println("1. Cargue las intersecciones al grafo");
		System.out.println("2. Cargue los arcos al grafo");
		System.out.println("3. Componentes conexos por cc");
		System.out.println("4. 5 componentes conexos mayores");
		System.out.println("5. Guarde el grafo en archivo Json");
		System.out.println("6. Cargue el grafo de un archivo Json");
		System.out.println("7. Salir");
		
	}
}
