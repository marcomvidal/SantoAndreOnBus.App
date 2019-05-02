package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.InterestPoint;
import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;

public class JsonParser {
    private Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    public Line toLine(String json) {
        return gson.fromJson(json, Line.class);
    }

    public List<InterestPoint> toInterestPointsList(String jsonString) {
        List<InterestPoint> interestPoints = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonInterestPoints = json.getJSONArray("interestPoints");

            for (int i = 0; i < jsonInterestPoints.length(); i++) {
                String jsonInterestPoint = jsonInterestPoints.getJSONObject(i).toString();
                interestPoints.add(gson.fromJson(jsonInterestPoint, InterestPoint.class));
            }

            return interestPoints;
        } catch (JSONException exception) {
            throw new RuntimeException(exception);
        }
    }
}
