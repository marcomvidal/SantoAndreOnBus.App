package br.com.vidal.santoandreonbus.br.com.vidal.santoandreonbus.models;

import android.support.annotation.NonNull;

public class InterestPoint {
    public int id;
    public String name;

    @NonNull
    @Override
    public String toString() { return this.name; }
}
