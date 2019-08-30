package br.com.vidal.santoandreonbus.utilities;

import android.net.SSLCertificateSocketFactory;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class APIClient {
    private static final String URL = "https://192.168.15.12:5001/api/";
    private static final String CONTENT_TYPE = "application/json";

    public String get(String urn) throws Exception {
        URL uri = new URL(URL + urn);

        HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
        connection.setSSLSocketFactory(
                SSLCertificateSocketFactory.getInsecure(0, null)
        );
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", CONTENT_TYPE);
        connection.setHostnameVerifier(ignoreSSLHostnameVerifier());
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

    private HostnameVerifier ignoreSSLHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
