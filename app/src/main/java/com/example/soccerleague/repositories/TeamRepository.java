package com.example.soccerleague.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.request.TeamApiClient;

import java.util.List;

public class TeamRepository {
    //This class is acting as repository

    private static TeamRepository instance ;

    private TeamApiClient teamApiClient;


    public static TeamRepository getInstance(){
        if(instance == null){
            instance = new TeamRepository();
        }
        return instance;

    }

    private TeamRepository(){
        teamApiClient = TeamApiClient.getInstance();

    }


    public LiveData<List<TeamModel>> getTeams(){
        return teamApiClient.getTeams();
    }

    //2-Calling method
    public void getTeamsApi(){
        teamApiClient.getTeamsApi();
    }

}
