package com.example.telegrambot;

import java.util.*;

public class TeamData {
    private List<Team> teamdata;

    public TeamData() {
    }

    public List<Team> getTeamdata() {
        return teamdata;
    }

    public void setTeamdata(List<Team> teamdata) {
        this.teamdata = teamdata;
    }

    public List<Team> getWinners() {
        Collections.sort(teamdata);
        return teamdata;
    }
}
