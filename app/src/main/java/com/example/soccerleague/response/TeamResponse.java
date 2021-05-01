package com.example.soccerleague.response;

import com.example.soccerleague.models.TeamModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {

    @SerializedName("teams1") // you can check different league(teams1, teams2 or teams3)
    @Expose()
    private List<TeamModel> team;

    public List<TeamModel> getTeam(){
        return team;
    }

}
