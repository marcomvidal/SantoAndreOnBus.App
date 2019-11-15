package br.com.vidal.santoandreonbus.utilities;

import com.google.gson.Gson;

import br.com.vidal.santoandreonbus.models.Line;
import br.com.vidal.santoandreonbus.models.Token;
import br.com.vidal.santoandreonbus.rest.Env;
import br.com.vidal.santoandreonbus.rest.RestClientForDevelopment;
import br.com.vidal.santoandreonbus.rest.RestClientForProduction;
import br.com.vidal.santoandreonbus.rest.RestClient;
import br.com.vidal.santoandreonbus.rest.RestCredentials;
import br.com.vidal.santoandreonbus.rest.RestEnvironment;

public class LineService {

    private RestCredentials credentials;
    private Gson gson;
    private RestClient client;

    public LineService() {
        this.credentials = new RestCredentials();
        this.gson = new Gson();
        this.client = Env.SELECTED == RestEnvironment.DEVELOPMENT ?
                new RestClientForDevelopment() :
                new RestClientForProduction();
    }

    public String getToken() throws Exception {
        if (Env.token == null) {
            String json = client.post("authentication/login", gson.toJson(credentials));
            Token token = gson.fromJson(json, Token.class);
            Env.token = token.token;
        }

        return Env.token;
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
