package com.example.soccerleague.utils;

import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.response.TeamResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamApi {

    @GET("db")
    Call<TeamResponse> getTeam();
}
