package com.example.soccerleague.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.soccerleague.R;
import com.example.soccerleague.adapters.FixtureAdapter;
import com.example.soccerleague.models.MatchModel;
import com.example.soccerleague.models.TeamModel;
import com.example.soccerleague.ui.activities.FixtureActivity;
import com.example.soccerleague.utils.FixtureGenerator;

import java.util.ArrayList;


public class FixtureFragment extends Fragment {

    ArrayList<TeamModel> teamList = new ArrayList<>();
    ArrayList<MatchModel> matchModels = new ArrayList<>();

    int flag = 1;
    int weekNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FixtureActivity activity = (FixtureActivity) getActivity();
        teamList = activity.sendData();
        if(teamList.size() % 2 == 1 && flag == 1) {
            teamList.add(new TeamModel(teamList.size() + 1, "-"));
            flag = 0;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_fixture, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_match);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        FixtureAdapter adapter =  new FixtureAdapter();
        recyclerView.setAdapter(adapter);

        String[][] rounds = FixtureGenerator.generateFixture(teamList.size());
        int index1, index2;

        int position = getArguments().getInt("position", 0);
        weekNumber = teamList.size()-1;//Her takım kendisi dışındaki takımlar ile maç yapmalı

        for(int i=0;i < teamList.size()/2 ; i++){
            if(position >= weekNumber){ //for second half of league matches
                String[] components = rounds[position-weekNumber][i].split(" v ");
                index1 = Integer.parseInt(components[1])-1;
                index2 = Integer.parseInt(components[0])-1;

            }
            else{ //for first half of league matches
                String[] components = rounds[position][i].split(" v ");
                index1 = Integer.parseInt(components[0])-1;
                index2 = Integer.parseInt(components[1])-1;
            }

            matchModels.add(new MatchModel(teamList.get(index1),teamList.get(index2)));
        }
        adapter.setMatches(matchModels);

        return view;
    }

    public static FixtureFragment addfrag(int position) {
       FixtureFragment fragment = new FixtureFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }
}