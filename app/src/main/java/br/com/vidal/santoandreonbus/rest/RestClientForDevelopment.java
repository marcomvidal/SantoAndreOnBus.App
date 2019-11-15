package br.com.vidal.santoandreonbus.rest;

import android.net.SSLCertificateSocketFactory;

import java.io.PrintStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class RestClientForDevelopment implements RestClient {

    public String get(String urn, String token) throws Exception {
        String uri = EnvForDevelopment.URL + urn;
        HttpsURLConnection connection = setConnection(new URL(uri), "GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.connect();

        return fetchData(connection);
    }

    public String post(String urn, String body) throws Exception {
        String uri = EnvForDevelopment.URL + urn;
        HttpsURLConnection connection = setConnection(new URL(uri), "POST");
        connection.setRequestProperty("Content-type", Env.CONTENT_TYPE);
        connection.setDoOutput(true);

        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(body);

        connection.connect();

        return fetchData(connection);
    }

    private HttpsURLConnection setConnection(URL uri, String httpMethod) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
        connection.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
        connection.setRequestMethod(httpMethod);
        connection.setRequestProperty("Accept", Env.CONTENT_TYPE);
        connection.setHostnameVerifier(ignoreSSLHostnameVerifier());
        connection.setConnectTimeout(EnvForDevelopment.CONNECTION_TIMEOUT);

        return connection;
    }

    private String fetchData(HttpsURLConnection connection) throws Exception {
        if (connection.getResponseCode() == 401) { throw new HttpUnauthorizedAccessException(); }
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder json = new StringBuilder();

        while (scanner.hasNext()) { json.append(scanner.nextLine()); }

        scanner.close();
        connection.disconnect();
        return json.toString();
    }

    private HostnameVerifier ignoreSSLHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) { return true; }
        };
    }
}
