package br.com.vidal.santoandreonbus.models;

public enum VehicleType {
    Microonibus("Microônibus"), Medio("Médio"), Padrao("Padrão");
    public String description;

    VehicleType(String description) { this.description = description; }
    public String toString() { return description; }
}
