package br.com.vidal.santoandreonbus.rest;

public class RestCredentials {
    public String email;
    public String password;

    public RestCredentials() {
        if (Env.SELECTED == RestEnvironment.DEVELOPMENT) {
            email = EnvForDevelopment.USERNAME;
            password = EnvForDevelopment.PASSWORD;
        } else {
            email = EnvForProduction.USERNAME;
            password = EnvForProduction.PASSWORD;
        }
    }
}
