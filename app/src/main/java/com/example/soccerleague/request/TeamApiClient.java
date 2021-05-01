package com.example.soccerleague.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.soccerleague.utils.AppExecutors;
import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.response.TeamResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class TeamApiClient {
    //this class is a like bridge between the retrofit data and live data

    //Live Data
    private MutableLiveData<List<TeamModel>> mTeams;
    private static TeamApiClient instance;

    //making global request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static TeamApiClient getInstance() {
        if (instance == null) {
            instance = new TeamApiClient();
        }
        return instance;

    }

    private TeamApiClient() {
        mTeams = new MutableLiveData<>();
    }

    public LiveData<List<TeamModel>> getTeams() {
        return mTeams;
    }


    //1-This method that we are going to call through the classes
    public void getTeamsApi() {
        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable();

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling
                myHandler.cancel(true);
            }
        }, 30, TimeUnit.SECONDS);


        //Retrieving data from RESTAPI by runnable class

    }

    private class RetrieveMoviesRunnable implements Runnable {

        boolean cancelRequest=false;

        public RetrieveMoviesRunnable() {
            cancelRequest = false;
        }


        @Override
        public void run() {

            try{
                Response response = getTeams().execute();
                if(cancelRequest){
                    return;
                }
                if(response.code()==200){
                    List<TeamModel> list = new ArrayList<>(((TeamResponse)response.body()).getTeam());
                    mTeams.postValue(list);



                }
                else{
                    String error = response.errorBody().string();
                    Log.v("Tag","Error "+error);
                    mTeams.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mTeams.postValue(null);
            }


            if (cancelRequest) {
                return;
            }

        }

        private Call<TeamResponse> getTeams(){
            return Service.getTeamApi().getTeam();
        }

        private void cancelRequest(){
            Log.v("Tag","Cancelling Search Request");
            cancelRequest = true;
        }

    }


}
