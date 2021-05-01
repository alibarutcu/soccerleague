package com.example.soccerleague.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.repositories.TeamRepository;

import java.util.List;

public class TeamListViewModel extends ViewModel {
    //this class is used for VIEWMODEL

    private TeamRepository teamRepository;

    //Constructor..
    public TeamListViewModel() {
        teamRepository = TeamRepository.getInstance();

    }

    public LiveData<List<TeamModel>> getTeams(){
        return teamRepository.getTeams();
    }

    //3-Calling method in viewmodel
    public void getTeamsApi(){
        teamRepository.getTeamsApi();
    }
}
