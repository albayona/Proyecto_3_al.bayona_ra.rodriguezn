package controller;

import api.IVertex;


import model.data_structures.Graphs.MaxPQ;
import model.data_structures.Graphs.UnexistingVertexException;
import model.logic.DataLoader;
import model.logic.JSONDataManager;
import model.logic.MVCModel;
import model.vo.TripleCostEdge;

import model.data_structures.HashTables.HashTable;


public class Controller {

    /**
     * Reference to the services manager
     */

    private  MVCModel model;


	public Controller() throws UnexistingVertexException {
		this.model = new MVCModel();
	}

	public static void loadGraph(){

	}

    public  void saveGraphIntoJSON() {
        JSONDataManager jsonMa = new JSONDataManager();
        jsonMa.saveGraphIntoJSON("./data/grafoDefinitivo.json", model.getGraph1());
    }

    public  void loadGraphFromJSON() {
        JSONDataManager jsonMa = new JSONDataManager();
        jsonMa.loadGraphFromJSON("./data/grafoDefinitivo.json", model.getGraph1());
    }

    public  String B1(double longS,
                     double latS,
                     double longD,
                     double latD) {

        String res = "";
        try {
            res = model.getBManager().B1(model.getGraph1(), longS, latS, longD, latD);
        } catch (Exception e) {

        }
        return res;


    }

    public   String B2(double longS,
                     double latS,

                     double time) {

        String res = "";
        try {
            res = model.getBManager().B2(model.getGraph1(), longS, latS, time);
        } catch (Exception e) {

        }
        return res;


    }

	public  String B3() {

		String res = "";
		try {
			res = model.getBManager().B3(model.getGraph1());
		} catch (Exception e) {

		}
		return res;


	}

	public  String C1() {

		String res = "";
		try {
			res = model.getCManager().C1(model.getGraph1(), model.getTravelTimesTree());
		} catch (Exception e) {

		}
		return res;


	}

	public  String C2(int origen, int destino) {

		String res = "";
		try {
			res = model.getCManager().C2(origen, destino, model.getTravelTimesTree());
		} catch (Exception e) {

		}
		return res;


	}

	public  String C3(int origen) throws UnexistingVertexException {

		return model.getCManager().C3(origen);


	}


}
