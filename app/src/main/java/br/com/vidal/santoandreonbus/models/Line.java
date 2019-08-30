package br.com.vidal.santoandreonbus.models;


import java.io.Serializable;
import java.util.List;


public class Line implements Serializable {

    public int id;
    public String letter;
    public String number;
    public String fromwards;
    public String towards;
    public int peakHeadway;
    public Company company;
    public List<InterestPoint> interestPoints;
    public List<Place> places;
    public List<LineVehicle> lineVehicles;

    public String getDenomination() {
        return this.letter + "-" + this.number;
    }
}
