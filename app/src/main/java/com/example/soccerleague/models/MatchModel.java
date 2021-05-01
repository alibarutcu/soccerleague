package com.example.soccerleague.models;

public class MatchModel {

    private TeamModel team1;
    private TeamModel team2;

    public TeamModel getTeam1() {
        return team1;
    }

    public TeamModel getTeam2() {
        return team2;
    }

    public MatchModel(TeamModel team1, TeamModel team2) {
        this.team1 = team1;
        this.team2 = team2;
    }
}
