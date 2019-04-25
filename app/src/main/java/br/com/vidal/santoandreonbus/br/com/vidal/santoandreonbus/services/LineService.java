package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.services;

import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;

public class LineService {
    public Line find() {
        Line line = new Line();
        line.id = 1;
        line.letter = "I";
        line.number = "04";
        line.fromwards = "Jardim Las Vegas";
        line.towards = "Parque Capuava";
        line.peakHeadway = 5;
        line.offpeakHeadway = 15;
        line.interestPoints = getInterestPoints();

        return line;
    }

    private List<InterestPoint> getInterestPoints() {
        ArrayList<InterestPoint> interestPoints = new ArrayList<>();

        InterestPoint interestPoint1 = new InterestPoint();
        interestPoint1.id = 1;
        interestPoint1.name = "Cem. Curuçá";
        interestPoints.add(interestPoint1);

        InterestPoint interestPoint2 = new InterestPoint();
        interestPoint2.id = 2;
        interestPoint2.name = "Pq. Reg. da Criança";
        interestPoints.add(interestPoint2);

        InterestPoint interestPoint3 = new InterestPoint();
        interestPoint3.id = 3;
        interestPoint3.name = "Estação";
        interestPoints.add(interestPoint3);

        InterestPoint interestPoint4 = new InterestPoint();
        interestPoint4.id = 4;
        interestPoint4.name = "R. Carijós";
        interestPoints.add(interestPoint4);

        return interestPoints;
    }
}
