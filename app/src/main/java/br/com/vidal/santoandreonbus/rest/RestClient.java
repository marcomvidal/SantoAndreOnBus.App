package br.com.vidal.santoandreonbus.rest;

public interface RestClient {
    String get(String urn, String token) throws Exception;
    String post(String urn, String body) throws Exception;
}
