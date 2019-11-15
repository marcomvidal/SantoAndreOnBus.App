package br.com.vidal.santoandreonbus.rest;

public class HttpUnauthorizedAccessException extends Exception {

    @Override
    public String getMessage() {
        return "HTTP 401 (unauthorized) obtained from API. JWT token is missing.";
    }
}
