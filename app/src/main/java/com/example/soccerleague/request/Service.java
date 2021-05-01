package com.example.soccerleague.request;

import com.example.soccerleague.utils.Credentials;
import com.example.soccerleague.utils.TeamApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static TeamApi teamApi = retrofit.create(TeamApi.class);

    public static TeamApi getTeamApi(){
        return teamApi;
    }

}
