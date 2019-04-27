package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;

public class InterestPointParser implements JSONParseable<List<InterestPoint>> {

    @Override
    public String toJSON(List<InterestPoint> interestPoint) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<InterestPoint> toInstance(String jsonString) {
        List<InterestPoint> interestPoints = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonInterestPoints = json.getJSONArray("interestPoints");

            for (int i = 0; i < jsonInterestPoints.length(); i++) {
                JSONObject jsonInterestPoint = jsonInterestPoints.getJSONObject(i);
                InterestPoint interestPoint = new InterestPoint();

                interestPoint.id = jsonInterestPoint.getInt("_id");
                interestPoint.name = jsonInterestPoint.getString("name");
                interestPoints.add(interestPoint);
            }

            return interestPoints;
        } catch (JSONException exception) {
            throw new RuntimeException(exception);
        }
    }
}
