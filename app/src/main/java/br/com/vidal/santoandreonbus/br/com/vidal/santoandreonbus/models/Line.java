package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Line {

    @SerializedName("_id")
    public int id;

    public String letter;
    public String number;
    public String fromwards;
    public String towards;
    public int peakHeadway;
    public int offpeakHeadway;
    public List<InterestPoint> interestPoints;

    public String getDenomination() {
        return this.letter + "-" + this.number;
    }
}
