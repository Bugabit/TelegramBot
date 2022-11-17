package com.example.telegrambot.entity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Team implements Comparable<Team> {
    private int id;
    private String name;
    private List<Actividad> actividades;

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public long getPuntos() {
        long puntos = 0;
        for (Actividad actividad : actividades) {
            puntos += actividad.getPuntos();
        }
        return puntos;
    }

    @Override
    public int compareTo(@NotNull Team team) {
        return (int) (team.getPuntos() - this.getPuntos());
    }

    @Override
    public String toString() {
        return this.name + ": " + this.getPuntos();
    }
}
