package br.com.vidal.santoandreonbus.rest;

import java.util.Collections;

import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClientForProduction implements RestClient {

    public String get(String urn, String token) throws Exception {
        Request request = setRequest(EnvForProduction.URL + urn)
                .header("Authorization", "Bearer " + token)
                .build();

        return fetchData(getClient(), request);
    }

    public String post(String urn, String body) throws Exception {
        Request request = setRequest(EnvForProduction.URL + urn)
                .post(RequestBody.create(body, MediaType.parse(Env.CONTENT_TYPE)))
                .header("Content-Type", Env.CONTENT_TYPE)
                .build();

        return fetchData(getClient(), request);
    }

    private OkHttpClient getClient() {
        return new OkHttpClient().newBuilder()
                .connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS))
                .build();
    }

    private Request.Builder setRequest(String uri) {
        return new Request.Builder()
                .url(uri)
                .header("Accept", Env.CONTENT_TYPE);
    }

    private String fetchData(OkHttpClient client, Request request) throws Exception {
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
