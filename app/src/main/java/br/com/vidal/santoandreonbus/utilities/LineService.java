package br.com.vidal.santoandreonbus.utilities;

import com.google.gson.Gson;

import br.com.vidal.santoandreonbus.models.Line;

public class LineService {

    private RestCredentials credentials;
    private RestClient client;
    private Gson gson;

    public LineService() {
        this.credentials = new RestCredentials();
        this.client = new RestClient();
        this.gson = new Gson();
    }

    public String getToken() throws Exception {
        String token = client.post("authentication/login", gson.toJson(credentials));
        return token.replace("\"", "");
    }

    public Line[] getAllLines() throws Exception {
        String json = client.get("lines/", getToken());
        return gson.fromJson(json, Line[].class);
    }

    public Line getLine(String denomination) throws Exception {
        String json = client.get("lines/" + denomination, getToken());
        return gson.fromJson(json, Line.class);
    }
}
