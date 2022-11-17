package com.example.telegrambot.entity;

public class Actividad {
    private String name;
    private long puntos;

    public Actividad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPuntos() {
        return puntos;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }
}
