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
    public List<Vehicle> vehicles;

    public String getDenomination() {
        return this.letter + "-" + this.number;
    }

    public String getPrefixesSeparatedByComma() {
        StringBuilder prefixes = new StringBuilder();

        for (int i = 0; i < this.company.prefixes.size(); i++) {
            prefixes.append(this.company.prefixes.get(i).number);

            if (i < this.company.prefixes.size() - 1) prefixes.append(", ");
        }

        return prefixes.toString();
    }
}
