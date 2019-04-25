package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models.Line;

public class LineParser implements JSONParseable<Line> {
    public String toJSON(Line line) {
        try {
            JSONStringer json = new JSONStringer();
            json.object().key("line");
            return json.object()
                    .key("id").value(line.id)
                    .key("letter").value(line.letter)
                    .key("number").value(line.number)
                    .key("fromwards").value(line.fromwards)
                    .key("towards").value(line.towards)
                    .key("peakHeadway").value(line.peakHeadway)
                    .key("offpeakHeadway").value(line.offpeakHeadway)
                    .endObject()
                    .toString();
        } catch (JSONException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Line toInstance(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            Line line = new Line();

            line.id = json.getInt("id");
            line.letter = json.getString("letter");
            line.number = json.getString("number");
            line.fromwards = json.getString("fromwards");
            line.towards = json.getString("towards");
            line.peakHeadway = json.getInt("peakHeadway");
            line.offpeakHeadway = json.getInt("offpeakHeadway");

            return line;
        } catch (JSONException exception) {
            throw new RuntimeException(exception);
        }
    }

}
