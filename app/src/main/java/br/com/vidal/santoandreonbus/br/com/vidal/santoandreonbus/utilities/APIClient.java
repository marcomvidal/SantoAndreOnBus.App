package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.utilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIClient {
    private static final String URL = "http://192.168.15.32:3000/";
    private static final String CONTENT_TYPE = "application/json";

    public String get(String urn) throws Exception {
        URL uri = new URL(URL + urn);

        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", CONTENT_TYPE);
        connection.connect();

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder json = new StringBuilder();

        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }

        scanner.close();
        connection.disconnect();
        return json.toString();
    }
}
