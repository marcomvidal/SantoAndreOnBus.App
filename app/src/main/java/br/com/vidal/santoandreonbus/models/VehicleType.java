package br.com.vidal.santoandreonbus.models;

import com.google.gson.annotations.SerializedName;

public enum VehicleType {
    @SerializedName("Microônibus") Microonibus("Microônibus"),
    @SerializedName("Médio") Medio("Médio"),
    @SerializedName("Padrão") Padrao("Padrão");
    public String description;

    VehicleType(String description) { this.description = description; }
    public String toString() { return description; }
}
