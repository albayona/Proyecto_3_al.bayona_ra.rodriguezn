package model.vo;

public class TravelTime {

    private int idSource;

    private int idDestine;

    private int timeIndicator;

    private double meanTime;

    private double standardDeviation;


    public TravelTime(int idSource, int idDestine, int timeIndicator, double meanTime, double standardDeviation) {
        this.idSource = idSource;
        this.idDestine = idDestine;
        this.timeIndicator = timeIndicator;
        this.meanTime = meanTime;
        this.standardDeviation = standardDeviation;
    }

    public int getIdSource() {
        return idSource;
    }

    public int getIdDestine() {
        return idDestine;
    }

    public int getTimeIndicator() {
        return timeIndicator;
    }

    public double getMeanTime() {
        return meanTime;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public String toString(){

        return "" + meanTime;
    }
}