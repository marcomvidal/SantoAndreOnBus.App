package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.parsers;

public interface JSONParseable<T> {
    String toJSON(T instance);
    T toInstance(String jsonString);
}
